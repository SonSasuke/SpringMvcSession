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


<div class="contain">
    <form:form action="${action}" method="post" modelAttribute="order">
    <div class="form-group">
        <label>Customer Name (*)</label>
        <form:input type="text" class="form-control col-md-6" path="customerName"/>
        <small class="form-text text-muted"><form:errors path="customerName" class="text-error" /></small>
    </div>
    <div class="form-group">
        <label>Customer Address (*)</label>
        <form:input type="text" class="form-control col-md-6" path="customerAddress"  />
    </div>
    <div class="mt-10">
        <button type="submit" class="btn btn-primary col-md-2">Save</button>
    </div>
    </form:form>
    <div>
</body>
</html>