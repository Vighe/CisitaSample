package it.framo.cisitasample2020.models;

import java.util.Date;

public class User extends Object {
    public
        String name = "";
        String surname = "";
        Date dateRegistration;
        Boolean visible = true;
        int age=0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String _name, String _surname, Date _date, int _age, Boolean _visible){

                }
}
