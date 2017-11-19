package com.edu.domain;


import javax.persistence.*;
import java.util.List;


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


   // @OneToMany  //这种方式会多出一张对照表，应该尽量避免这种方式，可以在Book中使用ManytoOne建立外键
    // CascadeType.REMOVE:级联删除，删除Category的同时删掉book,
    //orphanRemoval 默认的时候false,表示 执行List.remove的时候不会删掉该book记录，true在执行 remove(book)时会删掉
    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE, orphanRemoval = false)  //由book这张表维护关系
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

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
