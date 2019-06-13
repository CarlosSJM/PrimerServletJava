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
        <title>Finalizar Compra</title>
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
        <h1>Finalizar Compra</h1>
        <%
            String bienvenida = "";

            if (session.getAttribute("usuario") != null) {
                bienvenida = "Usuario actual: " + (String) session.getAttribute("usuario");
            }
            Carrito miCarro = (Carrito) session.getAttribute("carrito");
            Cliente c = (Cliente) session.getAttribute("cliente");
        %>
        <p><%=bienvenida%></p>
        <fieldset>
            <legend> Datos del Carrito </legend>            
            <center>
                Importe total: <%= miCarro.getImporte()%>     
            </center>
            <table border="1" align="center">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>DESCRIPCION</th>
                        <th>PRECIO</th>
                        <th>IMAGEN</th>
                    </tr>
                </thead>
                <% for (Producto p : miCarro.getContenido()) {%>
                <tbody>
                    <tr>
                        <td><%= p.getId_producto()%></td>
                        <td><%= p.getDescripcion()%></td>
                        <td><%= p.getPrecio()%></td>
                        <td><img src="<%= p.getImagen()%>" ></td>
                    </tr>
                </tbody>
                <% }%>
            </table>
        </fieldset>
        <br>    
        <fieldset style="float: left; width: 40%;">
            <legend> Datos del Cliente </legend>
            Nombre: <input type="text" name="nombre" value="<%= c.getNombre()%>" readonly /><br><br>
            Dirección: <input type="text" name="direccion" value="<%= c.getDireccion()%>" readonly /><br><br>
            Población: <input type="text" name="poblacion" value="<%= c.getPoblacion()%>" readonly /><br><br>
            Comunidad <input type="text" name="comunidad" value="<%= c.getComunidad()%>" readonly /><br><br>
            C.P. : <input type="text" name="cp" value="<%= c.getCp()%>" readonly /><br><br>
            DNI: <input type="text" name="dni" value="<%= c.getDni()%>" readonly /><br><br><br><br>
        </fieldset>
        <fieldset style="float: left; width: 40%;">
            <legend> Datos del Pedido </legend>
            <form action="servlet" method="POST">
                <input type="hidden" name="opcion" value="6" />
                Forma de Pago: 
                <input type="radio" name="pago" value="Contrareembolso" checked="checked" >Contrareembolso<br>
                <input type="radio" name="pago" value="Paypal">Paypal<br><br><br>
                Tipo de Envío: 
                <input type="radio" name="envio" value="Domicilio" checked="checked">Domicilio<br>
                <input type="radio" name="envio" value="Tienda">Tienda<br><br><br>
                <br>  
                <input type="submit" value="FINALIZAR COMPRA" />
            </form>
        </fieldset>

    </body>
</html>
