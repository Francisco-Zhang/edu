package com.edu.repository;

import com.edu.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.List;

//JpaSpecificationExecutor是一个单独的接口，用于生成动态sql
public interface BookRepository extends JpaRepository<Book,Long>,JpaSpecificationExecutor<Book> {

    //@EntityGraph(attributePaths = {"category"})
    @EntityGraph("Book.fetch.category.and.author")  //使用这种方式，复用性更高，如果增加抓取属性author,只需修改Book的抓取策略，所有的方法都会变
    Book findByName(String name);

    List<Book>  findByCreatedTime(Date date);

    List<Book>  findByNameAndCategoryName(String bookName,String categoryName);

    //还包括 Or、Between、LessThan、LessThanEqual、GreaterThan、IsNull、NotNull等关键字
    List<Book> findByNameLike(String name);

    List<Book> findByNameLikeOrderByNameDesc(String name);


    Page<Book> findByNameLike(String name, Pageable sort);  // 分页查询


    // 缺点：1.只能声明查询
    //      2.复杂的查询方法名会很长   针对这两个问题，可以使用@Query注解解决

    Page<Book> findByNameLikeAndCategoryNameOrderByNameDesc(String bookName,String categoryName,Pageable sort);

    //@Query(" from Book b  where b.name like ?1 and b.category.name=?2 order by b.name desc")
    //和上面注解结果相同，明确指明sql中使用连接
    //@Query(" from Book b  left join b.category c  where b.name like ?1 and c.name=?2 order by b.name desc")
    //只查询 book 中属性字段
    @Query(" select b from Book b  left join b.category c  where b.name like ?1 and c.name=?2 order by b.name desc")
    Page<Book> findBooks(String bookName,String categoryName,Pageable sort);

    //注解还可以写update,delete,count等
    @Query(" select count(b.id) from Book b  left join b.category c  where b.name like ?1 and c.name=?2 order by b.name desc")
    Long findBooksCount(String bookName,String categoryName);

    //@Query(" update Book b  set b.name= ?1 where b.id=?2 ")
    @Query(value = " update Book b  set b.name= ?1 where b.id=?2 ",nativeQuery = true)
    @Modifying                                  //更新语句需要多加个Modifying注解
    int updateBooks(String bookName,Long bookId);

}
