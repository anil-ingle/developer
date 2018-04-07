<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	$(document).ready(function() {
		             alert("hi");
						$.get("../user", function(data) {
							alert(data);
							var a = "<option value=''></option>";
							$.each(JSON.parse(data), function(k, v) {
								a += "<option value='"+v.cityId+"'>"
										+ v.cityName + "</option>";
							});
							$("#city").html(a);

						});
	});
						</script>

<body>
<div>
<h2></h2>
Select city: <select name="city" id="city" ></select>
							 

</div>
</body>
</html>