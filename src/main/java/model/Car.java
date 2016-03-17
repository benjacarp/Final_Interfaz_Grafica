package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

/**
 * Created by ASUS on 16/03/2016.
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @Column(nullable = false, unique = true, length = 7)
    private String patente;
    private String marca;
    private Blob photo;
    private boolean available;

    public Car() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "patente='" + patente + '\'' +
                ", marca='" + marca + '\'' +
                ", available=" + available +
                '}';
    }
}
