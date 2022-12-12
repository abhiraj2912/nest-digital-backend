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
    private String sickLeave;
    private String casualLeave;
    private String specialLeave;

    public LeaveCount() {
    }

    public LeaveCount(int id, int empId, String year, String sickLeave, String casualLeave, String specialLeave) {
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

    public String getSickLeave() {
        return sickLeave;
    }

    public void setSickLeave(String sickLeave) {
        this.sickLeave = sickLeave;
    }

    public String getCasualLeave() {
        return casualLeave;
    }

    public void setCasualLeave(String casualLeave) {
        this.casualLeave = casualLeave;
    }

    public String getSpecialLeave() {
        return specialLeave;
    }

    public void setSpecialLeave(String specialLeave) {
        this.specialLeave = specialLeave;
    }
}
