package org.example.BO;

import org.example.DAO.UsuarioDAO;
import org.example.Model.Usuario;

import java.sql.SQLException;
import java.util.List;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void cadastrarUsuario(Usuario usuario) {
        if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
            throw new IllegalArgumentException("E-mail não pode ser nulo ou vazio.");
        }
        if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia.");
        }
        try {
            Usuario existente = usuarioDAO.buscarUsuarioPorEmail(usuario.getEmail());
            if (existente != null) {
                throw new IllegalArgumentException("E-mail já cadastrado.");
            }
            usuarioDAO.cadastrarUsuario(usuario);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar usuário", e);
        }
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        try {
            return usuarioDAO.buscarUsuarioPorEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
    }

    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.listarUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar usuários", e);
        }
    }

    public void atualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO.atualizarUsuario(usuario);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário", e);
        }
    }

    public void excluirUsuario(int id) {
        try {
            usuarioDAO.excluirUsuario(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir usuário", e);
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        try {
            return usuarioDAO.buscarUsuarioPorId(id);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário pelo ID", e);
        }
    }

}