package org.example.Model;

public class Emissao {
    private int id;
    private double emissaoTotal;

    public Emissao(int id, double emissaoTotal) {
        this.id = id;
        this.emissaoTotal = emissaoTotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getEmissaoTotal() { return emissaoTotal; }
    public void setEmissaoTotal(double emissaoTotal) { this.emissaoTotal = emissaoTotal; }
}

