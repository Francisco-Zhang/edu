package com.edu.repository;

import com.edu.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface BookCrudRepository extends CrudRepository<Book,Long>{


}
