package org.example.Resource;

import org.example.BO.EmissaoBO;
import org.example.BO.VeiculoBO;
import org.example.Model.Emissao;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/emissoes")
public class EmissaoResource {
    private EmissaoBO emissaoBO = new EmissaoBO();
    private VeiculoBO veiculoBO = new VeiculoBO();

    @GET
    @Path("/{idVeiculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calcularEmissao(
            @PathParam("idVeiculo") int idVeiculo,
            @QueryParam("fatorEmissao") double fatorEmissao) {
        if (idVeiculo <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID do veículo é inválido.")
                    .build();
        }
        if (fatorEmissao <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Fator de emissão deve ser maior que zero.")
                    .build();
        }
        try {
            double consumoEnergetico = veiculoBO.buscarConsumoEnergeticoPorId(idVeiculo); // Implementação assumida
            Emissao emissao = emissaoBO.gerarEmissao(idVeiculo, consumoEnergetico, fatorEmissao);
            return Response.ok(emissao).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao calcular emissão: " + e.getMessage())
                    .build();
        }
    }
}
