package com.edu.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;  //对应数据库bigint类型

    private  String name;

    //生成数据库int
    @Column(columnDefinition = "INT(3)")
    private  int age;

    @Temporal(TemporalType.DATE)   //用来指定数据库数据类型省区时分秒，不使用注解默认dateTime类型
    private Date birthday;

    @Enumerated(EnumType.STRING)  //用来处理枚举类型
    private  Sex sex;

    @Embedded      //被注入的一批属性 ，用于内嵌对象的映射 ，具有相同生命周期（有Author,就有address）
    private  Address address;


    @ElementCollection   //集合注解，数据库会多出一张表
    private List<String> hobbies;

    @ElementCollection
    private  List<Address> addresses;

    @OneToMany(mappedBy = "author")
    @OrderBy("book.name ASC")   //默认时id排序
    private  List<BookAuthor> books;

    @OneToOne
    private  AuthorInfo info;

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }


    public List<BookAuthor> getBooks() {
        return books;
    }

    public void setBooks(List<BookAuthor> books) {
        this.books = books;
    }
}
