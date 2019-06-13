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
        <title>Crear Cliente</title>
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
        <h1>Formulario Cliente</h1>
        <form class="login" name="f1" action="servlet" method="POST" onsubmit="return comprobar()">
            <input type="hidden" name="opcion" value="5" />
            Usuario: <input type="text" name="usuario" maxlength="15" required
                            title="Introduzca un máximo de 15 caracteres"
                            placeholder="usuario para login"
                            > <br><br>
            Contraseña: <input type="password" name="contrasenia" maxlength="15" required
                            title="Introduzca un máximo de 15 caracteres"
                            placeholder="contraseña para login"
                               /><br><br><br>
            Nombre: <input type="text" name="nombre" required /><br><br>
            Dirección: <input type="text" name="direccion" required="required" /><br><br>
            Población: <select name="poblacion"><br><br>
                <option>Ávila</option>
                <option>Cádiz</option>
                <option>Ciudad Real</option>
                <option>Cuenca</option>
                <option>Guadalajara</option>
                <option>Madrid</option>
                <option>Santander</option>
                <option>Segovia</option>
                <option>Toledo</option>               
                <option>Teruel</option>               
            </select><br><br>
            Comunidad <select name="comunidad">
                <option>Madrid</option>
                <option>Castilla la Mancha</option>
                <option>Castilla-León</option>
                <option>Cantabria</option>
                <option>Aragón</option>              
            </select><br><br>
            C.P. : <input type="text" name="cp" required pattern="[0-9]{5}" 
                          title="Debe introducir 5 dígitos"/><br><br>
            DNI: <input type="text" name="dni" required pattern="[0-9]{8}[A-Za-z]{1}" 
                        title="Debe introducir 8 dígitos seguidos de una letra"/><br><br><br><br>

            <input type="submit" value="REGISTRAR" />
        </form>   
        <%
            String mensaje = "";
            if (request.getAttribute("mensaje") != null) {
                mensaje = (String) request.getAttribute("mensaje");
            }
        %>
        <p><%=mensaje%></p>
        <script>
            function comprobar() {
                var dni = document.getElementsByName('dni')[0].value;
                var num = dni.substring(0,8);
                var letra = dni.substring(8,9).toUpperCase();
                var array = ['T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X',
                    'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E', 'T'];
                var pos = num % 23;
                if (letra === array[pos]) {
                    return true;
                } else {
                    alert('La letra NO es correcta !!');
                    return false;
                }

            }
        </script>
    </body>
</html>
