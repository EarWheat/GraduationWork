package com.confucius.pojo;

import javax.persistence.*;

@Table(name = "user")
public class IrisInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_size")
    private Double firstSize;

    @Column(name = "second_size")
    private Double secondSize;

    @Column(name = "third_size")
    private Double thirdSize;

    @Column(name = "fourth_size")
    private Double fourthSize;

    @Column(name = "category")
    private Double category;

    @Column(name = "create_time")
    private String createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getFirstSize() {
        return firstSize;
    }

    public void setFirstSize(Double firstSize) {
        this.firstSize = firstSize;
    }

    public Double getSecondSize() {
        return secondSize;
    }

    public void setSecondSize(Double secondSize) {
        this.secondSize = secondSize;
    }

    public Double getThirdSize() {
        return thirdSize;
    }

    public void setThirdSize(Double thirdSize) {
        this.thirdSize = thirdSize;
    }

    public Double getFourthSize() {
        return fourthSize;
    }

    public void setFourthSize(Double fourthSize) {
        this.fourthSize = fourthSize;
    }

    public Double getCategory() {
        return category;
    }

    public void setCategory(Double category) {
        this.category = category;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
