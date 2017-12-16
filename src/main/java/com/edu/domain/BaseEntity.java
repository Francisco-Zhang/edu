package com.edu.domain;


import com.sun.org.glassfish.gmbal.ParameterNames;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue   //默认自增长
//    @GeneratedValue(generator = "sequenceGenerator")
//    @GenericGenerator(name = "sequenceGenerator",strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//    parameters = {
//            @Parameter(name= SequenceStyleGenerator.SEQUENCE_PARAM,value = "ID_SEQUENCE"),
//            @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM,value = "1000"),
//            @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM,value = "1")
//    })
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
