package model;

import javax.persistence.*;

/**
 * Created by ASUS on 03/03/2016.
 */
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(nullable = false, unique = true, length = 9)
    private String dni;

    @Column(nullable = false, unique = false, length = 31)
    private String name;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
