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
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <!-- Custom styles for this template -->
    <link href="/resources/css/accueil.css" rel="stylesheet">

   <%-- <link rel="stylesheet"
          href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />--%>
    <title>EnCommun - Le Site collaboratif</title>
</head>

<header>
    <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
        <h5 class="my-0 mr-md-auto font-weight-normal">EnCommun</h5>
        <nav class="my-2 my-md-0 mr-md-3">
            <c:if test="${!empty sessionScope.mCourant}">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <c:out value="${mCourant.surnom}"/>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuButton">
                        <a class="dropdown-item" href="/dashboard">Tableau de bord</a>
                        <a class="dropdown-item" href="/deconnexion">Deconnexion</a>
                    </div>
                </div>

            </c:if>
        </nav>
        <c:if test="${empty sessionScope.mCourant}">
            <a class="btn btn-outline-primary" href="/inscription">Inscription</a>
        </c:if>

    </div>
</header>

<body>


    <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <h1 class="display-4">EnCommun</h1>
        <p class="lead">Bienvenu sur EnCommun. L'outil collaboratif qui vous permet de gérer vos projets en toute simplicité. Ensemble relevons les plus grands défis.</p>
        <c:if test="${empty sessionScope.mCourant}">
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

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

    <script>
        Holder.addTheme('thumb', {
            bg: '#55595c',
            fg: '#eceeef',
            text: 'Thumbnail'
        });
    </script>
</body>
</html>
