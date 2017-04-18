<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags/" prefix="tag"%>
<%@taglib uri="http://www.zisezhixin.com/add" prefix="tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>define tag</title>
</head>
<body>
	<!-- test tag [body-content="empty"] -->
	<tag:add x="1" y="2" />
	<tld:add x="1" y="2"/>

</body>
</html>