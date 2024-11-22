package org.example.DAO;

import org.example.Model.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {

    public void cadastrarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO T_VEICULO (MODELO, MARCA, CONSUMO_ENERGETICO, EMISSAO_CARBONO, ID_USUARIO) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setDouble(3, veiculo.getConsumoEnergetico());
            stmt.setDouble(4, veiculo.getEmissaoCarbono());
            stmt.setInt(5, veiculo.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    public List<Veiculo> listarVeiculosPorUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM T_VEICULO WHERE ID_USUARIO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            List<Veiculo> veiculos = new ArrayList<>();
            while (rs.next()) {
                veiculos.add(new Veiculo(
                        rs.getInt("ID_VEICULO"),
                        rs.getString("MODELO"),
                        rs.getString("MARCA"),
                        rs.getDouble("CONSUMO_ENERGETICO"),
                        rs.getDouble("EMISSAO_CARBONO"),
                        rs.getInt("ID_USUARIO")
                ));
            }
            return veiculos;
        }
    }

    public double buscarConsumoEnergeticoPorId(int idVeiculo) throws SQLException {
        String sql = "SELECT CONSUMO_ENERGETICO FROM T_VEICULO WHERE ID_VEICULO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idVeiculo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("CONSUMO_ENERGETICO");
            }
            throw new SQLException("Veículo não encontrado com o ID fornecido.");
        }
    }

    public void atualizarVeiculo(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE T_VEICULO SET MODELO = ?, MARCA = ?, CONSUMO_ENERGETICO = ?, EMISSAO_CARBONO = ? WHERE ID_VEICULO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setDouble(3, veiculo.getConsumoEnergetico());
            stmt.setDouble(4, veiculo.getEmissaoCarbono());
            stmt.setInt(5, veiculo.getId());
            stmt.executeUpdate();
        }
    }

    public void excluirVeiculo(int id) throws SQLException {
        String sql = "DELETE FROM T_VEICULO WHERE ID_VEICULO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Veiculo buscarVeiculoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_VEICULO WHERE ID_VEICULO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Veiculo(
                        rs.getInt("ID_VEICULO"),
                        rs.getString("MODELO"),
                        rs.getString("MARCA"),
                        rs.getDouble("CONSUMO_ENERGETICO"),
                        rs.getDouble("EMISSAO_CARBONO"),
                        rs.getInt("ID_USUARIO")
                );
            }
            return null;
        }
    }

    public boolean verificarUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT 1 FROM T_USUARIO WHERE ID_USUARIO = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se o usuário existir
        }
    }
}