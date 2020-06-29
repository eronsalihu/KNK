package Controllers;

import java.util.Date;

public class WorkersTable {
    String name,surname,roli;
    int id;
    Date data;

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

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public WorkersTable(int id, String name, String surname, String roli, Date data) {
        this.id = id;
        this.name=name;
        this.surname=surname;
        this.roli=roli;
        this.data=data;
    }

}
