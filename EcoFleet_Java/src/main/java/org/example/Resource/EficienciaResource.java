package org.example.Resource;

import org.example.BO.EficienciaBO;
import org.example.Model.Eficiencia;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/eficiencia")
public class EficienciaResource {
    private EficienciaBO eficienciaBO = new EficienciaBO();

    @GET
    @Path("/{idVeiculo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response calcularEficiencia(
            @PathParam("idVeiculo") int idVeiculo,
            @QueryParam("distancia") double distanciaPercorrida,
            @QueryParam("energia") double energiaConsumida) {
        if (idVeiculo <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("ID do veículo é inválido.")
                    .build();
        }
        if (distanciaPercorrida <= 0 || energiaConsumida <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Distância e energia consumida devem ser maiores que zero.")
                    .build();
        }
        try {
            Eficiencia eficiencia = eficienciaBO.gerarEficiencia(idVeiculo, distanciaPercorrida, energiaConsumida);
            return Response.ok(eficiencia).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao calcular eficiência: " + e.getMessage())
                    .build();
        }
    }
}