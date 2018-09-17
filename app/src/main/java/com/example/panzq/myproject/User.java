package com.example.panzq.myproject;

import net.tsz.afinal.annotation.sqlite.Table;

import java.util.Date;

@Table(name = "user")//@Table 表示orm(对象关系映射)的表名
public class User {
    private int id;
    private String name;
    private String email;
    private Date registerDate;
    private Double money;
    //get、set 方法


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
