package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

/**
 * Created by ASUS on 03/03/2016.
 */
@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(nullable = false, unique = true, length = 9)
    private String dni;
    private Blob photo;
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

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "" + name + ", dni: " + dni;
    }
}
