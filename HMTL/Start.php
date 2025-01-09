<html>
<head>
  <meta charset="UTF-8">
  <title>Cafeteria Website</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <header>
    <h1>InfoUdesc</h1>
  </header>
  <main>
    <div class="menu-item">
    </div>
    <CENTER>
    <div class="menu-item">
    <h3>Login </h3>
    <center>
    <a href="cadastroLogin.php">Cadastro</a>
</center>
    <form method="post" action="validar_login.php">
    <label for="username">ID usuário:</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">Senha:</label>
    <input type="password" name="password" id="password">
    <br>
    <input type="submit" value="Entrar">
</form>
    </CENTER>
    <div class="menu-item">
    </div>
  </main>
  <footer>
    <p>2023 InfoUdesc</p>
  </footer>
</body>
</html>