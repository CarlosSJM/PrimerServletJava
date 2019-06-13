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
public class Pedido {
    private int id_pedido;
    private String usuario;
    private String pago;
    private String envio;
    
    ///////contructores/////////

    public Pedido() {}

    public Pedido(int id_pedido, String usuario, String pago, String envio) {
        this.id_pedido = id_pedido;
        this.usuario = usuario;
        this.pago = pago;
        this.envio = envio;
    }

    public Pedido(String usuario, String pago, String envio) {
        this.usuario = usuario;
        this.pago = pago;
        this.envio = envio;
    }


    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getEnvio() {
        return envio;
    }

    ///////getter & setter///////
    public void setEnvio(String envio) {
        this.envio = envio;
    }

    ///////method/////////

    @Override
    public String toString() {
        return "Pedido{" + "id_pedido=" + id_pedido + ", usuario=" + usuario + ", pago=" + pago + ", envio=" + envio + '}';
    }

}
