package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

import java.util.List;

public interface ProductManager {

    List<Producto> getListaProductos();
    void realizarPedido(Pedido pedido);
    void servirPedido();
    List<Pedido> getListaPedidosRealizados(String id);
    List<Producto> getListaProductosPorVentas();
}
