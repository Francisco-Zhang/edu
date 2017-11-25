package com.edu.repository;

import com.edu.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RepositoryTest extends BaseTest {

    @Autowired
    private  BookRepository bookRepository;

    @Test
    public void  test1(){
        bookRepository.findByName("战争与和平");

    }

}
