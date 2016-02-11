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
        <th>상품명</th>
        <th>가격</th>
        <th>재고수량</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${productList}" var="product">
        <tr>
            <th>${product.id}</th>
            <th>${product.name}</th>
            <th>${product.price}</th>
            <th>${product.stockQuantity}</th>
            <th><input type="button" value="수정" onclick="edit('${product.id}')"></th>
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