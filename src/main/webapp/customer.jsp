<%-- 
    Document   : customer
    Created on : Sep 10, 2023, 11:23:27 AM
    Author     : sas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create a Customer</title>
    </head>
    <body>
        <h1>Create A Customer</h1>

        <c:if test="${not empty requestScope.violations}">
            <p>There were issues with your submission. Please fix them.
            <ul>
                <c:forEach items="${requestScope.violations}" var="violation">
                    <li>There was a problem with ${violation.propertyPath}.  The error message was ${violation.message}</li>
                </c:forEach>
            </ul>
        </c:if>
        
        <form method="post" action="${pageContext.request.contextPath}/cust">
            <div>
                <label for="custId">Customer ID</label>
                <input type="text" id="custId" name="custId" value="${requestScope.customer.id}" />
            </div>
            <div>
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" name="firstName" value="${requestScope.customer.firstName}"  />
            </div>
            <div>
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" name="lastName" value="${requestScope.customer.lastName}"  />
            </div>
            <div>
                <label for="email">Email</label>
                <input type="text" id="email" name="email" value="${requestScope.customer.email}"  />
            </div>
            <div>
                <label for="addressId">Address ID</label>
                <input type="number" id="addressId" name="addressId" value="${requestScope.customer.addressId}" />
            </div>
            <div>
                <label for="activeInd">Active?</label>
                <input type="checkbox" id="activeInd" name="activeInd" value="active" ${requestScope.customer.active ? 'checked' : ''} />
            </div>
            <div>
                <label for="storeId">Store ID</label>
                <select id="storeId" name="storeId">
                    <option value="1" ${requestScope.customer.storeId eq 1 ? 'selected' : ''}>Store ID One</option>
                    <option value="2" ${requestScope.customer.storeId == 2 ? 'selected' : ''}>Store ID Two</option>
                </select>
            </div>
            <div>
                <label for="dateExample">Example Date Field</label>
                <input type="date" id="dateExample" name="dateExample" value="${requestScope.customer.createDate}" />
            </div>

            <button type="submit">Create Customer</button>
            
        </form>
    </body>
</html>
