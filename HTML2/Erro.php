<html>
<head>
  <meta charset="UTF-8">
  <title>Cafeteria Website</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
  <header>
    <h1>CAFETERIA ODAW</h1>
  </header>
  <nav>
    <a href="Menu.php">Home</a>
  </nav>
  <main>
    <div class="menu-item">
    </div>
    <CENTER>
    <div class="menu-item">
    <h3>Login </h3>
    <form method="post" action="validar_login.php">
    <label for="username">ID usuário:</label>
    <input type="text" name="username" id="username">
    <br>
    <label for="password">Senha:</label>
    <input type="password" name="password" id="password">
    <br>
    <input type="submit" value="Entrar">
    <p style="color: red;"> * Erro no cadastro </p> 
</form>
    </CENTER>
    <div class="menu-item">
    </div>
  </main>
  <footer>
    <p>2023 Cafeteria ODAW</p>
  </footer>
</body>
</html>