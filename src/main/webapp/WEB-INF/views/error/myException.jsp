<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>

<%
    Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
    //记录日志
    Logger logger = LoggerFactory.getLogger("myException.jsp");
    logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Exception - 异常信息</title>
</head>

<body>
<h2>Exception - 异常信息.</h2>
</body>
</html>
