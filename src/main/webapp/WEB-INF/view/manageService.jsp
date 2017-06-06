<%--
  Created by IntelliJ IDEA.
  User: hugo
  Date: 05/06/2017
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title></title>
    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/bower_components/semantic/dist/semantic.min.js"></script>

    <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">
    <script src="/WebContent/js/script.js"></script>
</head>
<body>
    <div class="ui secondary pointing menu">
        <a class="item">
            Home
        </a>
        <a class="item">
            Messages
        </a>
        <a class="item active">
            Friends
        </a>
        <div class="right menu">
            <c:choose>
                <c:when test="${logged==false}">
                    <a class="ui item login">
                        Login
                    </a>
                </c:when>
                <c:otherwise>
                    <div class="ui item logout">Activator</div>
                    <div class="ui logout popup">
                        <div class="header">Custom Header</div>
                        <div class="ui red basic buttonn">log  out</div>
                    </div>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <div class="ui container">
        <div class="ui link cards">
            <div class="card">
                <div class="image">
                    <img src="/images/avatar2/large/matthew.png">
                </div>
                <div class="content">
                    <div class="header">Matt Giampietro</div>
                    <div class="meta">
                        <a>Friends</a>
                    </div>
                    <div class="description">
                        Matthew is an interior designer living in New York.
                    </div>
                </div>
                <div class="extra content">
      <span class="right floated">
        Joined in 2013
      </span>
      <span>
        <i class="user icon"></i>
        75 Friends
      </span>
                </div>
            </div>
            <div class="card">
                <div class="image">
                    <img src="/images/avatar2/large/molly.png">
                </div>
                <div class="content">
                    <div class="header">Molly</div>
                    <div class="meta">
                        <span class="date">Coworker</span>
                    </div>
                    <div class="description">
                        Molly is a personal assistant living in Paris.
                    </div>
                </div>
                <div class="extra content">
      <span class="right floated">
        Joined in 2011
      </span>
      <span>
        <i class="user icon"></i>
        35 Friends
      </span>
                </div>
            </div>
            <div class="card">
                <div class="image">
                    <img src="/images/avatar2/large/elyse.png">
                </div>
                <div class="content">
                    <div class="header">Elyse</div>
                    <div class="meta">
                        <a>Coworker</a>
                    </div>
                    <div class="description">
                        Elyse is a copywriter working in New York.
                    </div>
                </div>
                <div class="extra content">
      <span class="right floated">
        Joined in 2014
      </span>
      <span>
        <i class="user icon"></i>
        151 Friends
      </span>
                </div>
            </div>
        </div>
    </div>
    <div class="ui login modal">
        <div class="header">Header</div>
        <div class="content">
            <form class="ui form">
                <div class="ui labeled input">
                    <div class="ui label">
                        Nom
                    </div>
                    <input type="text" placeholder="user@mail.xyz">
                </div>
                <div class="ui labeled input">
                    <div class="ui label">
                        Mot de passe
                    </div>
                    <input type="password" placeholder="****">
                </div>
            </form>

        </div>
    </div>

<div class="ui addService modal">
    <div class="header">Ajout d'un service</div>
    <div class="content">
        <div class="ui cards">
            <div class="ui card">
                <a class="image" href="#">
                    <img src="/webcontent/img/likeMeter.png">
                </a>
                <div class="content">
                    <a class="header" href="#">Compteur de like</a>
                    <div class="meta">
                        <a>cool stuff</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <div class="ui addLikeMetter modal">
        <div class="header">Ajout d'un compteur de like</div>
        <div class="content">
            <form class="ui form">
                <div class="field">
                    <label>Titre</label>
                    <input type="text" name="first-name" placeholder="Titre">
                </div>
                <div class="field">
                    <label>Description</label>
                    <textarea rows="2"></textarea>
                </div>
            </form>

           </div>
    </div>
</body>
</html>
