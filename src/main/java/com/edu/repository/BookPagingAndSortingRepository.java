package com.edu.repository;

import com.edu.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface BookPagingAndSortingRepository extends PagingAndSortingRepository<Book,Long> {

    List<Book> findByName(String name);

    List<Book>  findByCreatedTime(Date date);

}
