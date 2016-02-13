<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head></head>
<body>
<form action="${pageContext.servletContext.contextPath}/request" method="post">
    <select name="memberId">
        <c:forEach items="${memberList}" var="member">
            <option value="${member.id}">${member.name}</option>
        </c:forEach>
    </select>
    <select name="productId">
        <c:forEach items="${productList}" var="product">
            <option value="${product.id}">${product.name}</option>
        </c:forEach>
    </select>
    <input type="text" name="count">
    <input type="submit">
</form>
</body>
</html>