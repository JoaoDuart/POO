
<html>
<head>
  <meta charset="UTF-8">
  <title>Cafeteria Website</title>
  <link rel="stylesheet" href="style.css">
  <style>
.error {color: #FF0000;}
</style>
</head>
<body>
  <header>
    <h1>CAFETERIA ODAW</h1>
  </header>
  <nav>
    <a href="Menu.php">Home</a>
    <a href="Preços.php">Menu</a>
    <a href="Sobrenos.php">Sobre Nós</a>
    <a href="Cadastro.php">Cadastro</a>
    <a href="Bootstrap.php">Javascrip</a>
  </nav>
  <main>
  <div class="menu-item">
</div>
  <div class="menu-item">
    <h3>Teste PHP Validação </h3>
    <?php
$nameErr = $emailErr = $genderErr = $websiteErr = $commentErr = "";
$name = $email = $gender = $comment = $website = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $nameErr = "Insira seu nome";
  } else {
    $name = test_input($_POST["name"]);
    if (!preg_match("/^[a-zA-Z-' ]*$/",$name)) {
      $nameErr = "Somente letras e espaços";
    }
  }
  
  if (empty($_POST["email"])) {
    $emailErr = "Insira um email";
  } else {
    $email = test_input($_POST["email"]);
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
      $emailErr = "Email invalido";
    }
  }
    
  if (empty($_POST["website"])) {
    $website = "";
  } else {
    $website = test_input($_POST["website"]);
    $pattern = '/^[a-zA-Z0-9]{8,}?$/';
    if (!preg_match($pattern,$website)) {
      $websiteErr = "* Use uma senha somente com letras e numeros";
    }    
  }

  if (empty($_POST["comment"])) {
    $comment = "";
  } else {
    $comment = test_input($_POST["comment"]);
    $pattern1 = '/^([0-9]{3}\.?[0-9]{3}\.?[0-9]{3}\-?[0-9]{2})$/';
    if (!preg_match($pattern1,$comment)) {
      $commentErr = "* CPF Invalido";
    }    
  }

  if (empty($_POST["gender"])) {
    $genderErr = "Insira um Genero";
  } else {
    $gender = test_input($_POST["gender"]);
  }
}

function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>
<p><span class="error">* Campos obrigatorios</span></p>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">  
  Nome: <input type="text" name="name">
  <span class="error">* <?php echo $nameErr;?></span>
  <br><br>
  E-mail: <input type="text" name="email">
  <span class="error">* <?php echo $emailErr;?></span>
  <br><br>
  Senha: <input type="text" name="website">
  <span class="error"><?php echo $websiteErr;?></span>
  <br><br>
  CPF: <input type="text" name="comment">
  <span class="error"><?php echo $commentErr;?></span>
  <br><br>
  Genero:
  <br>
  <input type="radio" name="gender" value="Feminino">Feminino
  <input type="radio" name="gender" value="Masculino">Masculino
  <input type="radio" name="gender" value="Outros">Outros
  <br>
  <span class="error">* <?php echo $genderErr;?></span>
  <br><br>
  <input type="submit" name="submit" value="Submit">  
</form>


<?php

echo "<h2>Seu Cadastro:</h2>";
echo "Nome: " . $name;
echo "<br>";
echo "Email: " . $email;
echo "<br>";
echo "Senha: " . $website;
echo "<br>";
echo "CPF: " . $comment;
echo "<br>";
echo "Genero: " . $gender;
?>

  </div> 
  <div class="menu-item">
</div>
  </main>
  <footer>
    <p>2023 Cafeteria ODAW</p>
  </footer>
</body>
</html>