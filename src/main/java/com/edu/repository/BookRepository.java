package com.edu.repository;

import com.edu.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByName(String name);

    List<Book>  findByCreatedTime(Date date);

    List<Book>  findByNameAndCategoryName(String bookName,String categoryName);

    //还包括 Or、Between、LessThan、LessThanEqual、GreaterThan、IsNull、NotNull等关键字
    List<Book> findByNameLike(String name);

    List<Book> findByNameLikeOrderByNameDesc(String name);

}
