<?php
$cookie_name = "ODAW1";
$cookie_value = "Pedro";
setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
?>
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
      
      <h3>Alterar fonte</h3>
      <style>
        body {
          font-family: Arial, sans-serif;
          font-size: 16px;
          line-height: 1.5;
        }
        form {
          border: 1px solid #ccc;
          padding: 20px;
          max-width: 400px;
          margin: 0 auto;
        }
        label {
          display: inline-block;
          margin-bottom: 10px;
        }
        input[type="text"] {
          display: block;
          width: 100%;
          padding: 8px;
          border: 1px solid #ccc;
          border-radius: 4px;
          margin-bottom: 20px;
        }
        input[type="checkbox"] {
          margin-right: 10px;
        }
      </style>
    </head>
    <body>
      <form name="TCheck">
        <label for="muda">Muda Case</label>
        <input type="text" id="muda" name="Muda">
        <br><br>
        <label>
          <input type="checkbox" name="Opt1" value="1" checked 
                 onclick="if (this.checked) { AltMinusc() } ">
          Minúsculo
        </label>
        <label>
          <input type="checkbox" name="Opt2" value="2" 
                 onclick="if (this.checked) { AltMaiusc() } ">
          Maiúsculo
        </label>
        <br><br>
        <label>
          <input type="checkbox" name="Opt4" value="4" 
                 onclick="if (this.checked) { document.TCheck.Muda.style.fontStyle = 'italic' } 
                          else { document.TCheck.Muda.style.fontStyle = 'normal' }">
          Itálico
        </label>
        <label>
          <input type="checkbox" name="Opt5" value="5" 
                 onclick="if (this.checked) { document.TCheck.Muda.style.fontWeight = 'bold' } 
                          else { document.TCheck.Muda.style.fontWeight = 'normal' }">
          Negrito
        </label>
        <br><br>
        <label>
          <input type="checkbox" name="Opt3" 
                 onclick="if (Opt3.checked) {alert ('Server receberá = ' + Opt3.value) } ">
          Mandar pro Server
        </label>
      </form>
      <script>
        function AltMaiusc () { 
          document.TCheck.Muda.value = document.TCheck.Muda.value.toUpperCase()     
          document.TCheck.Opt1.checked = false 
        } 
        function AltMinusc () { 
          document.TCheck.Muda.value = document.TCheck.Muda.value.toLowerCase()    
          document.TCheck.Opt2.checked = false 
        }
      </script> 
    </div>
    <div class="menu-item">
      <h3>Formulário</h3>
      <script> 

        function TestaVal() { 
        if (document.TesteSub.Teste.value == ""|| document.TesteSub.Teste2.value == "" || document.TesteSub.Teste3.value == "" || document.TesteSub.Teste4.value == "") { 
            alert ("Todos os campos não estão Preenchidos...Form nao Submetido") 
            return false } 
        else { 
            alert ("Tudo Ok....Form Submetido") 
            return true } } 
        
        </script> 
        
        <p> 
        <form method="POST" name="TesteSub" onSubmit="return TestaVal()" action="teste.html"> 
      
  
        Nome:
        <input type=text size=10 maxlength=10 name="Teste" value="">
        Email:
        <input type=text size=10 maxlength=10 name="Teste2" value="">
        Telefone: 
        <input type=text size=10 maxlength=10 name="Teste3" value="">
        Idade: 
        <input type=text size=10 maxlength=10 name="Teste4" value=""> 
        Botao Submit 
        <input type=submit name="Bsub" value="Manda p/Server"> 
        </p> 
        </form>
    </div>
    <div class="menu-item">
    <h3>Nome e Data</h3>
    <script>
      var nome = prompt("Qual é o seu nome?");
      
      var agora = new Date();
      var hora = agora.getHours();
      var diaDaSemana = agora.toLocaleDateString("pt-BR", { weekday: 'long' });
      var diaMesAno = agora.toLocaleDateString("pt-BR");
      
      var saudacao;
      if (hora >= 6 && hora < 12) {
        saudacao = "Bom dia";
      } else if (hora >= 12 && hora < 18) {
        saudacao = "Boa tarde";
      } else {
        saudacao = "Boa noite";
      }
      
      var mensagem = saudacao + ", " + nome + ", hoje é " + diaDaSemana + ", " + diaMesAno + ".";
      document.write(mensagem);
    </script>
    </div>
    <br>
    <div class="menu-item">
      <h3>Soma de dois Números</h3>
    <label>Insira o primeiro número:</label>
    <input type="number" id="numero1"><br>
    <label>Insira o segundo número:</label>
    <input type="number" id="numero2"><br>
    <button onclick="soma()">Calcular Soma</button><br><br>
    <p id="resultado"></p>

    <script>
      function soma() {
        var num1 = document.getElementById("numero1").value;
        var num2 = document.getElementById("numero2").value;
        var resultado = parseInt(num1) + parseInt(num2);
        document.getElementById("resultado").innerHTML = "A soma de " + num1 + " e " + num2 + " é igual a " + resultado;
      }
    </script>
      </div>
    <div class="menu-item">
        <h3>Cores de Fundo</h3> 
<p>         
 <input type=radio name="Rad" value="1" onclick="document.bgColor='green'"> Fundo Verde 
 <br>
 <input type=radio name="Rad" value="2" onclick="document.bgColor='blueviolet'"> Fundo Violeta 
 <br>
 <input type=radio name="Rad" value="3" onclick="document.bgColor='#FFFF00'"> Fundo Amarelo 
</p>
        </div>
    <div class="menu-item">
          <h3>Selecão de sites</h3>
      <select id="selectLink" onchange="abrirLink()">
        <option value="https://www.google.com">Google</option>
        <option value="https://www.instagram.com">Instagram</option>
        <option value="https://www.link3.com">Link 3</option>
        <option value="https://www.link4.com" selected>Link 4 (recomendado)</option>
        <option value="https://www.link5.com">Link 5</option>
        <option value="https://www.link6.com">Link 6</option>
      </select>
  
      <script>
        function abrirLink() {
          var select = document.getElementById("selectLink");
          var link = select.options[select.selectedIndex].value;
          window.location.href = link;
        }
      </script>
            </div>
    <div class="menu-item">
      <h3>Fechar Site com Aviso</h3>
      <p>Clique no botão abaixo para fechar o site:</p>
      <button onclick="confirmarFechamento()">Fechar Site</button>
  
      <script>
        function confirmarFechamento() {
          if (confirm("Tem certeza que deseja fechar o site?")) {
            window.open("", "_self");
            window.close();
          }
        }
      </script>
              </div>
    <div class="menu-item">
      <h3>Conversor de cm para m e km</h3>
      <p>Insira a medida em centímetros:</p>
      <input type="number" id="cmInput">
      <br><br>
      <button onclick="converter()">Converter</button>
      <br><br>
      <p>Resultado:</p>
      <p id="mOutput"></p>
      <p id="kmOutput"></p>
  
      <script>
        function converter() {
          const cm = document.getElementById("cmInput").value;
          const m = (cm / 100).toFixed(2);
          const km = (cm / 100000).toFixed(10);
          document.getElementById("mOutput").innerHTML = `${cm} cm = ${m} m`;
          document.getElementById("kmOutput").innerHTML = `${cm} cm = ${km} km`;
        }
      </script>
                </div>
                <div class="menu-item">
    <h3>Teste PHP Horas</h3>
    <p>
      <?php
      date_default_timezone_set('America/Sao_Paulo');
        $data_atual = date('d/m/Y');
        $hora_atual = date('H:i');
        echo "Hoje é ".$data_atual." e agora são ".$hora_atual."h";
      ?>
    </p>
  </div>
  <div class="menu-item">
    <h3>Teste PHP Funcao</h3>

<form method="post">
  <label for="num1">Número 1:</label>
  <input type="number" name="num1" id="num1">
  <br>
  <label for="num2">Número 2:</label>
  <input type="number" name="num2" id="num2">
  <br>
  <input type="submit" value="Calcular"> <br> <br>
  <?php
    $a = $b = $num1 = $num2 = $result = "";
function multiply($a, $b) {
  return $a * $b;
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $num1 = $_POST['num1'];
  $num2 = $_POST['num2'];

  $result = multiply($num1, $num2);

  echo 'Resultado: ' . $result;
}
?>
</form>
  </div>
  <div class="menu-item">
    <h3>Teste PHP String</h3>
    <?php
      echo "Ola Mundo" . "<br>";
      echo strrev("Ola Mundo");
      echo "<br>";
      echo str_replace("Mundo","Debora","Ola Mundo");
    ?>

  </div>
  <div class="menu-item">
    <h3>Teste PHP Array</h3>
    <?php
    $Cardapio = array("Pão", "Café", "Bolo");
    echo "Eu gosto de " . $Cardapio[0] . "," . $Cardapio[1] ."," . $Cardapio[2] . "."; 
    ?>
  </div>
  <div class="menu-item">
    <h3>Teste PHP Numero acessados TXT</h3>
    <?php
    $arquivo = 'contador.txt';
    $contador = file_get_contents($arquivo);
    $contador++;
    file_put_contents($arquivo, $contador);
    echo "Esta página foi acessada " . $contador . " vezes.";
    ?>
  </div>
  <div class="menu-item">
    <h3>Teste PHP Cookies</h3>
    <?php
if(!isset($_COOKIE[$cookie_name])) {
  echo "Cookie named '" . $cookie_name . "' não esta aplicado!";
} else {
  echo "Cookie '" . $cookie_name . "' esta aplicado!<br>";
  echo "Valor is: " . $_COOKIE[$cookie_name];
}
?>
  </div>
  <div class="menu-item">
    <h3>Teste PHP Formulario </h3>
    <form method="post">
        Nome:
        <input type=text size=10 name="name">
        Email:
        <input type=text size=10 name="email">
        Telefone: 
        <input type=text size=10 name="telefone">
        Idade: 
        <input type=text size=10 name="idade">
        CPF:
        <input type=text size=10 name="cpf"> 
        Masculino <input type=radio name="gen" value="Masculino"> 
        <br>
        Feminino <input type=radio name="gen" value="Feminino"> 
        <br>
        Outros <input type=radio name="gen" value="Outros">
        </p> 
        <input type=submit name="Bsub" value="Submit"> 
        </p>
    </form>
  </div>
  <div class="menu-item">
    <h3>Teste PHP Print Formulario</h3>
    <?php
    if ($_SERVER['REQUEST_METHOD'] === 'POST') {
        if (empty($_POST["name"]) && empty($_POST["email"]) && empty($_POST["telefone"]) && empty($_POST["idade"]) && empty($_POST["cpf"])  && empty($_POST["gen"])){
            echo "Por favor, faça seu cadastro.";
        }else{
          if(empty($_POST["name"]) || empty($_POST["email"]) || empty($_POST["telefone"]) || empty($_POST["idade"]) || empty($_POST["cpf"])  || empty($_POST["gen"])){
            echo "Por favor, faça o cadastro completo";
          } else {
            echo "Bem-vindo " . $_POST["name"] . "<br>";
            echo "Seu email é: " . $_POST["email"] . "<br>";
            echo "Seu Telefone é: " . $_POST["telefone"] . "<br>";
            echo "Sua Idade é: " . $_POST["idade"] . "<br>";
            echo "Seu CPF é: " . $_POST["cpf"] . "<br>";
            echo "Seu Gênero é: " . $_POST["gen"] . "<br>";
        }
    }
  }
    ?>
  </div>
  </div>
  <div class="menu-item">
    <h3> PHP Encriptar </h3>
    <form method="post">
  <label for="password">Senha:</label>
  <input type="password" name="password" id="password">
  <input type="submit" value="Enviar">
</form>
    <?php
    $hash = $password = "";
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
  $password = $_POST['password'];

  $hash = password_hash($password, PASSWORD_DEFAULT);

  echo 'Senha codificada: ' . $hash;
}
?>



  </div>
      </main>
  <footer>
    <p>2023 Cafeteria ODAW</p>
  </footer>
</body>
</html>