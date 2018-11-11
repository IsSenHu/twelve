package com.husen.twelvejpa.converter;


import com.husen.twelvejpa.enums.AdminStatus;
import javax.persistence.AttributeConverter;

/**
 * Created by HuSen on 2018/10/29 17:32.
 */
public class AdminConverter implements AttributeConverter<AdminStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AdminStatus adminStatus) {
        if(null == adminStatus) {
            return AdminStatus.DISABLED.getValue();
        }
        return adminStatus.getValue();
    }

    @Override
    public AdminStatus convertToEntityAttribute(Integer integer) {
        AdminStatus[] values = AdminStatus.values();
        for(AdminStatus adminStatus : values) {
            if(adminStatus.getValue().equals(integer)) {
                return adminStatus;
            }
        }
        return AdminStatus.DISABLED;
    }
}
