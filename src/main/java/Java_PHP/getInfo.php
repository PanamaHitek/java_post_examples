<?php
$server = "localhost";
$usuario = "root";
$pass = "";
$BD = "example_database";
$conection = mysqli_connect($server, $usuario, $pass, $BD);
if (!$conection) {
    echo 'Ha sucedido un error inexperado en la conexion de la base de datos<br>';
}
$sql = 'SELECT * FROM users_list;';
if (!$result = mysqli_query($conection, $sql))
    die();
$rawdata = array();
$i = 0;
while ($row = mysqli_fetch_assoc($result)) {
    $rawdata[$i] = array_map('utf8_encode', $row);
    $i++;
}
$close = mysqli_close($conection);
if (!$close) {
    echo 'Ha sucedido un error inexperado en la desconexion de la base de datos<br>';
}
echo json_encode($rawdata);
?>