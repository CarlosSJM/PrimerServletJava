/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.web;

import app.modelo.Cliente;
import app.modelo.DetallePedido;
import app.modelo.Pedido;
import app.modelo.Producto;
import app.negocio.Carrito;
import app.persistencia.TiendaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ploo
 */
public class ServletTienda extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ServletTienda</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ServletTienda at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        String op = request.getParameter("opcion");

        if (null != op) {
            switch (op) {
                case "1": { // pagina principal mostrar productos
                    List<Producto> lista = new TiendaDAO().listarProductos();
                    request.setAttribute("todos", lista);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                    break;
                } // fin mostrar productos
                case "2": { // mostrar carrito 
                    // 1- Recuperar o crear la session del usuario
                    HttpSession miSession = request.getSession();
                    // 2- Recuperar el carrito del usuario
                    Carrito miCarro = (Carrito) miSession.getAttribute("carrito");
                    // 3- Comprobar que realmente existe el carrito
                    if (miCarro == null) {
                        miCarro = new Carrito();
                        miSession.setAttribute("carrito", miCarro);
                    }
                    // 4- Recuperar y convertir el id del producto a comprar  
                    String indice = request.getParameter("id");
                    if (indice != null) { // puede ser que se entre sin seleccionar nada
                        int id = Integer.parseInt(request.getParameter("id"));
                        // 5- Añadimos el producto al carrito
                        miCarro.addProducto(id);
                    }
                    // 6- Elejir la vista que mostrará el resultado
                    RequestDispatcher rd = request.getRequestDispatcher("/mostrarCarrito.jsp");
                    // 7- Redirigir hacia esa vista
                    rd.forward(request, response);
                    break;
                } // fin mostrar carrito

                case "3": { // borrar carrito
                    // 1- Recuperar o crear la session del usuario
                    // false, si no existe la session NO LA CREA
                    HttpSession miSession = request.getSession(false);
                    // 2- Recuperar el carrito del usuario
                    Carrito miCarro = (Carrito) miSession.getAttribute("carrito");
                    // 3- Si se ha elegido para borrar significa que sí existe
                    // 4- Recuperar y convertir el id del producto a comprar
                    int id = Integer.parseInt(request.getParameter("id"));
                    // 5- Añadimos el producto al carrito
                    miCarro.sacarProducto(id);
                    // 6- Elejir la vista que mostrará el resultado
                    RequestDispatcher rd = request.getRequestDispatcher("/mostrarCarrito.jsp");
                    // 7- Redirigir hacia esa vista
                    rd.forward(request, response);
                    break;
                } // fin borrar carrito

                case "4": {  // comprobar login
                    String usuario = request.getParameter("usuario");
                    String contrasenia = request.getParameter("contrasenia");
                    String origen = request.getParameter("origen");
                    Cliente c = new TiendaDAO().buscarCliente(usuario);
                    //    Comprobamos que los valores sean los de la BBDD
                    if (null != c.getUsuario() && c.getUsuario().equals(usuario) && c.getPassword().equals(contrasenia)) {
                        // 1- Crear la session del usuario
                        HttpSession miSession = request.getSession();
                        // 2- guardar datos usuario en session
                        miSession.setAttribute("usuario", usuario);
                        miSession.setAttribute("contrasenia", contrasenia);
                        miSession.setAttribute("origen", origen);
                        // 3- guardar datos cliente en session también
                        miSession.setAttribute("cliente", c);
                        if ("login".equals(origen)) { // a index
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        } else { // viene de realizar compra
                            request.getRequestDispatcher("/finalizarCompra.jsp").forward(request, response);
                        }
                    } else {
                        // Guardamos en request un mensaje de error
                        request.setAttribute("mensaje", "El usuario y/o la contraseña no son correctos");
                        if ("login".equals(origen)) { // a login
                            request.getRequestDispatcher("/login.jsp").forward(request, response);
                        } else { // a realizar compra
                            request.getRequestDispatcher("/realizarCompra.jsp").forward(request, response);
                        }
                    }
                    break;
                } // fin comprobar login

                case "5": {  // registrarse
                    String nombre = request.getParameter("nombre");
                    String direccion = request.getParameter("direccion");
                    String poblacion = request.getParameter("poblacion");
                    String comunidad = request.getParameter("comunidad");
                    String cp = request.getParameter("cp");
                    String dni = request.getParameter("dni");
                    String usuario = request.getParameter("usuario");
                    String contrasenia = request.getParameter("contrasenia");

                    Cliente c = new Cliente(usuario, contrasenia, nombre, direccion, poblacion, comunidad, cp, dni);
                    int i = new TiendaDAO().crearCliente(c);//i nos devolvera resultado de si se ha creado
                    if (i == 0) { // no se ha podido insertar ( error o duplicado )
                        request.setAttribute("mensaje", "Este usuario ya existe.");
                        request.getRequestDispatcher("/altaCliente.jsp").forward(request, response);
                    } else {
                        // 1- Crear la session del usuario
                        HttpSession miSession = request.getSession();
                        // 2- guardar datos usuario en session
                        miSession.setAttribute("usuario", usuario);
                        miSession.setAttribute("contrasenia", contrasenia);
                        String origen = (String) miSession.getAttribute("origen");
                        // 3- guardar datos cliente en session
                        miSession.setAttribute("cliente", c);
                        if ("login".equals(origen)) { // a index
                            request.getRequestDispatcher("/index.jsp").forward(request, response);
                        } else { // a finalizarCompra
                            request.getRequestDispatcher("/finalizarCompra.jsp").forward(request, response);
                        }
                    }
                    break;
                } // fin registrarse

                case "6": {  // crear pedido
                    String pago = request.getParameter("pago");
                    String envio = request.getParameter("envio");
                    HttpSession miSession = request.getSession(false);
                    String usuario = (String) miSession.getAttribute("usuario");

                    Pedido pd = new Pedido(usuario, pago, envio);
                    int pedido = new TiendaDAO().crearPedido(pd);// nos devolvera el nro de pedido
                    if (pedido == 0) { // no se ha podido insertar ( error o duplicado )
                        request.setAttribute("mensaje", "Error al crear pedido");
                        request.getRequestDispatcher("/exitoCompra.jsp").forward(request, response);
                    } else { // crear detalle pedido
                        request.setAttribute("pedido", pedido);
                        Carrito miCarro = (Carrito) miSession.getAttribute("carrito");
                        int i = 0;
                        TiendaDAO detalle = new TiendaDAO();
                        for (Producto p : miCarro.getContenido()) {
                            i++;
                            DetallePedido d = new DetallePedido(pedido, i, p.getId_producto(), p.getDescripcion(), p.getPrecio());
                            detalle.crearDetalle(d);
                        }
                        // ya se ha realizado el pedido, se borra carrito
                        miSession.removeAttribute("carrito");
                        request.getRequestDispatcher("/exitoCompra.jsp").forward(request, response);
                    }
                    break;
                } // fin crear pedido

                case "7": { // consultar pedido    
                    HttpSession miSession = request.getSession();
                    String usuario = (String) miSession.getAttribute("usuario");
                    String dato = request.getParameter("pedido");
                    if (dato == null) { // puede ser que se entre sin seleccionar nada
                        request.setAttribute("mensaje", "Debe introducir un número de pedido");
                    } else {
                        int num = Integer.parseInt(request.getParameter("pedido"));
                        if (num == 0) {
                            request.setAttribute("mensaje", "Debe introducir un número de pedido");
                        } else {
                            Pedido p = new TiendaDAO().buscarPedido(num);
                            //    Comprobamos que el pedido sea del usuario
                            System.out.println(p.getId_pedido() + " - " + p.getUsuario() + " - " + usuario);
                            if ( 0 != p.getId_pedido() && p.getUsuario().equals(usuario) ) {
                                List<Producto> lista = new TiendaDAO().buscarDetalle(num);
                                // Cliente ya lo tengo en session
                                // Cliente c = new TiendaDAO().buscarCliente(p.getUsuario());
                                // creo carrito para pedidos
                                Carrito miCarro = new Carrito();
                                for (Producto p1 : lista) {
                                    miCarro.addProducto(p1.getId_producto());
                                }
                                // creo request
                                request.setAttribute("pedido", p);
                                request.setAttribute("productos", miCarro);
                                // request.setAttribute("cliente", c);
                            }else{
                                  // pedido no existe o es de otro usuario  
                                request.setAttribute("mensaje", "Pedido no existe / Es de otro usuario / Usted no está logado");  
                            }
                        }
                    }
                    request.getRequestDispatcher("/consultarPedido.jsp").forward(request, response);
                    break;
                } // fin consultar pedido

                case "8": {  // registrarse desde login
                    HttpSession miSession = request.getSession();
                    miSession.setAttribute("origen", "login");
                    request.getRequestDispatcher("/altaCliente.jsp").forward(request, response);
                    break;
                } // fin logarse

                case "9": { // deslogarse
                    HttpSession miSession = request.getSession();
                    miSession.invalidate();
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                    break;
                } // fin deslogarse

                default:
                    break;
            } // fin switch
        } // fin if

    } // fin processRequest

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

//        List<Product> products = productService.list();
//        request.setAttribute("products", products);
//        request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);   
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
