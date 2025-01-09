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
  <main>
    <div class="menu-item">
    </div>
    <CENTER>
    <div class="menu-item">
    <h3>Cadastro</h3>
    <form method="post" action="processar_cadastro.php">
      <label for="nome">Nome:</label>
      <input type="text" name="nome" id="nome">
      <br>
      <label for="senha">Senha:</label>
      <input type="password" name="senha" id="senha">
      <br>
      <input type="submit" value="Cadastrar">
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