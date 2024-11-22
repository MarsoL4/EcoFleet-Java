package org.example.Resource;

import org.example.BO.UsuarioBO;
import org.example.Model.Usuario;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/usuarios")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuario) {
        try {
            if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
                throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
            }
            if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
                throw new IllegalArgumentException("O email do usuário não pode ser vazio.");
            }
            if (!usuario.getEmail().contains("@")) {
                throw new IllegalArgumentException("Formato de email inválido.");
            }
            if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
                throw new IllegalArgumentException("A senha do usuário não pode ser vazia.");
            }

            usuarioBO.cadastrarUsuario(usuario);
            return Response.status(Response.Status.CREATED)
                    .entity("Usuário cadastrado com sucesso!")
                    .build();

        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Erro de validação: " + e.getMessage())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao cadastrar usuário: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuario(@PathParam("email") String email) {
        if (email == null || !email.contains("@")) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("E-mail inválido.")
                    .build();
        }
        Usuario usuario = usuarioBO.buscarUsuarioPorEmail(email);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(usuario).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        List<Usuario> usuarios = usuarioBO.listarUsuarios();
        return Response.ok(usuarios).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") int id, Usuario usuario) {
        try {
            Usuario usuarioExistente = usuarioBO.buscarUsuarioPorId(id);
            if (usuarioExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário não encontrado com o ID: " + id)
                        .build();
            }

            // Atualiza os dados
            usuario.setId(id);
            usuarioBO.atualizarUsuario(usuario);

            return Response.ok(usuario).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao atualizar usuário: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluirUsuario(@PathParam("id") int id) {
        try {
            usuarioBO.excluirUsuario(id);
            return Response.ok("Usuário excluído com sucesso!").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir usuário: " + e.getMessage())
                    .build();
        }
    }

}