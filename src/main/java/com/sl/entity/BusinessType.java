package com.sl.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
@Entity
@Table(name = "business_type")
public class BusinessType {
    private Long businessTypeId;
    private String businessTypeName;

    public BusinessType() {
    }

    public BusinessType(Long businessTypeId, String businessTypeName) {
        this.businessTypeId = businessTypeId;
        this.businessTypeName = businessTypeName;
    }

    @Id
    @GeneratedValue
    public Long getBusinessTypeId() {
        return businessTypeId;
    }

    public void setBusinessTypeId(Long businessTypeId) {
        this.businessTypeId = businessTypeId;
    }

    @Column(name = "business_type_name")
    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

}
