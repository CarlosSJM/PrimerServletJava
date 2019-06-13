/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.negocio;

import app.modelo.Producto;
import app.persistencia.TiendaDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ploo
 */
public class Carrito implements Serializable {
    
    private double importe;
    private final List<Producto> contenido = new ArrayList<>();
    
    //////////////////////////////////

    public double getImporte() {
        return importe;
    }

    public List<Producto> getContenido() {
        return contenido;
    }
    
    //////////////////////////////////
    
    public void addProducto(int id){
        Producto p = new TiendaDAO().buscarProducto(id);
        contenido.add(p);
        importe += p.getPrecio();
    }
    
    public void sacarProducto(int id){
        Producto encontrado = null;
        
        for ( Producto p : contenido ){
            if ( p.getId_producto() == id ){
                encontrado = p;
                importe -= p.getPrecio();
            }
        }
        
        contenido.remove(encontrado);     
    }
    
}
