package org.example.BO;

import org.example.DAO.VeiculoDAO;
import org.example.Model.Veiculo;

import java.sql.SQLException;
import java.util.List;

public class VeiculoBO {
    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new IllegalArgumentException("Modelo do veículo não pode ser nulo ou vazio.");
        }
        if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
            throw new IllegalArgumentException("Marca do veículo não pode ser nula ou vazia.");
        }
        if (veiculo.getConsumoEnergetico() <= 0) {
            throw new IllegalArgumentException("Consumo energético deve ser maior que zero.");
        }
        if (veiculo.getEmissaoCarbono() < 0) {
            throw new IllegalArgumentException("Emissão de carbono não pode ser negativa.");
        }
        try {
            veiculoDAO.cadastrarVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar veículo", e);
        }
    }

    public List<Veiculo> listarVeiculosPorUsuario(int idUsuario) {
        try {
            return veiculoDAO.listarVeiculosPorUsuario(idUsuario);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veículos por usuário", e);
        }
    }

    public double buscarConsumoEnergeticoPorId(int idVeiculo) {
        try {
            return veiculoDAO.buscarConsumoEnergeticoPorId(idVeiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consumo energético do veículo", e);
        }
    }

    public void atualizarVeiculo(int id, Veiculo veiculo) {
        try {
            veiculoDAO.atualizarVeiculo(veiculo);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veículo", e);
        }
    }

    public void excluirVeiculo(int id) {
        try {
            veiculoDAO.excluirVeiculo(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veículo", e);
        }
    }

    public Veiculo buscarVeiculoPorId(int id) {
        try {
            return veiculoDAO.buscarVeiculoPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veículo com ID: " + id, e);
        }
    }

    public boolean verificarUsuarioExistente(int idUsuario) {
        try {
            // Chama o método de busca no DAO para verificar o usuário
            return veiculoDAO.verificarUsuarioPorId(idUsuario);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar existência do usuário com ID: " + idUsuario, e);
        }
    }


}