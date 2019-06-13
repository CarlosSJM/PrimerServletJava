/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.modelo;

/**
 *
 * @author Ploo
 */
public class DetallePedido {

private int id_pedido;
private int id_item;  
private int id_producto;  
private String Descripcion;
private double precio;

////////constructores////////

    public DetallePedido() {
    }

    public DetallePedido(int id_pedido, int id_item, int id_producto, String Descripcion, double precio) {
        this.id_pedido = id_pedido;
        this.id_item = id_item;
        this.id_producto = id_producto;
        this.Descripcion = Descripcion;
        this.precio = precio;
    }

///////////////getter & setter///////////////

    public int getId_pedido() {
        return id_pedido;
    }

    public int getId_item() {
        return id_item;
    }

    public int getId_producto() {
        return id_producto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /////////method////////

    @Override
    public String toString() {
        return "DetallePedido{" + "id_pedido=" + id_pedido + ", id_item=" + id_item + ", id_producto=" + id_producto + ", Descripcion=" + Descripcion + ", precio=" + precio + '}';
    }
    
}
