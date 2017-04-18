<%@ tag body-content="empty" trimDirectiveWhitespaces="true" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="x" required="true" rtexprvalue="true" %>
<%@ attribute name="y" required="true" rtexprvalue="true" %>
<div>
    tag:<input type="text" value="${x}"/>+<input type="text" value="${y}"/>=<input type="text" value="${x+y}"/>
</div>