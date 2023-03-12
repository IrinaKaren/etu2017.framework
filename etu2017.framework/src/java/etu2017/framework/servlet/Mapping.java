/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package etu2017.framework.servlet;


/**
 *
 * @author Johan
 */
public class Mapping {
    String name;
    String method;

    //Getters
    public String getName() {
        return name;
    }
     public String getMethod() {
        return method;
    }

     //Setters
    public void setMethod(String method) {
        this.method = method;
    }
    public void setName(String name) {
        this.name = name;
    }   
}
