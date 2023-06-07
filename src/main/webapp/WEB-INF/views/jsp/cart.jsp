<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<style>
    <%@include file="/resources/css/main.css" %>
</style>
<jsp:include page="header.jsp" />
<body>
<div class="contain pt-10">
    <!-- Input group -->
    <div class="mt-10">
        <p>
            <c:out value="${msg}" />
        </p>
        <c:if test="${empty listCart}">
        <p>No data</p>
        </c:if>
        <c:if test="${not empty listCart}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Product Name</th>
                <th scope="col">Product Description</th>
                <th scope="col">Unit Price</th>
                <th scope="col">Total San Pham</th>
<%--                <th scope="col">Total Price</th>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${listCart}" varStatus="e">
                <tr>
                    <td>${c.product.productId}</td>
                    <td>${c.product.productName}</td>
                    <td>${c.product.productDescription}</td>
                    <td>${c.product.unitPrice}</td>
                    <td>${c.totalProduct}</td>
<%--                    <td>${c.totalPrice}</td>--%>
                    <td><button class="btn btn-sm btn-primary"
                                onclick="location.href='remove/${c.product.productId}'">Remove To Cart</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
        <div>
            <div class="d-flex justify-content-center">
                <a class="btn btn-outline-primary" href="/cart/check">Check Out</a>
            </div>
        </div>
</body>
</html>