package com.eduexcellence.feesms.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="fees_data")
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String month;
    private String year;
    private Double feeAmount;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
