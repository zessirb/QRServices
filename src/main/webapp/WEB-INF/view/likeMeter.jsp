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
        <link rel="stylesheet" href="/WebContent/css/main.css">
      <!--  <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">-->
    </head>
    <body>

    <div class="button-label like" tabindex="0">
        <form method="post" action="/services/${url}">
            <button type="submit" class="ui button" href="#">
                <i class="heart icon"></i>
            </button>
        </form>

<c:choose>
    <c:when test="${liked == true}">
       Thanks
    </c:when>
    <c:otherwise>
        Like
    </c:otherwise>
    </c:choose>
        </a>
                <span class="label">
                   ${count}
                </span>
    </div>
    </body>
</html>
