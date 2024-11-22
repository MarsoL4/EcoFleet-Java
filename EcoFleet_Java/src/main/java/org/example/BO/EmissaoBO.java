package org.example.BO;

import org.example.Model.Emissao;

public class EmissaoBO {
    public double calcularEmissao(double consumoEnergetico, double fatorEmissao) {
        if (consumoEnergetico <= 0) {
            throw new IllegalArgumentException("Consumo energético deve ser maior que zero.");
        }
        if (fatorEmissao <= 0) {
            throw new IllegalArgumentException("Fator de emissão deve ser maior que zero.");
        }
        return consumoEnergetico * fatorEmissao;
    }

    public Emissao gerarEmissao(int id, double consumoEnergetico, double fatorEmissao) {
        double emissaoTotal = calcularEmissao(consumoEnergetico, fatorEmissao);
        return new Emissao(id, emissaoTotal);
    }
}