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
    <title>Manager de service</title>
    <script src="/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="/bower_components/semantic/dist/semantic.min.js"></script>

    <link rel="stylesheet" href="/bower_components/semantic/dist/semantic.min.css">
    <script src="/WebContent/js/script.js"></script>
</head>
<body>
<div class="ui secondary pointing menu">
    <div class="item">
        <img src="/WebContent/img/icon.png">
    </div>
    <div class="header item">
        QRServices
    </div>
    <div class="right menu">
        <c:choose>
            <c:when test="${logged==false}">
                <a class="ui item login">
                    Login
                </a>
            </c:when>
            <c:otherwise>
                <div class="ui item logout">Utilisateur</div>
                <div class="ui logout popup">
                    <div class="header">Dandre</div>
                    <a class="ui red basic buttonn" href="/login?url=/manage">log  out</a>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>
<div class="ui container">
    <div class="ui grid four cards">
<c:choose>
    <c:when test="${logged}">
        <c:forEach items="${services}" var="service">
            <div class="ui card">
                <div class="image">
                    <img src="/WebContent/img/${service[3]}.png">
                </div>
                <div class="content">
                    <div class="header">${service[1]}</div>
                    <div class="meta">
                        <a>${service[3]}</a>
                    </div>
                    <div class="description">
                            ${$service[2]}
                    </div>
                </div>
                <a href="/services/${service[0]}" class="ui bottom attached button">
                    <i class="add icon"></i>
                    Details
                </a>

            </div>


        </c:forEach>



        <div class="card">
            <div class="image">
                <a href="#" class="addservice"><img src="/WebContent/img/add.png"></a>
            </div>
        </div>

        </c:when>
    </c:choose>
    </div>


    <div class="ui large ${logged ? "":"login"} modal">
        <div class="header">Header</div>
        <div class="content">
            <form class="ui form" method="post" action="/login">
                <input type="hidden" name="url" value="/manage">
                <div class="ui labeled input">
                    <div class="ui label">
                        Nom d'utilisateur
                    </div>
                    <input type="text" name="username" placeholder="user">
                </div>
                <div class="ui labeled action input">
                    <div class="ui label">
                        Mot de passe
                    </div>
                    <input type="password" name="password" placeholder="****">
                    <button type="submit" class="ui button">Valider</button>
                </div>
            </form>

        </div>
    </div>


    <div class="ui addService modal">
        <div class="header">Ajout d'un service</div>
        <div class="content">
            <div class="ui services four cards">

                <div class="ui card">
                    <div class="blurring dimmable image">
                        <div class="ui dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui inverted likemeter button">Selectionner</div>
                                </div>
                            </div>
                        </div>
                        <img src="/WebContent/img/likemeter.png" >
                    </div>
                    <div class="content">
                        <a class="header" href="#">Compteur de like</a>
                        <div class="description">
                            Suivez les tendences
                        </div>
                    </div>
                </div>

                <div class="ui card">
                    <div class="blurring dimmable image">
                        <div class="ui dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui inverted guestbook button">Selectionner</div>
                                </div>
                            </div>
                        </div>
                        <img src="/WebContent/img/guestbook.png" >
                    </div>
                    <div class="content">
                        <a class="header" href="#">Livre d'or</a>
                        <div class="description">
                            Ayez un retour de la part de votre communauté
                        </div>

                    </div>
                </div>


                <div class="ui card">
                    <div class="blurring dimmable image">
                        <div class="ui dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui inverted newsletter button">Selectionner</div>
                                </div>
                            </div>
                        </div>
                        <img src="/WebContent/img/newsletter.png" >
                    </div>
                    <div class="content">
                        <a class="header" href="#">Inscription a une newletter</a>
                        <div class="description">
                            Faites en sorte que vos abboné ne rates aucune news
                        </div>
                    </div>
                </div>



                <div class="ui card">
                    <div class="blurring dimmable image">
                        <div class="ui dimmer">
                            <div class="content">
                                <div class="center">
                                    <div class="ui inverted button">Selectionner</div>
                                </div>
                            </div>
                        </div>
                        <img src="/WebContent/img/survey.png" >
                    </div>
                    <div class="content">
                        <a class="header" href="#">Sondage</a>
                        <div class="description">
                            Demander l'avis de votre communauté
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</div>
</div>

<div class="ui addLikeMeter modal">
    <div class="header">Ajout d'un compteur de like</div>
    <div class="content">
        <form class="ui form" method="post" action="/likemetermanager">
            <div class="field" >
                <label>Titre</label>
                <input type="text" name="titre" placeholder="Titre">
            </div>
            <div class="field">
                <label>Description</label>
                <textarea name="description" rows="2"></textarea>
            </div>
            <button type="submit" class="ui teal button">Valider</button>
        </form>

    </div>
</div>

<div class="ui addGuestbook modal">
    <div class="header">Ajout d'un compteur de like</div>
    <div class="content">
        <form class="ui form" method="post" action="/guestbookmanager">
            <div class="field" >
                <label>Titre</label>
                <input type="text" name="titre" placeholder="Titre">
            </div>
            <div class="field">
                <label>Description</label>
                <textarea name="description" rows="2"></textarea>
            </div>
            <button type="submit" class="ui teal button">Valider</button>
        </form>

    </div>
</div>

<div class="ui addNewsletter modal">
    <div class="header">Ajout d'un compteur de like</div>
    <div class="content">
        <form class="ui form" method="post" action="/newslettermanager">
            <div class="field" >
                <label>Titre</label>
                <input type="text" name="titre" placeholder="Titre">
            </div>
            <div class="field">
                <label>Description</label>
                <textarea name="description" rows="2"></textarea>
            </div>
            <button type="submit" class="ui teal button">Valider</button>
        </form>

    </div>
</div>

</div>

</body>
</html>
