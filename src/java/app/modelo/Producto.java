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
public class Producto {
    private int id_producto;
    private String descripcion;
    private double precio;
    private String imagen;

    public Producto() {}

    public Producto(int id_producto, String descripcion, double precio, String imagen) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    public Producto(int id_producto, String descripcion, double precio) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio = precio;
    }
    
    
    
    ///////////////////////////////////

    public int getId_producto() {
        return id_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    ///////////////////////////

    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", descripcion=" + descripcion + ", precio=" + precio + ", imagen=" + imagen + '}';
    }
    
    
}
