<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head></head>
<body>
<form method="post" action="${pageContext.servletContext.contextPath}/product/new">
    <input type="text" name="name">
    <input type="text" name="price">
    <input type="text" name="stockQuantity">
    <input type="submit" value="submit">
</form>

</body>
</html>