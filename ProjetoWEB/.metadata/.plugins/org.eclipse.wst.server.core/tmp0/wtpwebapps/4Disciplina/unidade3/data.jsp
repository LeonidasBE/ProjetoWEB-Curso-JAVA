<%@ page language="java" %>
<%@ page import="java.time.LocalDate" %>
<html>
<body>
	<p>A data atual é === <%= LocalDate.now() %></p>
	<p>2 x 5 = <% out.print(2*5); %></p>
</body>
</html>