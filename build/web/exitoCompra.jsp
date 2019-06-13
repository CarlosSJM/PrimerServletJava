<%-- 
    Document   : login
    Created on : 19-feb-2019, 18:59:10
    Author     : Ploo
--%>

<%@page import="app.modelo.Cliente"%>
<%@page import="app.modelo.Producto"%>
<%@page import="app.negocio.Carrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exito Compra</title>
        <link rel="stylesheet" type="text/css" href="CSS/tienda2.css">
    </head>
    <a href="finalizarCompra.jsp"></a>
    <body>
        <h1 class="header">Tienda Práctica</h1>
        <ul>
            <li><a href="index.jsp">INICIO</a></li>
            <li><a class="active" href="servlet?opcion=2">VER COMPRA</a></li>
            <li><a href="consultarPedido.jsp">CONSULTAR PEDIDO</a></li>
            <li><a href="login.jsp">LOGIN</a></li>
            <li><a href="servlet?opcion=9">DESLOGARSE</a></li>
        </ul>
        <h1>Exito Compra</h1>

        <%
            String bienvenida = "";

            if (session.getAttribute("usuario") != null) {
                bienvenida = "Usuario actual: " + (String) session.getAttribute("usuario");
            }
            int pedido = (Integer) request.getAttribute("pedido");
        %>
        <p><%=bienvenida%></p>
        <fieldset>
            <legend> Resultado de la Compra </legend>
            <br><br><br>        

            <h4>La compra se ha realizado con éxito</h4>
            <br><br>
            Su número de pedido es: 
            <input type="text" name="pedido" value="<%= pedido%>" readonly="readonly" />

            <button onclick="location.href = 'consultarPedido.jsp'" type="button">
                CONSULTAR PEDIDO</button>

        </fieldset>
        <br>    


    </body>
</html>
