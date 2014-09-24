<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<body>
	<h1>${message}</h1>


    <spring:url value="/add-user" var="add-user"/>

    <a href="${add-user}">Add User</a>

</body>
</html>