<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <link href="<c:url value="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />" rel="stylesheet">
        <link href="/resources/css/connexion.css" rel="stylesheet">


    <title>En Commun - Connexion</title>
</head>
<%--<%@ include file="header.jsp" %>--%>
<body class="text-center">

<form:form action="/connexion" method="post" class="form-signin" modelAttribute="membre">

    <img class="mb-4" src="/resources/img/logo.png" alt="" width="72" height="72">

    <h1 class="h3 mb-3 font-weight-normal">Connectez-vous</h1>

    <c:if test="${!empty erreurConnexion}">
        <div class="alert alert-danger" role="alert">
            <a href="#" class="close" data-dismiss="alert"
               aria-label="close">×</a>
            <strong>Erreur !</strong> <c:out value="${erreurConnexion}"/>
        </div>
    </c:if>

    <div class="form-group">
        <form:label path="login" class="sr-only">Login:</form:label>
        <form:input path="login" class="form-control" placeholder="Login" />  <form:errors path="login" cssStyle="color:red;"/><br/>
    </div>
    <div class="form-group">
        <form:label path="motDePasse" class="sr-only">Mot de passe :</form:label>
        <form:password path="motDePasse" class="form-control" placeholder="Mot de passe"/>  <form:errors path="motDePasse" cssStyle="color:red;"/><br/>
        <form:errors cssStyle="color:red;"/><br/>
    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Connexion</button>

    <div class="form-group">
        <p class="text-center">Vous n'êtes pas encore membre ? <a href="/inscription">Inscrivez-vous</a></p>
    </div>

</form:form>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="<c:url value="webjars/bootstrap/4.1.3/js/bootstrap.min.js" />"></script>
<script src="<c:url value="webjars/jquery/3.3.1/jquery.min.js" />"></script>

</body>
</html>
