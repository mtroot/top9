<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit meal</title>
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px;
        }
        dt {
            display: inline-block;
            width: 170px;
        }
        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
</head>
<body>
<section>
    <h2>Edit meal</h2>
    <a href="/">Home</a>
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
