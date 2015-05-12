package com.zanox;

/**
 * Created by jsoler on 11.05.15.
 */

public class Employee {

    private Integer id;
    private String name;
    private String lastName;
    private String address;
    private Integer salary;

    public Employee(Integer id, String name, String lastName, String address, Integer salary) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer raiseSalary(Integer increase){
        this.salary = this.salary + increase;
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
