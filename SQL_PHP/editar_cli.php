<?php
  include("conexao.php");
  $cod_cliente=$_GET['cod_cliente'];
  $nome = $_GET['nome'];
  $email = $_GET['email'];
  $senha = $_GET['senha'];
	mysqli_query($con, "UPDATE `cliente` SET `nome`='".$nome."',`email`='".$email."',`senha`='".$senha."' WHERE `cod_cliente`='".$cod_cliente."'");
?>
