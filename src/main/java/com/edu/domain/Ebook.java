package com.edu.domain;

import javax.persistence.Entity;


//并不会生成新的表，而是在book表中，通过dType来区分不同的book类型，dtype为自动生成的字段，子类中特有的属性也都会添加到book中
@Entity
public class Ebook extends  Book {

    private  String formate;

    public String getFormate() {
        return formate;
    }

    public void setFormate(String formate) {
        this.formate = formate;
    }
}
