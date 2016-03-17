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

    public Car() {
    }

    public Car(String marca, String patente) {
        this.marca = marca;
        this.patente = patente;
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
}
