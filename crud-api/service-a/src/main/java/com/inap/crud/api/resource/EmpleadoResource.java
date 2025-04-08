package com.inap.crud.api.resource;

import com.inap.crud.api.model.Empleado;
import com.inap.crud.api.model.EmpleadoDAO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;

import java.util.List;

@Path("/empleados")
public class EmpleadoResource {

    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed(name = "empleados-get-timer", description = "Tiempo de ejecución para obtener empleados")
    @Counted(name = "empleados-get-count", description = "Número de veces que se ha llamado al endpoint GET empleados")
    public Response getEmpleados() {
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();
        if (empleados.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron empleados").build();
        }
        return Response.ok(empleados).build(); // Retorna la lista de empleados como JSON
    }

    // Endpoint para agregar un nuevo empleado
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Counted(name = "empleados-post-count", description = "Número de veces que se ha llamado al endpoint POST empleados")
    public Response addEmpleado(Empleado empleado) {
        if (empleadoDAO.agregarEmpleado(empleado)) {
            return Response.status(Response.Status.CREATED).entity(empleado).build(); // Retorna el empleado creado
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al agregar el empleado").build();
        }
    }

    // Endpoint para actualizar un empleado existente
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmpleado(Empleado empleado) {
        if (empleadoDAO.actualizarEmpleado(empleado)) {
            return Response.ok(empleado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error al actualizar el empleado").build();
        }
    }

    // Endpoint para eliminar un empleado
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmpleado(@PathParam("id") int id) {
        if (empleadoDAO.eliminarEmpleado(id)) {
            return Response.status(Response.Status.NO_CONTENT).build(); // Retorna no content en caso de éxito
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Empleado no encontrado").build();
        }
    }
}
