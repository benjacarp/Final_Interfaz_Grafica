package model;

/**
 * Created by ASUS on 03/03/2016.
 */
public class Client {

    private String name;

    public Client() {
    }

    public Client(String name, long dni) {
        this.name = name;
        this.dni = dni;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    private long dni;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
