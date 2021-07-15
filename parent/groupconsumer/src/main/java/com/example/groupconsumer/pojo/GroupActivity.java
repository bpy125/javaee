package com.example.groupconsumer.pojo;

import java.util.Date;
import java.util.List;

public class GroupActivity {
    Integer id;
    String name;
    Double basicPay;
    String address;

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getDue() {
        return due;
    }

    public void setDue(Integer due) {
        this.due = due;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    String content;
    String date;
    Integer days;
    Integer due;
    String leader;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    Integer number;
    List<Tourist> list;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getBasicPay() {
        return basicPay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBasicPay(Double basicPay) {
        this.basicPay = basicPay;
    }
    public List<Tourist> getList() {
        return list;
    }
    public void setList(List<Tourist> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GroupActivity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basicPay=" + basicPay +
                ", address='" + address + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", list=" + list +
                '}';
    }
}
