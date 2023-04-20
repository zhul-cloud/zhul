package com.zhul.cloud.feign;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import com.nepxion.discovery.plugin.framework.adapter.PluginAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractFeignImpl {
    @Autowired
    private PluginAdapter pluginAdapter;

    public String doInvoke(String value) {
        return pluginAdapter.getPluginInfo(value);
    }
}