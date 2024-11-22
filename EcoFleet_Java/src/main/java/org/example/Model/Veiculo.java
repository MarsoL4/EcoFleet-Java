package org.example.Model;

public class Veiculo {
    private int id;
    private String modelo;
    private String marca;
    private double consumoEnergetico;
    private double emissaoCarbono;
    private int idUsuario; // Novo campo para vincular o veículo ao usuário

    public Veiculo(int id, String modelo, String marca, double consumoEnergetico, double emissaoCarbono, int idUsuario) {
        if (modelo == null || modelo.isEmpty()) {
            throw new IllegalArgumentException("Modelo não pode ser nulo ou vazio.");
        }
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser nula ou vazia.");
        }
        if (consumoEnergetico <= 0) {
            throw new IllegalArgumentException("Consumo energético deve ser maior que zero.");
        }
        if (emissaoCarbono < 0) {
            throw new IllegalArgumentException("Emissão de carbono não pode ser negativa.");
        }
        if (idUsuario <= 0) {
            throw new IllegalArgumentException("ID do usuário deve ser maior que zero.");
        }
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.consumoEnergetico = consumoEnergetico;
        this.emissaoCarbono = emissaoCarbono;
        this.idUsuario = idUsuario;
    }

    public Veiculo() {}

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public double getConsumoEnergetico() { return consumoEnergetico; }
    public void setConsumoEnergetico(double consumoEnergetico) { this.consumoEnergetico = consumoEnergetico; }
    public double getEmissaoCarbono() { return emissaoCarbono; }
    public void setEmissaoCarbono(double emissaoCarbono) { this.emissaoCarbono = emissaoCarbono; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
}