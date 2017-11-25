package com.edu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class AuthorInfo {
    @Id
    @GeneratedValue
    private  Long id;

    private String school;

    @OneToOne(mappedBy = "info")
    private  Author author;

}
