package com.edu.domain;


import javax.persistence.*;


//@Table(name = "edu_category") 此处会覆盖全局都命名策略
@Entity
public class Category {

    //Id注解标识主键
    @Id
    //默认是Auto,此设置是根据数据库默认设置（mysql设置为自增长）
    @GeneratedValue
    private  long id;

    //什么都不写，默认加注解 @Basic,会自动生成数据库字段
    @Column(name = "edu_name",length =10,nullable = false,unique = true)
    private String name;

    @Transient  //用于标识该属性不需要生成数据库表对应都字段
    private  String xxxx;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
