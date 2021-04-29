package edu.upc.dsa.services;


import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/bar")
@Path("/bar")
public class BarService {

    //ProductManagerImpl o interfaz ProductManager ????
    private ProductManager pm;

    public BarService() {
        this.pm = ProductManagerImpl.getInstance();
        if (pm.getNumProductos()==0) {

            pm.anadirProducto(new Producto("Fanta", 1, 10));
            pm.anadirProducto(new Producto("Café", 0.5F, 50));
            pm.anadirProducto(new Producto("Platano", 0.65F, 15));

        }
    }

    @GET
    @ApiOperation(value = "get lista de productos ordenado por precio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosByPrecio() {

        List<Producto> productos = this.pm.getListaProductosPorPrecio();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(201).entity(entity).build();
    }


    @POST
    @ApiOperation(value = "realizar un pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Pedido.class),

    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newPedido(Pedido pedido) {
        this.pm.realizarPedido(pedido);
        return Response.status(201).entity(pedido).build();
    }


    @PUT
    @ApiOperation(value = "servir un pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
    })
    @Path("/")
    public Response servirPedido() {
        pm.servirPedido();
        return Response.status(201).build();
    }


    @GET
    @ApiOperation(value = "get lista de pedidos de un usuario", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Pedido.class),
            @ApiResponse(code = 404, message = "No hay pedidos")
    })
    @Path("/pedidos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidos(@PathParam("id") String id) {

        List<Pedido> lista = pm.getListaPedidosRealizados(id);

        if(lista.size() == 0)
            return Response.status(404).build();

        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(lista) {};
        return Response.status(201).entity(entity).build();
    }

    @GET
    @ApiOperation(value = "get lista de productos ordenado por ventas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class)
    })
    @Path("/productos/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosByVentas() {

        List<Producto> lista = pm.getListaProductosPorVentas();
        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(lista) {};
        return Response.status(201).entity(entity).build();
    }
}
