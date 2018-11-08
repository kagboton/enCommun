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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link href="/resources/css/main.css" rel="stylesheet">

    <title>EnCommun - Le Site collaboratif</title>
</head>

<body>
    <header>
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
            <h5 class="my-0 mr-md-auto font-weight-normal">EnCommun</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <c:if test="${!empty sessionScope.loginCourant}">

                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <c:out value="${loginCourant}"/>
                        </button>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/dashboard">Tableau de bord</a>
                            <a class="dropdown-item" href="/competences">Liste des competences</a>
                            <a class="dropdown-item" href="/mesCompetences">Mes compétences</a>
                            <a class="dropdown-item" href="/ajouterCompetence">S'ajouter une compétence</a>
                            <a class="dropdown-item" href="/ajouterProjet">Ajouter un projet</a>
                            <a class="dropdown-item" href="/mesProjets">Mes projets</a>
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

    <div class="main-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <c:if test="${!empty erreurDeconnexion}">
            <div class="alert alert-danger" role="alert">
                <a href="#" class="close" data-dismiss="alert"
                   aria-label="close">×</a>
                <strong>Erreur ! </strong><c:out value="${erreurDeconnexion}"/>
            </div>
        </c:if>
        <c:if test="${!empty okDeconnexion}">
            <div class="alert alert-success" role="success">
                <a href="#" class="close" data-dismiss="alert"
                   aria-label="close">×</a>
                <strong>OK ! </strong><c:out value="${okDeconnexion}"/>
            </div>
        </c:if>
        <h1 class="display-4">EnCommun</h1>
        <p class="lead">Bienvenu sur EnCommun. L'outil collaboratif qui vous permet de gérer vos projets en toute simplicité. Ensemble relevons les plus grands défis.</p>
        <c:if test="${empty sessionScope.loginCourant}">
            <a class="btn btn-outline-primary" href="/connexion">Connexion</a>
        </c:if>

    </div>

    <div class="container">
        <footer class="pt-4 my-md-5 pt-md-5 border-top text-center">
            <div class="inner">
                <p>EnCommun, by <a href="https://github.com/kagboton">@kagboton</a>.</p>
            </div>
        </footer>
    </div>

    <script>
        Holder.addTheme('thumb', {
            bg: '#55595c',
            fg: '#eceeef',
            text: 'Thumbnail'
        });
    </script>

    <script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>
