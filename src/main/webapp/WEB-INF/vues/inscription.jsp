<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="/resources/css/connexion.css" rel="stylesheet">

    <title>En Commun - Inscription</title>
</head>

<body class="text-center">

<form:form action="/inscription" method="post" class="form-signin" modelAttribute="membre">

    <img class="mb-4" src="/resources/img/logo.png" alt="" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal">Inscription-vous</h1>

    <c:if test="${!empty erreurInscription}">
        <div class="alert alert-danger" role="alert">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Erreur ! </strong> <c:out value="${erreurInscription}"/>
        </div>
    </c:if>
    <div class="form-group">
        <form:label path="login" class="sr-only">Login : </form:label>
        <form:input path="login" class="form-control" placeholder="Login"/><form:errors path="login" cssStyle="color:red;"/><br/>
    </div>
    <div class="form-group">
        <form:label path="motDePasse" class="sr-only">Mot de passe :</form:label>
        <form:password path="motDePasse" class="form-control" placeholder="Mot de passe"/>  <form:errors path="motDePasse" cssStyle="color:red;"/><br/>
    </div>
    <div class="form-group">
        <form:label path="surnom" class="sr-only">Surnom : </form:label>
        <form:input path="surnom" class="form-control" placeholder="Surnom" required="true" /><br/>
    </div>
    <form:errors cssStyle="color:red;"/>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Inscription</button>

    <div class="form-group">
        <p class="text-center">Déjà membre? <a href="/connexion">Connectez-vous</a></p>
    </div>
    <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
</form:form>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>