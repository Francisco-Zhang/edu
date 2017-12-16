package com.edu.repository;


import com.edu.domain.PrintBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.Date;
import java.util.List;

//JpaSpecificationExecutor是一个单独的接口，用于生成动态sql
public interface PrintBookRepository extends JpaRepository<PrintBook,Long>,JpaSpecificationExecutor<PrintBook> {


}
