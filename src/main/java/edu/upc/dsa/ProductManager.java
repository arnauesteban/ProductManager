package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

import java.util.*;

public interface ProductManager {

    List<Producto> getListaProductos();
    void realizarPedido(Pedido pedido);
    void servirPedido();
    List<Pedido> getListaPedidosRealizados(String id);
    List<Producto> getListaProductosPorVentas();

    void anadirProducto(Producto producto);
    void anadirUsuario(Usuario usuario);
    HashMap<String, Usuario> getUsuarios();
    Queue<Pedido> getColaPedidosPendientes();
    int getNumeroPedidos();
    int getNumVentas();
    int getNumProductos();
    List<Producto> getListaProductosPorPrecio();
}
