<%-- 
    Document   : login
    Created on : 19-feb-2019, 18:59:10
    Author     : Ploo
--%>

<%@page import="app.modelo.Pedido"%>
<%@page import="app.modelo.Cliente"%>
<%@page import="app.modelo.Producto"%>
<%@page import="app.negocio.Carrito"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar Pedidos</title>
        <link rel="stylesheet" type="text/css" href="CSS/tienda2.css"
    </head>
    <body>
        <h1 class="header">Tienda Práctica</h1>
        <%
            String bienvenida = "";
            if (session.getAttribute("usuario") != null) {
                bienvenida = "Usuario actual: " + (String) session.getAttribute("usuario");
            }

            Pedido pd = (Pedido) request.getAttribute("pedido");
            Carrito miCarro = (Carrito) request.getAttribute("productos");
            Cliente c = (Cliente) session.getAttribute("cliente");
            if (pd == null) {
                pd = new Pedido();
                miCarro = new Carrito();
                c = new Cliente();
            }
        %>
        <ul>
            <li><a href="index.jsp">INICIO</a></li>
            <li><a href="servlet?opcion=2">VER COMPRA</a></li>
            <li><a class="active" href="consultarPedido.jsp">CONSULTAR PEDIDO</a></li>
            <li><a href="login.jsp">LOGIN</a></li>
            <li><a href="servlet?opcion=9">DESLOGARSE</a></li>
        </ul>
        <h1>Consultar Pedidos</h1>

        <p><%=bienvenida%></p>

        <form action="servlet" method="POST">
            <input type="hidden" name="opcion" value="7" />
            Pedido: <input type="text" name="pedido" pattern="[0-9]{1,}" value="<%= pd.getId_pedido()%>" 
                      required title="Debe introducir un valor numérico" />
            <input type="submit" name="login" value="CONSULTAR" />
        </form>
            
        <%
            String mensaje = "";
            if (request.getAttribute("mensaje") != null) {
                mensaje = (String) request.getAttribute("mensaje");
            }
        %>
        <p><%=mensaje%></p>
        <fieldset style="float: left; width: 30%;">
            <legend> Datos del Cliente </legend>
            Nombre: <input type="text" name="nombre" value="<%= c.getNombre()%>" readonly /><br><br>
            Dirección: <input type="text" name="direccion" value="<%= c.getDireccion()%>" readonly /><br><br>
            Población: <input type="text" name="poblacion" value="<%= c.getPoblacion()%>" readonly /><br><br>
            Comunidad <input type="text" name="comunidad" value="<%= c.getComunidad()%>" readonly /><br><br>
            C.P. : <input type="text" name="cp" value="<%= c.getCp()%>" readonly /><br><br>
            DNI: <input type="text" name="dni" value="<%= c.getDni()%>" readonly />
        </fieldset>
        <fieldset>
            <legend> Datos del Pedido </legend>
            Forma de Pago: <input type="text" name="pago" value="<%= pd.getPago()%>" readonly /><br><br>
            Tipo de Envío: <input type="text" name="envio" value="<%= pd.getEnvio()%>" readonly /><br><br>
        </fieldset>
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
                    </tr>
                </thead>

                <% for (Producto p : miCarro.getContenido()) {%>

                <tbody>
                    <tr>
                        <td><%= p.getId_producto()%></td>
                        <td><%= p.getDescripcion()%></td>
                        <td><%= p.getPrecio()%></td>
                    </tr>
                </tbody>
                <% }%>
            </table>
        </fieldset>
        <br> 

        <script>
            if ( document.getElementsByTagName('input')[1].value === "0" ) {
                fields = document.getElementsByTagName('fieldset');
                for (var i = 0; i < fields.length; i++) {
                    fields[i].style.visibility = "hidden";
                }
            }
        </script>
    </body>
</html>
