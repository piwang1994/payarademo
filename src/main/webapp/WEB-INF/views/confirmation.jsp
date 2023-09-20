<%-- 
    Document   : confirmation
    Created on : Sep 10, 2023, 12:49:52 PM
    Author     : sas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Successfully Created</title>
    </head>
    <body>
        <h1>Confirmation of Customer Creation</h1>
        <ul>
            <li>${requestScope.customer.id}</li>
            <li>${requestScope.customer.firstName}</li>
            <li>${requestScope.customer.lastName}</li>
            <li>${requestScope.customer.email}</li>
            <li>${requestScope.customer.storeId}</li>
            <li>${requestScope.customer.addressId}</li>
            <li>${requestScope.customer.active}</li>
            <li>${requestScope.customer.createDate}</li>
        </ul>
    </body>
</html>
