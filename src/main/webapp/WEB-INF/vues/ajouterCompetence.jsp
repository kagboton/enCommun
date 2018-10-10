<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: KilianAgboton
  Date: 27/09/2018
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="/resources/css/main.css" rel="stylesheet">
    <link href="/resources/css/form.css" rel="stylesheet">

    <title>En Commun - Dashboard</title>
</head>
<body>


<header>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal">EnCommun</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <c:if test="${!empty sessionScope.loginCourant}">

                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${sessionScope.loginCourant}"/>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="/dashboard">Tableau de bord</a>
                        <a class="dropdown-item" href="/competences">Liste des competences</a>
                        <a class="dropdown-item" href="/mesCompetences">Mes compétences</a>
                        <a class="dropdown-item" href="/dashboard">S'ajouter une compétence</a>
                        <a class="dropdown-item" href="/deconnexion">Deconnexion</a>
                    </div>
                </div>

            </c:if>
        </nav>
        <c:if test="${empty sessionScope.loginCourant}">
            <a class="btn btn-outline-primary" href="/inscription">Inscription</a>
        </c:if>
    </div>
</header>

<div class="container">
    <header>
        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link" href="/dashboard">Tableau de bord</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/competences">Liste des compétences</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="/mesCompetences">Mes compétences</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/ajouterCompetence">Ajouter une compétence</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/deconnexion">Deconnexion</a>
            </li>
        </ul>
    </header>

    <div class="main-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center ">
        <h1 class="display-4">
            Ajouter une compétence
        </h1>
    </div>

    <div class="container text-center" id="myForm">


        <form:form action="/ajouterCompetence" method="post" class="form-signin" modelAttribute="competenceMembre">

            <c:if test="${!empty message}">
                <div class="alert alert-danger" role="alert">
                    <a href="#" class="close" data-dismiss="alert"
                       aria-label="close">×</a>
                    <strong>Erreur ! </strong><c:out value="${message}"/>
                </div>
            </c:if>

            <div class="form-group">
                <select name="intituleC">
                    <c:forEach items="${competences}" var="competence">
                        <option value="${competence.intituleC}" name="intituleC"><c:out value="${competence.intituleC}"/></option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <form:label path="niveau" class="sr-only">Niveau:</form:label>
                <form:input path="niveau" class="form-control" placeholder="Niveau" />  <form:errors path="niveau" cssStyle="color:red;"/>
            </div>
            <div class="form-group">
                <form:label path="commentaire" class="sr-only">Commentaire :</form:label>
                <form:textarea path="commentaire" class="form-control" placeholder="Commentaire"/>  <form:errors path="commentaire" cssStyle="color:red;"/>
            </div>
            <form:errors cssStyle="color:red;"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Ajouter</button>


        </form:form>

    </div>
</div>

<div class="container">
    <footer class="pt-4 my-md-5 pt-md-5 border-top text-center">
        <div class="inner">
            <p>EnCommun, by <a href="https://github.com/kagboton">@kagboton</a>.</p>
        </div>
    </footer>

    <script>
        Holder.addTheme('thumb', {
            bg: '#55595c',
            fg: '#eceeef',
            text: 'Thumbnail'
        });
    </script>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<%--<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>--%>
<script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>



</body>
</html>
