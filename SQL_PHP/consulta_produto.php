<?php
include ("conexao.php");
$sql = mysqli_query($con, "SELECT * FROM `produto`");
$linhas = mysqli_num_rows($sql);
while($linhas = mysqli_fetch_array($sql)){
  echo $linhas["cdProd"].":".
  $linhas["cdOperador"].":".
  $linhas["nome"].":".
  $linhas["quantidade"].":".
  $linhas["vlUnit"].":".
  $linhas["descricao"].";";
}
?>