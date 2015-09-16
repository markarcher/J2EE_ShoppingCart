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
&nbsp;&nbsp;<a href="LogOut">Log Out</a><br>
<p>${User}&nbsp;&nbsp;Your Shopping Cart Contains</p><br>
<p>${emptyCart}</p>
<p>${NotEnoughQty}</p><br>

<form action="ClrRemoveItemsFrmCart" >
	<Table BORDER="1" CELLSPACING="1" CELLPADDING="1" width="10%">
	<tr><th>Product Name</th><th>Price</th><th>Quantity</th></tr>
		<c:forEach var="item" items="${shoppingList}"> 
 	<col width="100">
  	<col width="50">
 	<tr>
		<td><INPUT TYPE="RADIO" NAME="productType"
                     VALUE="${item.getProductModel().getProductName()}">${item.getProductModel().getProductName()}</td>
		<td>${item.getProductModel().getPrice()}</td>
		<td>${item.getQuantity()}</td>
	</tr>
</c:forEach>
</Table><br>
<input type="submit" name="remove" value="removeItem">
<input type="submit" name="clear" value="clearItems"><br><br>
<input type="submit" name="checkout" value="check_Out">
</form>
<br>
<p>Total Amount: ${totalAmount}</p>

<a href = "CustomerHome">Shop Again</a>

</body>
</html>