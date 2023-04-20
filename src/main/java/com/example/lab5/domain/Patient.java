package com.example.lab5.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Patient implements Entity<Integer> , Serializable {
    public static final long serialVersionUID = 1L;
    private String name;

    private String phonenumber;

    private Integer id;
    private Date date;

    public Patient(String name, String phonenumber, Integer id, int year, int month, int day, int hour, int minute) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.id = id;
        Date Date1 = new Date();
        Date1.setYear(year);
        Date1.setMonth(month);
        Date1.setHours(hour);
        Date1.setDate(day);
        Date1.setMinutes(minute);
        this.date=Date1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(name, patient.name) && Objects.equals(phonenumber, patient.phonenumber) && Objects.equals(id, patient.id) && Objects.equals(date, patient.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phonenumber, id, date);
    }

    public Patient(String name, String phonenumber, Integer id, Date date) {
        this.name = name;
        this.phonenumber = phonenumber;
        this.id = id;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name+' '+phonenumber+' '+id+' '+date.getYear()+' '+date.getMonth()+' '+date.getDay()+' '+date.getHours()+' '+date.getMinutes();
    }
    public String toStringNormal() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", id=" + id +
                ", date=" + date +
                '}';
    }
}
