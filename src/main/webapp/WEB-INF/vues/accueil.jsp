<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KilianAgboton
  Date: 27/09/2018
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link href="<c:url value="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="/resources/css/main.css" rel="stylesheet">

    <title>EnCommun - Le Site collaboratif</title>
</head>
<%@ include file="header.jsp" %>
<body>

    <div class="main-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <c:if test="${!empty erreurDeconnexion}">
            <div class="alert alert-danger" role="alert">
                <a href="#" class="close" data-dismiss="alert"
                   aria-label="close">×</a>
                <strong>Erreur !</strong><c:out value="${erreurDeconnexion}"/>
            </div>
        </c:if>
        <c:if test="${!empty okDeconnexion}">
            <div class="alert alert-success" role="success">
                <a href="#" class="close" data-dismiss="alert"
                   aria-label="close">×</a>
                <strong>OK !</strong><c:out value="${okDeconnexion}"/>
            </div>
        </c:if>
        <h1 class="display-4">EnCommun</h1>
        <p class="lead">Bienvenu sur EnCommun. L'outil collaboratif qui vous permet de gérer vos projets en toute simplicité. Ensemble relevons les plus grands défis.</p>
        <c:if test="${empty loginCourant}">
            <a class="btn btn-outline-primary" href="/connexion">Connexion</a>
        </c:if>

    </div>

    <%@ include file="footer.jsp" %>

</body>
</html>
