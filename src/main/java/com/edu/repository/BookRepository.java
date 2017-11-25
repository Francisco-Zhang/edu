package com.edu.repository;

import com.edu.domain.Book;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface BookRepository extends Repository<Book,Long>{

    List<Book> findByName(String name);

}
