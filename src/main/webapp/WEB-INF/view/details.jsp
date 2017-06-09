<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 08/06/2017
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
    <head>
        <title>Detail</title>
        <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">
    </head>
    <body>
    <div class="ui secondary pointing menu">
        <div class="item">
            <img src="/WebContent/img/icon.png">
        </div>
        <div class="header item">
            details
        </div>
        <div class="item">
            <a href="/manage" class="circular ui teal button"><i class="Long Arrow Left icon"></i> </a>
        </div>
        </div>
    <h1 class="ui header">Informations sur le service :</h1>

    <div class="ui container grid">
        <div class="ten wide column">
            <div class="ui text container">

                <div class="ui divided selection list">
                    <a class="item">
                        <div class="ui teal horizontal label">Type</div>
                        ${service[3]}
                    </a>
                    <a class="item">
                        <div class="ui red horizontal label">Nom</div>
                        ${service[1]}
                    </a>
                    <a class="item">
                        <div class="ui purple horizontal label">Description</div>
                        ${service[2]}
                    </a>
                <form action="/servicemanager" method="delete">
                    <input type="hidden" name="url" value="${url}">
                    <button type="submit" class="ui button red">supprimer</button>
                </form>
                </div>
            </div>
        </div>
        <div class="six wide column">
            <div class="ui teal ribbon label">
                <i class="Qrcode icon"></i> QRCode
            </div>
            <img class="ui medium bordered image" src="/qrcode/${url}">
        </div>
        <div class="sixteen wide column">

            <c:choose>
                <c:when test="${type == 'likemeter'}">

                    <div class="ui statistic">
                        <div class="label">
                            Like
                        </div>
                        <div class="value">
                            ${count}
                        </div>
                    </div>

                </c:when>
                <c:when test="${type == 'guestbook'}">
                <div class="ui items">
                    <c:forEach items="${comments}" var="comment">
                        <div class="item">
                            <a class="ui tiny image">
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
                </c:when>

                <c:when test="${type == 'newsletter'}">
                    <div class="ui list">
                        <c:forEach items="${mails}" var="mail">
                           <c:if test="${mail ==''}">
                               <div class="item">
                                   <i class="mail icon"></i>
                                   <div class="content">
                                       <a href="mailto:${mail}">${mail}</a>
                                   </div>
                               </div>
                           </c:if>
                        </c:forEach>
                    </div>
                </c:when>
            </c:choose>
        </div>

    </div>


    </body>
</html>
