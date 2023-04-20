package com.zhul.cloud.common.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.mapper.annotation.MapperProperty;
import com.zhul.cloud.common.mapper.annotation.MapperSource;
import com.zhul.cloud.common.model.AntdPage;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.MapperKey;
import ma.glasnost.orika.metadata.TypeFactory;

/**
 * Created by yanglikai on 2019/5/28.
 */
public final class Mapper {

  private Mapper() {
  }

  private static final MapperFactory mapperFactory;

  private static final Object CLASS_MAP_REGISTER_LOCK = new Object();

  static {
    mapperFactory = new DefaultMapperFactory.Builder().build();
  }

  public static <T> AntdPage<T> mapToAntd(Page source, Class target) {
    Objects.requireNonNull(source, "source must be not null");

    if (CollectionUtil.isEmpty(source.getRecords())) {
      return new AntdPage<>(source.getTotal(), source.getSize(), source.getCurrent());
    }

    AntdPage<T> newPages = new AntdPage<T>();
    newPages.setTotal(source.getTotal());
    newPages.setPageSize(source.getSize());
    newPages.setCurrent(source.getCurrent());
    newPages.setData((List<T>) source.getRecords().stream().map(e1 -> map(e1, target)).collect(
        Collectors.toList()));

    return newPages;
  }

  public static <T> Page<T> map(Page source, Class target) {
    Objects.requireNonNull(source, "source must be not null");

    if (CollectionUtil.isEmpty(source.getRecords())) {
      return source;
    }

    Page<T> newPages = new Page<T>();
    newPages.setTotal(source.getTotal());
    newPages.setSize(source.getSize());
    newPages.setCurrent(source.getCurrent());
    newPages.setPages(source.getPages());
    newPages
        .setRecords((List<T>) source.getRecords().stream().map(e1 -> map(e1, target)).collect(
            Collectors.toList()));

    return newPages;
  }

  public static <T> List<T> map(List source, Class target) {
    Objects.requireNonNull(source, "source must be not null");

    List<T> list = new ArrayList<>(source.size());

    for (Object o : source) {
      T result = map(o, target);

      list.add(result);
    }

    return list;
  }

  public static <T> T map(Object source, Class target) {
    Objects.requireNonNull(source, "source must be not null");

    MapperSource mapperSource = (MapperSource) target.getAnnotation(MapperSource.class);
    Class sourceClass;
    if (mapperSource == null) {
      sourceClass = source.getClass();
    } else {
      sourceClass = mapperSource.value();
    }

    MapperKey mapperKey = getMapperKey(target, sourceClass);
    if (mapperFactory.getClassMap(mapperKey) == null) {
      synchronized (CLASS_MAP_REGISTER_LOCK) {
        if (mapperFactory.getClassMap(mapperKey) == null) {
          registerClassMap(target, sourceClass);
        }
      }
    }

    BoundMapperFacade mapperFacade = mapperFactory.getMapperFacade(sourceClass, target);
    T result = (T) mapperFacade.map(source);
    return result;
  }

  @SuppressWarnings("unchecked")
  private static MapperKey getMapperKey(Class tc, Class sc) {
    return new MapperKey(TypeFactory.valueOf(sc), TypeFactory.valueOf(tc));
  }

  private static void registerClassMap(Class tc, Class sc) {
    ClassMapBuilder classMapBuilder = mapperFactory.classMap(sc, tc);

    Field[] fields = tc.getDeclaredFields();
    for (Field field : fields) {
      MapperProperty mapperProperty = field.getAnnotation(MapperProperty.class);
      if (mapperProperty == null) {
        continue;
      }

      for (String el : mapperProperty.value()) {
        if (findField(sc, el) == null) {
          continue;
        }

        classMapBuilder.field(el, field.getName());
      }
    }

    classMapBuilder.byDefault().register();
  }

  private static Field findField(Class clazz, String fieldName) {
    for (Field field : clazz.getDeclaredFields()) {
      if (field.getName().equals(fieldName)) {
        return field;
      }
    }

    return null;
  }
}
