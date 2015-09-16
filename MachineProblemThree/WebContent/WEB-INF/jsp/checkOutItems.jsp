<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>Your Total Bill is : ${amount}</p>

<Table BORDER="1" CELLSPACING="1" CELLPADDING="1" width="10%">
	<tr><th>Product Name</th><th>Price</th><th>Quantity</th></tr>
		<c:forEach var="item" items="${shoppingList}"> 
 <col width="100">
 <col width="50">
 		<tr>
		<td>${item.getProductModel().getProductName()}</td>
		<td>${item.getProductModel().getPrice()}</td>
		<td>${item.getQuantity()}</td>
		</tr>
		</c:forEach>
</Table><br>

<p>Thank you for Shopping&nbsp;&nbsp;&nbsp;&nbsp;${User}&nbsp;&nbsp;</p><br>
&nbsp;&nbsp;<a href="login">Log in</a><br>
</body>
</html>