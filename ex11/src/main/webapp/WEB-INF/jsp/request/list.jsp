<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head></head>
<body>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>회원명</th>
        <th>대표 상품 이름</th>
        <th>주문가격</th>
        <th>대표상품주문수량</th>
        <th>상태</th>
        <th>일시</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requestList}" var="request">
        <tr>
            <th>${request.id}</th>
            <th>${request.member.name}</th>
            <th>123</th>
            <th>50000</th>
            <th>321</th>
            <th>${request.status}</th>
            <th>${request.orderDate}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    var edit = function(productId){
        document.location.href = "${pageContext.servletContext.contextPath}/product/"+productId+"/edit";
    }
</script>
</body>
</html>