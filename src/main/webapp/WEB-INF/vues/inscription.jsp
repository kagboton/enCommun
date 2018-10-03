<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KilianAgboton
  Date: 27/09/2018
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="<c:url value="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="/resources/css/connexion.css" rel="stylesheet">

    <title>En Commun - Inscription</title>
</head>
<%--<%@ include file="header.jsp" %>--%>
<body class="text-center">

<form action="/inscription" method="post" class="form-signin">

    <img class="mb-4" src="/resources/img/logo.png" alt="" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal">Inscription-vous</h1>

    <c:if test="${!empty erreurInscription}">
        <div class="alert alert-danger" role="alert">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Erreur !</strong> <c:out value="${erreurInscription}"/>
        </div>
    </c:if>
    <div class="form-group">
        <label for="login" class="sr-only">Login : </label>
        <input type = "text" name = "login" id="login" class="form-control" placeholder="Login" required autofocus>
    </div>
    <div class="form-group">
        <label for="mdp" class="sr-only">Mot de passe :</label>
        <input type = "password" name = "mdp" id="mdp" class="form-control" placeholder="Mot de passe" required autofocus>
    </div>
    <div class="form-group">
        <label for="surnom" class="sr-only">Surnom : </label>
        <input type = "text" name = "surnom" id="surnom" class="form-control" placeholder="Surnom" required autofocus>
    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>

    <div class="form-group">
        <p class="text-center">Déjà membre? <a href="/connexion">Connectez-vous</a></p>
    </div>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="<c:url value="webjars/bootstrap/4.1.3/js/bootstrap.min.js" />"></script>
<script src="<c:url value="webjars/jquery/3.3.1/jquery.min.js" />"></script>

</body>

</html>