<?php
include("conexao.php");
$cod_cliente=$_GET['cod_cliente'];
$sql = mysqli_query($con, "DELETE FROM `cliente` where `cod_cliente`='".$cod_cliente."'");
?>
