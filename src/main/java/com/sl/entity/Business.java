package com.sl.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/7 0007.
 */
@Entity
@Table(name = "business")
public class Business {
    private Long businessId;
    private String businessName;
    private BusinessType businessType;
    private Set<User> users = new HashSet<User>();

    public Business() {
    }

    public Business(Long businessId, String businessName, BusinessType businessType, Set<User> users) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.businessType = businessType;
        this.users = users;
    }

    @Id
    @GeneratedValue
    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Column(name = "business_name")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @OneToOne(optional = false)
    @JoinColumn(name = "business_type_id",unique=true, nullable=false, updatable=false)
    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }

    @OneToMany(mappedBy = "business")
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
