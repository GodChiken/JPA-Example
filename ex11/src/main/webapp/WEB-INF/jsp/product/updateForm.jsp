<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head></head>
<body>
<form method="post" action="${pageContext.servletContext.contextPath}/product/${product.id}/edit">
    <input type="text" name="name" value="${product.name}">
    <input type="text" name="price" value="${product.price}">
    <input type="text" name="stockQuantity" value="${product.stockQuantity}">
    <input type="hidden" name="id" value="${product.id}">
    <input type="submit" value="submit">
</form>
</body>
</html>