package model;

import javax.persistence.*;

/**
 * Created by ASUS on 17/03/2016.
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false, unique = true, length = 7)
    private String id;

    @ManyToOne
    @JoinColumn(name = "client_dni", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_patente", nullable = false)
    private Car car;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
