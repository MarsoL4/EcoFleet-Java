package org.example.Model;

public class Eficiencia {
    private int id;
    private double eficiencia;

    public Eficiencia(int id, double eficiencia) {
        this.id = id;
        this.eficiencia = eficiencia;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getEficiencia() { return eficiencia; }
    public void setEficiencia(double eficiencia) { this.eficiencia = eficiencia; }
}

