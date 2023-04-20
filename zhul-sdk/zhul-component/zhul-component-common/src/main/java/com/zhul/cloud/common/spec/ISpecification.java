package com.zhul.cloud.common.spec;

import javax.validation.constraints.NotNull;

/**
 * Created by yanglikai on 2022/12/07.
 */
public interface ISpecification<T> {

  boolean satisfiedBy(@NotNull T candidate);

  boolean satisfiedBy(@NotNull T candidate, Notification notification);
}
