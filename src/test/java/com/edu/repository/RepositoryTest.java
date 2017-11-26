package com.edu.repository;

import com.edu.BaseTest;
import com.edu.domain.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositoryTest extends BaseTest {

    @Autowired
    private  BookBaseRepository bookbaseRepository;

    @Autowired
    private BookCrudRepository bookCrudRepository;

    @Autowired
    private  BookRepository bookRepository;


    @Test
    public void test(){
        bookRepository.findAll(new Sort(Sort.Direction.ASC,"name","id"));



    }


    @Test
    public  void  test1(){
        Book book=new Book();
        book.setName("战争与和平");
        bookCrudRepository.save(book);   //测试用例会回滚掉，并不会commite 到数据库中

        Book book1=bookCrudRepository.findOne(1L);   // 如果book中 category抓取策略是 eager，此处会生成关联查询

        System.out.println(bookCrudRepository.exists(1L));  //是否存在
        bookCrudRepository.findAll();   // 比较危险，查询所有

        List<Long>  ids =new ArrayList<Long>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        bookCrudRepository.findAll(ids);   // in 查询





    }

    @Test
    public void  test2(){

        //注入的是代理，只要是继承例Repository接口，都会生成代理，但是CurdRepository等有特殊注解NoBeans,所以不会为其生成代理
        System.out.println(bookbaseRepository.getClass().getName());
        bookbaseRepository.findByName("战争与和平");
        bookbaseRepository.findByCreatedTime(new Date());
    }





}
