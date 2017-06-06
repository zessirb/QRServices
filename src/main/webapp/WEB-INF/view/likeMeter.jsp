<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 31/05/2017
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title></title>

      <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">

    </head>
    <body>
    <form method="post" action="/services/${url}">
    <div class="ui labeled button" tabindex="0">

        <button type="submit" class="ui ${liked ? "yellow" : 'red'} button">
            <i class="heart icon"></i>
            <c:choose>
                <c:when test="${liked == true}">
                    Thanks
                </c:when>
                <c:otherwise>
                    Like
                </c:otherwise>
            </c:choose>
        </button>

        <a class="ui basic ${liked ? "yellow" : 'red'} left pointing label">
            ${count}
        </a>
       </div>
    </form>
    </body>
</html>
