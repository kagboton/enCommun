<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KilianAgboton
  Date: 27/09/2018
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<script src="<c:url value="webjars/bootstrap/4.1.3/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="webjars/jquery/3.1.1/jquery.min.js" />"></script>
    <link href="<c:url value="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />" rel="stylesheet">--%>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
        <!-- Custom styles for this template -->
        <link href="/resources/css/connexion.css" rel="stylesheet">
        <link href="/resources/css/accueil.css" rel="stylesheet">

    <title>En Commun - Connexion</title>
</head>
<%--<%@ include file="header.jsp" %>--%>
<body class="text-center">

<form action="/connexion" method="post" class="form-signin">

    <img class="mb-4" src="/resources/img/logo.png" alt="" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal">Connectez-vous</h1>

    <c:if test="${!empty erreurConnexion}">
        <div class="alert alert-danger" role="alert">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Erreur !</strong> <c:out value="${erreurConnexion}"/>
        </div>
    </c:if>
    <c:if test="${!empty erreurDejaConnecte}">
        <div class="alert alert-danger" role="alert">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Erreur !</strong> <c:out value="${erreurDejaConnecte}"/>
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

    <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>

    <div class="form-group">
        <p class="text-center">Vous n'êtes pas encore membre ? <a href="/inscription">Inscrivez-vous</a></p>
    </div>

</form>

<%--<%@ include file="footer.jsp" %>--%>

</body>
</html>
