package com.boss.design.structuralModel.compositePattern;

/**
 * 2、树叶构件（Leaf）角色：
 */
public class Employee implements Person {
    private String name;
    private String dept;
    private int salary;

    public Employee(String name, String dept, int salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

    @Override
    public void describeEmployees() {
        System.out.println("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary + " ]");
    }

    @Override
    public double countMonthlyIncome() {
        return salary;
    }
}
