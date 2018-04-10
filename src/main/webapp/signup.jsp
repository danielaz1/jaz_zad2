<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rejestracja</title>
</head>
<body>
<form action="signup" method="post">
    <label>Login:<input type="text" id="username" name="username"/></label><br/>
    <label>Haslo:<input type="password" id="password" name="password"/></label><br/>
    <label>Potwierdz haslo:<input type="password" id="confirmpassword" name="confirmpassword"/></label><br/>
    <label>Adres email:<input type="email" id="email" name="email"/></label><br/>

    <input type="submit" value="Rejestracja"/>
</form>
</body>
</html>