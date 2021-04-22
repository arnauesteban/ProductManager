package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager{
    private HashMap<String, Usuario> usuarios;
    private List<Producto> listaProductos;
    private Queue<Pedido> colaPedidosPendientes;
    private int numVentas;

    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private static ProductManagerImpl instance;

    private ProductManagerImpl()
    {
        listaProductos = new ArrayList<Producto>();
        usuarios = new HashMap<String, Usuario>();
        colaPedidosPendientes = new LinkedList<Pedido>();
        numVentas = 0;
    }

    public static ProductManagerImpl getInstance()
    {
        if(instance == null)
            instance = new ProductManagerImpl();

        return instance;

    }

    public void anadirProducto(Producto producto)
    {
        logger.info("Peticion: agregar producto " + producto);
        logger.info("Lista de productos ANTES de agregar: " + listaProductos);
        this.listaProductos.add(producto);
        logger.info("Lista de productos DESPUÉS de agregar: " + listaProductos);
    }

    public void anadirUsuario(Usuario usuario)
    {
        logger.info("Peticion: agregar usuario " + usuario);
        logger.info("Lista de usuarios ANTES de agregar: " + usuarios);
        this.usuarios.put(usuario.getID(), usuario);
        logger.info("Lista de usuarios DESPUES de agregar: " + usuarios);
    }

    public int getNumeroPedidos(){

        logger.info("Peticion getNumeroPedidos() => " + this.colaPedidosPendientes.size());
        return this.colaPedidosPendientes.size();
    }

    //lista ordenada por precios ASC
    public List<Producto> getListaProductosPorPrecio() {
        logger.info("Peticion getListaProductosPorPrecio()");
        logger.info("Lista de productos sin ordenar: " + listaProductos);
        Collections.sort(this.listaProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return (int)p1.getPrecio() - (int)p2.getPrecio();
            }
        });
        logger.info("Lista de productos ordenada por precio: " + listaProductos);
        return listaProductos;
    }

    public List<Producto> getListaProductos()
    {
        logger.info("Peticion getListaProductos() => " + listaProductos);
        return listaProductos;
    }

    public Queue<Pedido> getColaPedidosPendientes()
    {
        logger.info("Peticion getColaPedidosPendientes() => " + colaPedidosPendientes);
        return this.colaPedidosPendientes;
    }

    public HashMap<String, Usuario> getUsuarios()
    {
        logger.info("Peticion getUsuarios() => " + usuarios);
        return this.usuarios;
    }

    public void realizarPedido(Pedido pedido) {
        logger.info("Peticion: realizar pedido " + pedido);
        logger.info("Cola de pedidos ANTES de agregar: " + colaPedidosPendientes);
        logger.info("Lista de pedidos del cliente ANTES de agregar: " + pedido.getUsuario().getListaRealizados());
        colaPedidosPendientes.add(pedido);
        Usuario cliente = pedido.getUsuario();
        cliente.getListaRealizados().add(pedido);
        logger.info("Cola de pedidos DESPUES de agregar: " + colaPedidosPendientes);
        logger.info("Lista de pedidos del cliente DESPUES de agregar: " + pedido.getUsuario().getListaRealizados());
    }

    public void servirPedido() {
        logger.info("Peticion: servir pedido ");
        logger.info("Cola de pedidos ANTES de servir: " + colaPedidosPendientes);
        logger.info("Lista de productos ANTES de servir: " + listaProductos);
        Pedido servido = colaPedidosPendientes.poll();
        if(servido == null)
        {
            logger.warn("Aviso: No hay pedidos a servir.");
        }
        else for(int i = 0; i < servido.getListaProductos().size(); i++) {
            int j = 0;
            boolean encontrado = false;
            while(!encontrado && j < listaProductos.size()) {
                if(listaProductos.get(j).getNombre().equals(servido.getListaProductos().get(i).getNombre())) {
                    encontrado = true;
                    listaProductos.get(j).setCantidad(listaProductos.get(j).getCantidad() - servido.getListaProductos().get(i).getCantidad());
                    numVentas += servido.getListaProductos().get(i).getCantidad();
                }
                j++;
            }
        }
        logger.info("Cola de pedidos DESPUES de servir: " + colaPedidosPendientes);
        logger.info("Lista de productos DESPUES de servir: " + listaProductos);
    }

    public List<Pedido> getListaPedidosRealizados(String id)
    {
        logger.info("Peticion: getListaPedidosRealizados(" + id + ")");
        return usuarios.get(id).getListaRealizados();
    }

    public List<Producto> getListaProductosPorVentas() {
        logger.info("Peticion: getListaProductosPorVentas()");
        logger.info("Lista de productos ANTES de ordenar: " + listaProductos);
        List<Producto> lista = this.listaProductos;
        Collections.sort(lista, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getVendidos() - p2.getVendidos();
            }
        });
        logger.info("Lista de productos DESPUES de ordenar: " + listaProductos);
        return lista;
    }

    public int getNumVentas()
    {
        logger.info("Peticion: getNumVentas() => " + numVentas);
        return numVentas;
    }

    public int getNumProductos()
    {
        logger.info("Peticion: getNumProductos() => " + listaProductos.size());
        return listaProductos.size();
    }
}
