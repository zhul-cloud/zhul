package com.zhul.cloud.discovery.nacos;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class NacosDiscoveryEnvProcessor extends ZhulEnvProcessor {

    @Override
    public String getPrefixName() {
        return "nacos-discovery";
    }

    @Override
    public int getOrder() {
        return super.getOrder() + 1;
    }
}
