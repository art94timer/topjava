<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Edit Meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit Meal</h2>
<form id="edit meal" action="/topjava/meals" method="post">
    <label for="description">Description: </label>
    <input id="description" type="text" name="description" value="${meal.description}"></br>
    <label for="calories">Calories: </label>
    <input id="calories" type="number" name="calories" value="${meal.calories}"></br>
    <label for="dateTime">DateTime: </label>
    <input id= "dateTime" type="datetime-local" name="dateTime" value="${meal.dateTime}"></br>
    <input type="hidden" name="id" value="${meal.id}">
    <button form="edit meal" type="submit">Save</button>
</form>
</body>
</html>