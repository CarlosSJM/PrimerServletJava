
<%-- 
    Document   : login
    Created on : 19-feb-2019, 18:59:10
    Author     : Ploo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="CSS/tienda2.css">
    </head>
    <body>
        <h1 class="header">Tienda Práctica</h1>
        <ul>
            <li><a href="index.jsp">INICIO</a></li>
            <li><a href="servlet?opcion=2">VER COMPRA</a></li>
            <li><a href="consultarPedido.jsp">CONSULTAR PEDIDO</a></li>
            <li><a class="active" href="login.jsp">LOGIN</a></li>
            <li><a href="servlet?opcion=9">DESLOGARSE</a></li>
        </ul>

        <h1>Formulario Login</h1>
        <form class="login" name="login" action="servlet" method="POST">
            <input type="hidden" name="opcion" value="4" />
            <input type="hidden" name="origen" value="login" />
            Usuario: <input type="text" name="usuario" value="" /><br><br>
            Contraseña: <input type="password" name="contrasenia" value="" /><br><br>

            <input type="submit" name="registro" value="REGISTRARSE"
                   formaction="servlet?opcion=8"/>
                   <!-- onclick="document.login.action='servlet?opcion=8'"/> -->
            <input type="submit" name="login" value="LOGIN" />
        </form>   
        <%
            String mensaje = "";
            if (request.getAttribute("mensaje") != null) {
                mensaje = (String) request.getAttribute("mensaje");
            }
        %>
        <p><%=mensaje%></p>
    </body>
</html>
