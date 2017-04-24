<?php
//Información y credenciales de base de datos
$server = "localhost";
$user = "root";
$pass = "";
$database = "example_database";
//Conexión a la base de datos
$conection = mysqli_connect($server, $user, $pass, $database);
if (!$conection) {
    echo 'Ha sucedido un error inexperado en la conexion de la base de datos<br>';
}
//Consulta que se va a ejecutar
$query = 'SELECT * FROM users_list;';
if (!$result = mysqli_query($conection, $query))
    die();
$rawdata = array();
$i = 0;
//Se empaca la información obtenida de la consulta en un Array
while ($row = mysqli_fetch_assoc($result)) {
    $rawdata[$i] = array_map('utf8_encode', $row);
    $i++;
}
if (!mysqli_close($conection)) {
    echo 'Ha sucedido un error inexperado en la desconexion de la base de datos<br>';
}
//Se codifica el array en formato JSON
echo json_encode($rawdata);
?>