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
public class Cliente {

    private String usuario;
    private String password;
    private String nombre;
    private String direccion;
    private String poblacion;
    private String comunidad;
    private String cp;
    private String dni;

//////contructores//////
    public Cliente() { }

    public Cliente(String usuario, String password, String nombre, String direccion, String poblacion, String comunidad, String cp, String dni) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.direccion = direccion;
        this.poblacion = poblacion;
        this.comunidad = comunidad;
        this.cp = cp;
        this.dni = dni;
    }


    ////////getter & setter////////

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Cliente{" + "usuario=" + usuario + ", password=" + password + ", nombre=" + nombre + ", direccion=" + direccion + ", poblacion=" + poblacion + ", comunidad=" + comunidad + ", cp=" + cp + ", dni=" + dni + '}';
    }


    
}
