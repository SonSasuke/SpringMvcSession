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
        <c:if test="${empty orderList}">
        <p>No data</p>
        </c:if>
        <c:if test="${not empty orderList}">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Customer Name</th>
                <th scope="col">Customer Address</th>
                <th scope="col">View Details</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orderList}" varStatus="e">
                <tr>
                    <td>${order.orderId}</td>
                    <td>${order.customerName}</td>
                    <td>${order.customerAddress}</td>
                    <td><button class="btn btn-sm btn-primary"
                                onclick="location.href='/cart/viewDetail/${order.orderId}'">View Details</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </c:if>
        <div>
        </div>
</body>
</html>