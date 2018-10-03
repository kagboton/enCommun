<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="<c:url value="webjars/bootstrap/4.1.3/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="/resources/css/main.css" rel="stylesheet">

    <title>En Commun - Dashboard</title>
</head>
<%@ include file="header.jsp" %>
<body>

    <div class="main-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
        <c:if test="${!empty mCourant}">
            <h1 class="display-4">
                <c:out value="Bienvenu ${mCourant.surnom} !"/>
            </h1>
        </c:if>
    </div>

    <%@ include file="footer.jsp" %>

</body>
</html>
