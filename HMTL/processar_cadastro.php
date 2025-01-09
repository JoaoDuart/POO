<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
  $username = $_POST['nome'];
  $password = $_POST['senha'];
  $arquivo = fopen("autenticacao.txt", "a");
  fwrite($arquivo, $username . ":" . $password . "\n");
  fclose($arquivo);
  header("Location: Start.php");
  exit;
}
?>