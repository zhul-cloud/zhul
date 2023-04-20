package com.zhul.cloud.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by yanglikai on 2019/5/28.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class RootObject implements Serializable {
}
