<?php
$username = $_POST['username'];
$password = $_POST['password'];
$autenticacao = file('autenticacao.txt');
foreach ($autenticacao as $linha) {
    list($user, $pass) = explode(':', $linha);
    if (trim($username) === trim($user) && trim($password) === trim($pass)) {
        header('Location: Menu.php');
        exit;
    }
}
header('Location: Erro.php');
?>