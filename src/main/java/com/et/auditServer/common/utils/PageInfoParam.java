package com.et.auditServer.common.utils;

import java.util.List;
import java.util.Map;

public class PageInfoParam<T> extends com.github.pagehelper.PageInfo {
    public PageInfoParam(List<T> list) {
        super(list);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    private Map<String, String> params;


}
