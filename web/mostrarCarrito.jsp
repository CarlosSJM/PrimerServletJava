<%-- 
    Document   : mostrarTodos
    Created on : 11-feb-2019, 20:40:44
    Author     : Ploo
--%>

<%@page import="app.negocio.Carrito"%>
<%@page import="java.util.List"%>
<%@page import="app.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Carrito</title>
        <link rel="stylesheet" type="text/css" href="CSS/tienda2.css">
    </head>
    <body>
        <h1 class="header">Tienda Práctica</h1>
        <ul>
            <li><a href="index.jsp">INICIO</a></li>
            <li><a class="active" href="servlet?opcion=2">VER COMPRA</a></li>
            <li><a href="consultarPedido.jsp">CONSULTAR PEDIDO</a></li>
            <li><a href="login.jsp">LOGIN</a></li>
            <li><a href="servlet?opcion=9">DESLOGARSE</a></li>
        </ul>

        <h1 >Mostrar Carrito</h1>

        <% Carrito miCarro = (Carrito) session.getAttribute("carrito");
       
            String bienvenida = "";
   
         if (session.getAttribute("usuario") != null) {
                bienvenida = "Usuario actual: " + (String) session.getAttribute("usuario");
            }
        %>
        <p><%=bienvenida%></p>
    <center>
        Importe total: <%= miCarro.getImporte()%>     
    </center>
    <br><br>
    <table border="1" >
        <thead>
            <tr>
                <th>ID</th>
                <th>DESCRIPCION</th>
                <th>PRECIO</th>
                <th>IMAGEN</th>
                <th>Eliminar producto</th>
            </tr>
        </thead>
        <% for (Producto p : miCarro.getContenido()) {%>
        <tbody>
            <tr>
                <td><%= p.getId_producto()%></td>
                <td><%= p.getDescripcion()%></td>
                <td><%= p.getPrecio()%></td>
                <td><img src="<%= p.getImagen()%>" ></td>
                <td>
                    <a href="servlet?opcion=3&id=<%= p.getId_producto()%>">
                        <img src="IMG/eliminar.png"  ></a>
                </td>
            </tr>
        </tbody>
        <% }%>
    </table>
    <br><br>  
    <!-- <button onclick="location.href = 'login.jsp'" type="button"> -->
    <button onclick="location.href = 'realizarCompra.jsp'" type="button">
        COMPRAR</button>

</body>
</html>
