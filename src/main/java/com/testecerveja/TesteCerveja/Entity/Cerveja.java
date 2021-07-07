package com.testecerveja.TesteCerveja.Entity;

import javax.persistence.*;

@Entity
public class Cerveja {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String estilo;

    @Column(nullable = false)
    private int tempL;

    @Column(nullable = false)
    private int tempH;

    public Cerveja() {}

    public Cerveja(String estilo, int tempL, int tempH) {
        this.estilo = estilo;
        this.tempL = tempL;
        this.tempH = tempH;
    }

    public Cerveja(Integer id, String estilo, int tempL, int tempH) {
        this.id = id;
        this.estilo = estilo;
        this.tempL = tempL;
        this.tempH = tempH;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public int getTempL() {
        return tempL;
    }

    public void setTempL(int tempL) {
        this.tempL = tempL;
    }

    public int getTempH() {
        return tempH;
    }

    public void setTempH(int tempH) {
        this.tempH = tempH;
    }

    public double getMedia() {
        return (this.tempH + this.tempL) / 2;
    }
}
