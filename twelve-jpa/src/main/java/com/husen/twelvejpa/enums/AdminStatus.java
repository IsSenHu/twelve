package com.husen.twelvejpa.enums;

/**
 * Created by HuSen on 2018/10/29 17:27.
 */
public enum AdminStatus {
    ENABLE(1, "启用"),
    DISABLED(2, "禁用");

    private Integer value;
    private String description;

    public final static String Doc = "1:启用;2:禁用;";

    AdminStatus(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
