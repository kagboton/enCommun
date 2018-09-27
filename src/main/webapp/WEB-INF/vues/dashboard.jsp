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
    <title>En Commun - Dashboard</title>
</head>
<body>
    <h1>Dashboard</h1>

    <c:if test="${!empty mCourant}"> <c:out value="Bienvenu ${mCourant.surnom} !"/></c:if>

</body>
</html>
