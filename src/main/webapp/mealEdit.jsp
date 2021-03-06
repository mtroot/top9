<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h2>${param.action == 'create' ? 'Create meal' : 'EditMeal'}</h2>
    <a href="index.html">Home</a>
    <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="post" action="meals">
        <% if (meal.getId() != null) {%>
        <input type = "hidden" name = "id" value = "${meal.id == null ? " " : meal.id}" >
        <%} else {%>
        <input type = "hidden" name = "id" value = "" >
        <%}%>
        <dl>
            <dt>DateTime:</dt>
            <dd>
                <input type="datetime-local" value="${meal.dateTime}" name="datetime">
            </dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd>
                <input type="text" value="${meal.description}" name="description">
            </dd>
        </dl>
        <dl>
            <dt>Calories:</dt>
            <dd>
                <input type="number" value="${meal.calories}" name="calories">
            </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
