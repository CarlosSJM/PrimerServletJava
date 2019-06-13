<%-- 
    Document   : index
    Created on : 18-feb-2019, 19:50:54
    Author     : Ploo
--%>

<%@page import="java.util.List"%>
<%@page import="app.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda Práctica</title>
        <link rel="stylesheet" type="text/css" href="CSS/tienda2.css">
    </head>
    <body>
        <h1 class="header">Tienda Práctica</h1>
        <%
            String bienvenida = "";

            if (session.getAttribute("usuario") != null) {
                bienvenida = "Usuario actual: " + (String) session.getAttribute("usuario");
            }
        %>

        <ul>
            <li><a class="active" href="index.jsp">INICIO</a></li>
            <li><a href="servlet?opcion=2">VER COMPRA</a></li>
            <li><a href="consultarPedido.jsp">CONSULTAR PEDIDO</a></li>
            <li><a href="login.jsp">LOGIN</a></li>
            <li><a href="servlet?opcion=9">CERRAR SESION</a></li>
        </ul>

        <h1>Lista de Productos</h1>
        <p><%=bienvenida%></p>

        <%
            List<Producto> lista
                    //                = (List<Producto>) request.getAttribute("todos"); // request
                    = (List<Producto>) request.getAttribute("todosRequest"); // Listener Request
            //              = (List<Producto>) application.getAttribute("todosApp"); // Listener Application
        %>
        <table border="1" >
            <thead>
                <tr>
                    <th>ID</th>
                    <th>DESCRIPCION</th>
                    <th>PRECIO</th>
                    <th>IMAGEN</th>
                    <th>Comprar Producto</th>
                </tr>
            </thead>
            <% for (Producto p : lista) {%>
            <tbody>
                <tr>
                    <td><%= p.getId_producto()%></td>
                    <td><%= p.getDescripcion()%></td>
                    <td><%= p.getPrecio()%></td>
                    <td><img src="<%= p.getImagen()%>" ></td>
                    <td>
                        <a href="servlet?opcion=2&id=<%= p.getId_producto()%>">
                            <img src="IMG/carrito.png" ></a>
                    </td>
                </tr>
            </tbody>
            <% }%>
        </table>

    </body>
</html>
