package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ASUS on 17/03/2016.
 */
@Entity
@Table(name = "prestamo")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "client_dni", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "car_patente", nullable = false)
    private Car car;

    private boolean active;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date start;

    //@Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }
}
