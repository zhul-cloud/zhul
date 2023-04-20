package com.zhul.cloud.framework.api;

import com.zhul.cloud.env.ZhulEnvProcessor;

/**
 * Created by yanglikai on 2020/12/17.
 */
public class ZhulApplicationEnvProcessor extends ZhulEnvProcessor {

    @Override
    public String getPrefixName() {
        return "zhul-application-config";
    }
}
