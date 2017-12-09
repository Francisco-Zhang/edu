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
public class Book extends BaseEntity{



    private  String name;

    // opthinal=fale允许category为空，默认true
    @ManyToOne(fetch = FetchType.LAZY,optional = true)   //懒加载  默认是 eager，会直接加载
    private  Category category;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> author;

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
}
