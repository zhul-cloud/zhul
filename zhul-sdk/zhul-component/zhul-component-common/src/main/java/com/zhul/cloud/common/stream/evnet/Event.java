package com.zhul.cloud.common.stream.evnet;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Map;
import lombok.Data;

/**
 * 事件对象
 * <p></p>
 * Created by yanglikai on 2021/5/28.
 */
@Data
public class Event implements Serializable {

  @ApiModelProperty(value = "事件编号")
  private String id;

  @ApiModelProperty(value = "创建时间")
  private String created;

  @ApiModelProperty(value = "事件类型")
  private String type;

  @ApiModelProperty(value = "事件标签")
  private String tags;

  @ApiModelProperty(value = "消息键")
  private String key;

  @ApiModelProperty(value = "事件数据")
  private Map<String, Object> data;

  public Event() {
    this.data = Maps.newHashMap();
  }


  private static final String OBJECT_KEY = "object";

  public <T> T tObject(Class<T> target) {
    if (this.data == null) {
      return null;
    }

    Object obj = data.getOrDefault(OBJECT_KEY, null);
    if (obj == null) {
      return null;
    }

    String json = JSONUtil.toJsonStr(obj);

    return JSONUtil.toBean(json, target);
  }

  public void addData(Object target) {
    this.data.put(OBJECT_KEY, target);
  }
}
