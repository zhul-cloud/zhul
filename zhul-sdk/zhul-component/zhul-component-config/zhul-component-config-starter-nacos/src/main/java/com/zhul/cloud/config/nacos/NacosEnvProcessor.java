package com.zhul.cloud.config.nacos;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class NacosEnvProcessor extends ZhulEnvProcessor {

    @Override
    public String getPrefixName() {
        return "nacos-config";
    }

    @Override
    public int getOrder() {
        return super.getOrder();
    }
}
