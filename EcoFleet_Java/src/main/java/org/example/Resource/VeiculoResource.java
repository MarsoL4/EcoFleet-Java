package org.example.Resource;

import org.example.BO.VeiculoBO;
import org.example.Model.Veiculo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Path("/veiculos")
public class VeiculoResource {
    private VeiculoBO veiculoBO = new VeiculoBO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarVeiculo(Veiculo veiculo) {
        try {
            // Validações dos campos
            if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O modelo do veículo não pode ser vazio.")
                        .build();
            }
            if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("A marca do veículo não pode ser vazia.")
                        .build();
            }
            if (veiculo.getConsumoEnergetico() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O consumo energético deve ser maior que zero.")
                        .build();
            }
            if (veiculo.getEmissaoCarbono() < 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("A emissão de carbono não pode ser negativa.")
                        .build();
            }
            if (veiculo.getIdUsuario() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("ID do usuário inválido.")
                        .build();
            }

            // Verifica se o usuário associado ao veículo existe (opcional, dependendo da lógica do BO)
            boolean usuarioExiste = veiculoBO.verificarUsuarioExistente(veiculo.getIdUsuario());
            if (!usuarioExiste) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Usuário com ID " + veiculo.getIdUsuario() + " não encontrado.")
                        .build();
            }

            // Cadastro do veículo
            veiculoBO.cadastrarVeiculo(veiculo);
            return Response.status(Response.Status.CREATED)
                    .entity("Veículo cadastrado com sucesso!")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao cadastrar veículo: " + e.getMessage())
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizarVeiculo(@PathParam("id") int id, Veiculo veiculo) {
        try {
            // Validações básicas
            if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O modelo do veículo não pode ser vazio.")
                        .build();
            }
            if (veiculo.getMarca() == null || veiculo.getMarca().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("A marca do veículo não pode ser vazia.")
                        .build();
            }
            if (veiculo.getConsumoEnergetico() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O consumo energético deve ser maior que zero.")
                        .build();
            }
            if (veiculo.getIdUsuario() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("ID do usuário inválido.")
                        .build();
            }

            // Verifica se o veículo existe
            Veiculo veiculoExistente = veiculoBO.buscarVeiculoPorId(id);
            if (veiculoExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Veículo com ID " + id + " não encontrado.")
                        .build();
            }

            // Atualiza o veículo
            veiculoBO.atualizarVeiculo(id, veiculo);
            return Response.status(Response.Status.OK)
                    .entity("Veículo atualizado com sucesso!")
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao atualizar veículo: " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/usuario/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculosPorUsuario(@PathParam("idUsuario") int idUsuario) {
        if (idUsuario <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID do usuário é inválido.")
                    .build();
        }
        try {
            List<Veiculo> veiculos = veiculoBO.listarVeiculosPorUsuario(idUsuario);
            if (veiculos.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Nenhum veículo encontrado para o usuário com ID " + idUsuario + ".")
                        .build();
            }
            return Response.ok(veiculos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao listar veículos: " + e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response excluirVeiculo(@PathParam("id") int id) {
        try {
            Veiculo veiculoExistente = veiculoBO.buscarVeiculoPorId(id);
            if (veiculoExistente == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("Veículo com ID " + id + " não encontrado.")
                        .build();
            }

            veiculoBO.excluirVeiculo(id);
            return Response.ok("Veículo excluído com sucesso!").build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro interno ao excluir veículo: " + e.getMessage())
                    .build();
        }
    }
}