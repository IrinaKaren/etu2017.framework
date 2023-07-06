/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2017.framework.servlet;
import  etu2017.framework.annotation.Url;
/**
 *
 * @author Johan
 */
public class Employee {
    private String name;
    private String prenom;

    public String getName() {
        return name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Url("Emp_name")
    public String printName() {
        return "Employé name = " + name;
    }

    @Url("Emp_surname")
    public String printSurname() {
        return "Employé surname = " + prenom;
    }
}

