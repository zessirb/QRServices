<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 06/06/2017
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Newsletter</title>
    <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">
</head>
<body>
<form method="post" action="/services/${url}" class="ui form">
    <div class="field ${error? "error":""}">
        <label>E-mail :</label>
        <div class="ui action input">
            <input type="email" name="mail" placeholder="joe@schmoe.com">
            <button type="submit" class="ui teal button">Envoyer</button>
        </div>

    </div>
</form>

</body>
</html>
