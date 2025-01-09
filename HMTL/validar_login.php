<?php
$username = $_POST['username'];
$password = $_POST['password'];
$autenticacao = file('autenticacao.txt');
foreach ($autenticacao as $linha) {
    list($user, $pass) = explode(':', $linha);
    if (trim($username) === trim($user) && trim($password) === trim($pass)) {
        header('Location: Main.html');
        exit;
    }
}
header('Location: Erro.php');
?>