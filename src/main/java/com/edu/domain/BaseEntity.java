package com.edu.domain;


import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long id;  //对应数据库bigint类型

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime=new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
