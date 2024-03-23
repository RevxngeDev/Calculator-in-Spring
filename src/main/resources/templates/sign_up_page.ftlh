<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        /* Estilos para los input */
        input {
            margin: 10px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 200px;
        }

        /* Estilos para el botón */
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            cursor: pointer;
        }

        /* Estilos para el placeholder */
        input::placeholder {
            color: #999;
        }
    </style>
</head>
<body>
<form action="/signUp" method="post">
    <input name="email" type="email" placeholder="EMAIL">
    <input name="password" type="password" placeholder="PASSWORD">
    <input name="firstname" type="text" placeholder="FIRSTNAME">
    <input name="lastname" type="text" placeholder="LASTNAME">
    <input type="submit" value="SIGN UP">
</form>
</body>
</html>