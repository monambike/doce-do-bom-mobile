<?php
include("conexao.php");
$sql = mysqli_query($con, "SELECT `senha`,`email` FROM `cliente`");
$linhas = mysqli_num_rows($sql);
while($linhas = mysqli_fetch_array($sql)){
  echo $linhas["senha"].":".
  $linhas["email"].";";
}
?>
