package com.edu.repository;

import com.edu.BaseTest;
import com.edu.domain.Book;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositoryTest extends BaseTest {

    @Autowired
    private  BookBaseRepository bookbaseRepository;

    @Autowired
    private BookCrudRepository bookCrudRepository;

    @Autowired
    private BookPagingAndSortingRepository bookPagingAndSortingRepository;

    @Autowired
    private  BookRepository bookRepository;   //最常用的是这个接口，继承上面三个接口的所有方法




    @Test
    public void test(){
        //分页
        Pageable pageable=new PageRequest(0,10,new Sort(Sort.Direction.ASC,"name","id"));

        Book book=new Book();
        book.setName("战争与和平");
        book.setCreatedTime(null);
        Example<Book> example=Example.of(book);     //局限性：不支持or、group by、between
        bookRepository.findAll(example,pageable);   //1.Example查询  精确查询

        ExampleMatcher exampleMatcher=ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);  //只针对字符串类型起作用
        Example<Book> example2=Example.of(book,exampleMatcher);
        bookRepository.findAll(example2,pageable);   //2.Example查询  使用匹配器中的模糊查询（匹配器中还包含其他查询方式）

    }



    @Test
    public void test1(){
        bookPagingAndSortingRepository.findAll(new Sort(Sort.Direction.ASC,"name","id"));

        bookPagingAndSortingRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"name"),
                new Sort.Order(Sort.Direction.ASC,"id")));      //多个排序

        bookPagingAndSortingRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC,"name"),
                new Sort.Order("id")));      //order中不指明排序方向则默认ASC升序
        //分页
        Pageable pageable=new PageRequest(0,10,new Sort(Sort.Direction.ASC,"name","id"));

        Page<Book> books= bookPagingAndSortingRepository.findAll(pageable);
        books.getContent();

    }


    @Test
    public  void  test2(){
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
    public void  test3(){

        //注入的是代理，只要是继承例Repository接口，都会生成代理，但是CurdRepository等有特殊注解NoBeans,所以不会为其生成代理
        System.out.println(bookbaseRepository.getClass().getName());
        bookbaseRepository.findByName("战争与和平");
        bookbaseRepository.findByCreatedTime(new Date());

        //通过接口方法声明生成sql
        bookRepository.findByNameAndCategoryName("战争与和平","aaa");

        bookRepository.findByNameLike("%战争与和平%");  //自己写 ％ 标明左右关联

        bookRepository.findByNameLikeOrderByNameDesc("%战争与和平"); // 排序

        bookRepository.findByNameLikeAndCategoryNameOrderByNameDesc("%战争与和平%","战争",new PageRequest(0,10));
        bookRepository.findBooks("%战争与和平%","战争",new PageRequest(0,10));

        long count= bookRepository.findBooksCount("%战争与和平%","战争");

        int r=bookRepository.updateBooks("aaa",1L);

    }
    @Test //动态查询
    public  void  test4(){
        Specification<Book> spec=new Specification<Book>() {
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 =criteriaBuilder.equal(root.get("name"),"战争与和平");
                Predicate p2 =criteriaBuilder.equal(root.get("category").get("name"),"战争");
                Predicate p3 =criteriaBuilder.and(p1,p2);   //组合模式，既是 实体 也是容器  and 相当于容器，组合两个 predicate

                root.fetch("category", JoinType.LEFT);  // 指定查询语句包含 category字段，以及使用左连接
                return p3;  // return p1;  也可以单独返回p1,都是Predicate对象
            }
        };
        bookRepository.findOne(spec);
    }

    @Test
    public  void  test5(){

        bookRepository.save(new Book());  // 调用了自定义的 Repostory中的save方法
    }



}
