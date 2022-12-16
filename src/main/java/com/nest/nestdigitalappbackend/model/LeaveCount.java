package com.nest.nestdigitalappbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leavecount")
public class LeaveCount {

    @Id
    @GeneratedValue
    private int id;
    private int empId;
    private String year;
    private int sickLeave;
    private int casualLeave;
    private int specialLeave;

    public LeaveCount() {
    }

    public LeaveCount(int id, int empId, String year, int sickLeave, int casualLeave, int specialLeave) {
        this.id = id;
        this.empId = empId;
        this.year = year;
        this.sickLeave = sickLeave;
        this.casualLeave = casualLeave;
        this.specialLeave = specialLeave;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(int sickLeave) {
        this.sickLeave = sickLeave;
    }

    public int getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(int casualLeave) {
        this.casualLeave = casualLeave;
    }

    public int getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(int specialLeave) {
        this.specialLeave = specialLeave;
    }
}
