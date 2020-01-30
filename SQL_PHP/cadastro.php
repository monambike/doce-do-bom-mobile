<?php
  include("conexao.php");
  $nome = $_GET['nome'];
  $email = $_GET['email'];
  $senha = $_GET['senha'];
  $cpf = $_GET['cpf'];
  $foto = $_GET['foto'];
	mysqli_query($con, "INSERT INTO `cliente`(`nome`,`senha`,`email`,`cpf`,`foto`)
  VALUES ('".$nome."','".$senha."','".$email."','".$cpf."','".$foto."')");
?>
