<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<a href="/topjava/meals?action=edit">Add Meal</a>
</br>
<table border="1">
    <thead>
    <th>Time</th>
    <th>Description</th>
    <th>Calories</th>
    <th></th>
    <th></th>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <tr>
            <td>
                <fmt:parseDate var="time" value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:ss"></fmt:parseDate>
                <fmt:formatDate value="${time}" pattern="dd.MM.yyyy HH:ss"></fmt:formatDate>
            </td>
            <td>${meal.description}</td>
            <td style="color:${meal.excess ? 'red' : 'green'}">${meal.calories}</td>
            <td><a href="/topjava/meals?action=edit&id=${meal.id}">Update</a></td>
            <td><a href="/topjava/meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>