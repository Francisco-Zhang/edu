package com.edu.domain;

import org.springframework.data.jpa.repository.EntityGraph;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedEntityGraph(name = "Book.fetch.category.and.author",
attributeNodes = {
        @NamedAttributeNode("category"),
        @NamedAttributeNode("author")
    }
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   //默认为这种形式，即ebook,printBook都在一张表
//@Inheritance(strategy = InheritanceType.JOINED)  //这种方式会生成三张表，通过外键关联
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)  // 生成三张表，每个类生成一张表，注意要修改主键的生成方式，防止主键重复，适用于子类比较少（不适合union）,父子类差别比较大
public class Book extends BaseEntity{     //以上两种方式执行 test10会生成不同的表和操作sql,



    private  String name;

    // opthinal=fale允许category为空，默认true
    @ManyToOne(fetch = FetchType.LAZY,optional = true)   //懒加载  默认是 eager，会直接加载
    private  Category category;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> author;

    @Version
    private  int version;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<BookAuthor> getAuthor() {
        return author;
    }

    public void setAuthor(List<BookAuthor> author) {
        this.author = author;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
