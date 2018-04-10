<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Nadaj uprawnienia premium</title>
</head>
<body>

<h2>Nadaj/usun uprawnienia premium</h2> <br/>
<form action="addPremium" method="post">
    <label>Login:<input type="text" id="username" name="username"/></label><br/>
    <label><input type=radio name="premium" value="add" checked/>Nadaj</label>
    <label><input type=radio name="premium" value="remove"/>Usun</label>
    <input type="submit" value="Wyslij"/>
</form>
</body>
</html>