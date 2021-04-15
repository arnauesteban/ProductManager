package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;

import java.util.*;

public class ProductManagerImpl implements ProductManager{
    private HashMap<String, Usuario> usuarios;
    private List<Producto> listaProductos;
    private Queue<Pedido> colaPedidosPendientes;
    private int numVentas;

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
        this.listaProductos.add(producto);
    }

    public void anadirUsuario(Usuario usuario)
    {
        this.usuarios.put(usuario.getID(), usuario);
    }

    public int getNumeroPedidos(){ return this.colaPedidosPendientes.size(); }

    //lista ordenada por precios ASC
    public List<Producto> getListaProductosPorPrecio() {

        Collections.sort(this.listaProductos, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return (int)p1.getPrecio() - (int)p2.getPrecio();
            }
        });
        return listaProductos;
    }

    public List<Producto> getListaProductos()
    {
        return listaProductos;
    }

    public Queue<Pedido> getColaPedidosPendientes()
    {
        return this.colaPedidosPendientes;
    }

    public HashMap<String, Usuario> getUsuarios()
    {
        return this.usuarios;
    }

    public void realizarPedido(Pedido pedido) {
        colaPedidosPendientes.add(pedido);
        Usuario cliente = pedido.getUsuario();
        cliente.getListaRealizados().add(pedido);
    }

    public void servirPedido() {
        Pedido servido = colaPedidosPendientes.poll();
        for(int i = 0; i < servido.getListaProductos().size(); i++) {
            int j = 0;
            Boolean encontrado = false;
            while(!encontrado && j < listaProductos.size()) {
                if(listaProductos.get(j).getNombre() == servido.getListaProductos().get(i).getNombre()) {
                    encontrado = true;
                    listaProductos.get(j).setCantidad(listaProductos.get(j).getCantidad() - servido.getListaProductos().get(i).getCantidad());
                    numVentas += servido.getListaProductos().get(i).getCantidad();
                }
                j++;
            }
        }
    }

    public List<Pedido> getListaPedidosRealizados(String id) {
        return usuarios.get(id).getListaRealizados();
    }

    public List<Producto> getListaProductosPorVentas() {
        List<Producto> lista = this.listaProductos;
        Collections.sort(lista, new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getVendidos() - p2.getVendidos();
            }
        });
        return lista;
    }

    public int getNumVentas() {
        return numVentas;
    }

    public int getNumProductos() { return listaProductos.size(); }
}
