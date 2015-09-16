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
<p>Let's Go Shopping&nbsp;&nbsp;${User}&nbsp;&nbsp;<a href="LogOut">Log Out</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="ShowCart">Show cart</a></p>

<form action="AddToCart" >
	<Table BORDER="1" CELLSPACING="1" CELLPADDING="2" width="20%">
		<tr><th>Product Name</th><th>Price</th><th>Quantity</th></tr>
			<c:forEach var="item" items="${productList}"> 
 	<col width="100">
  	<col width="50">
 		<tr>
			<td>
				<INPUT TYPE="RADIO" NAME="productType"
           	          VALUE="${item.getProductName()}">${item.getProductName()}</td>
                     
			<td>${item.getPrice()}</td>
			<td>${item.getQuantity()}</td>
		</tr>
			</c:forEach>

</Table><p>Enter Quantity</p>
<input type="text" name="quantity" pattern="[0-9]+" />
<input type="submit" name="submit" value="Add to Cart" />
</form>
<br>


</body>
</html>