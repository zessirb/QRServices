<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 31/05/2017
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title></title>
    <!--# jquery #-->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <!--# semanticUI#-->
    <script src="bower_components/semantic/dist/semantic.min.js"></script>
    <link rel="stylesheet" href="bower_components/semantic/dist/semantic.min.css">

</head>
<body>

        <div class="ui labeled button" tabindex="0">
            <div class="ui ${color} button">
                <i class="heart icon"></i> Like
            </div>
            <a class="ui basic ${color} left pointing label">
                ${likeCount}
            </a>
        </div>
</body>
</html>
