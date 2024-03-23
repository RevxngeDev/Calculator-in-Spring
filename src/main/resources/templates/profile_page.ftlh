<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
            line-height: 1.6;
        }

        /* Estilos para el título "Профиль" */
        h1 {
            font-size: 24px;
            margin-top: 20px;
        }

        /* Estilos para el formulario */
        form {
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            width: 70%;
        }

        /* Estilos para las etiquetas y los inputs */
        label, input {
            display: block;
            margin-bottom: 10px;
        }

        input[type="text"] {
            width: 70%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;

        }

        /* Estilos para el título "Calculations History" */
        h2 {
            margin-top: 20px;
        }

        /* Estilos para la tabla */
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        table, th, td {
            border: 1px solid #ccc;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>
Профиль
<h1>${userName}</h1>
<h1>${userId}</h1>


<h2>Calculadora</h2>
<form action="/calculate" method="post">
    <label for="expression">Expression :</label>
    <input type="text" id="expression" name="expression" required><br><br>
    <p> please leave a space between symbols and numbers :) </p>
    <button type="button" onclick="appendToDisplay('+')">+</button>
    <button type="button" onclick="appendToDisplay('-')">-</button>
    <button type="button" onclick="appendToDisplay('*')">*</button>
    <button type="button" onclick="appendToDisplay('/')">/</button>
    <button type="button" onclick="appendToDisplay('^2')">^2</button>
    <button type="button" onclick="appendToDisplay('sqrt(')">sqrt</button>
    <br><br>
    <button type="submit">Calcular</button>
</form>
<h2>Calculations History</h2>
<table border="1">
    <#list user.calculations as calculation>
        <tr>
            <td>${calculation.expression}</td>
            <td>${calculation.result}</td>
        </tr>
    </#list>
</table>
<script>
    function appendToDisplay(value) {
        document.getElementById('expression').value += value;
    }
</script>
</body>
</html>