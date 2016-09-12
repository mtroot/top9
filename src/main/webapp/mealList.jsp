<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .exceed {
            color: red;
        }
        .norm {
            color: green;
        }
    </style>
</head>
<body>
<br>
    <h2>Meal list</h2>
    <a href="index.html">Home</a>
    <form method="get" action="meals">
        <dl>
            <dt>Start Date</dt>
            <dd>
                <input type="date" name="startDate">
            </dd>
        </dl>
        <dl>
            <dt>Start time</dt>
            <dd>
                <input type="time" name="startTime">
            </dd>
        </dl>
        <dl>
            <dt>End Date</dt>
            <dd>
                <input type="date" name="endDate">
            </dd>
        </dl>
        <dl>
            <dt>End Time</dt>
            <dd>
                <input type="time" name="endTime">
            </dd>
        </dl>
        <button type="submit">Filter</button>
    </form>
    <br>
    <a href="meals?action=create">Add meal</a>
    <br>
    <table border="1" cellpadding="3">
        <thead>
        <tr>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
            <th>exceeded</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${ml}" var="meal">
            <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.to.MealWithExceed"/>
            <tr class=${meal.exceed ? "exceed" : "norm"}>
                <td>
                    <%--<%DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");%>
                    <%=meal.getDateTime().format(f)%>--%>
                    ${fmt:matches(meal.dateTime, "yyyy-MM-dd HH:mm")}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>

</body>
</html>
