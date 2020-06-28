package Controllers;

public class deleteTable {
    String name,surname,dataFillimit,dataMbarimit;
    int id;

    public deleteTable(int id,String name,String surname,String dataFillimit,String dataMbarimit) {
        this.id = id;
        this.name=name;
        this.surname=surname;
        this.dataFillimit=dataFillimit;
        this.dataMbarimit=dataMbarimit;
    }

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

    public String getDataFillimit() {
        return dataFillimit;
    }

    public void setDataFillimit(String dataFillimit) {
        this.dataFillimit = dataFillimit;
    }

    public String getDataMbarimit() {
        return dataMbarimit;
    }

    public void setDataMbarimit(String dataMbarimit) {
        this.dataMbarimit = dataMbarimit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
