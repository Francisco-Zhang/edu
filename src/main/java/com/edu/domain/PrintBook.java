package com.edu.domain;

import javafx.scene.chart.PieChart;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PrintBook extends  Book {


    private Date printDate;  //不能设置非空到字段，因为ebook不会插入该字段，适用于子类和父类只有少量字段不同，大量字段是相同的

    public Date getPrintDate() {
        return printDate;
    }

    public void setPrintDate(Date printDate) {
        this.printDate = printDate;
    }
}
