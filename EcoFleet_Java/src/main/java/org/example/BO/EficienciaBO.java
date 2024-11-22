package org.example.BO;

import org.example.Model.Eficiencia;

public class EficienciaBO {
    public double calcularEficiencia(double distanciaPercorrida, double energiaConsumida) {
        if (energiaConsumida <= 0) {
            throw new IllegalArgumentException("Energia consumida deve ser maior que zero.");
        }
        if (distanciaPercorrida <= 0) {
            throw new IllegalArgumentException("Distância percorrida deve ser maior que zero.");
        }
        return distanciaPercorrida / energiaConsumida;
    }

    public Eficiencia gerarEficiencia(int id, double distanciaPercorrida, double energiaConsumida) {
        double eficiencia = calcularEficiencia(distanciaPercorrida, energiaConsumida);
        return new Eficiencia(id, eficiencia);
    }
}