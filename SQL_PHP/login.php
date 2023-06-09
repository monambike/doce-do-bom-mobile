<?php
include("conexao.php");
$email=$_GET['email'];
$senha=$_GET['senha'];
$sql = mysqli_query($con, "SELECT * FROM `cliente` where `email`='".$email."' and `senha`='".$senha."'");
$linhas = mysqli_num_rows($sql);
while($linhas = mysqli_fetch_array($sql)){
  echo $linhas["cod_cliente"].":".
  $linhas["nome"].":".
  $linhas["senha"].":".
  $linhas["email"].":".
  $linhas["cpf"].":".
  $linhas["foto"];
}
?>
