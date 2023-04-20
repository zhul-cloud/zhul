package com.zhul.cloud.framework.service;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class ZhulServiceEnvProcessor extends ZhulEnvProcessor {

    @Override
    public String getPrefixName() {
        return "zhul-service-config";
    }
}
