<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 06/06/2017
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Guestbook</title>
    <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">
</head>
<body>
<div class="ui container fluid padded">
    <div class="ui items">
        <c:forEach items="${comments}" var="comment">
            <div class="item">
                <a class="ui mini circular image">
                    <img src="/WebContent/img/megaphone.png">
                </a>
                <div class="content">
                    <a class="header">${comment[0]}</a>

                    <div class="description">
                        <p>${comment[1]}</p>
                    </div>

                </div>
            </div>
        </c:forEach>
    </div>
    <div class="ui divider"></div>
    <div class="ui inverted segment">
        <form method="post" action="/services/${url}"class="ui inverted form">


            <div class="field">
                <label>Titre</label>
                <input type="text" name="title">
            </div>

            <div class="field">
                <label>Message :</label>
                <textarea rows="2" name="message"></textarea>
            </div>

            <button type = submit class="ui icon button">
                <i class="send icon"></i> Envoyer
            </button>

        </form>
    </div>

</div>

</body>
</html>
