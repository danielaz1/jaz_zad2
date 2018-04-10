<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Logowanie</title>
</head>
<body>
<h2>Logowanie</h2>
<br/>Administrator login: admin, haslo: admin<br/>
<form action="login" method="post">
    <label>Login:<input type="text" id="username" name="username"/></label><br/>
    <label>Haslo:<input type="text" id="password" name="password"/></label><br/>
    <input type="submit" value="Logowanie"/>
</form>

<br/><br/> <a href="signup.jsp">Zarejestruj sie</a> <br/><br/>
<a href="/users">Wyswietl informacje o uzytkownikach</a> <br/><br/>

</body>
</html>