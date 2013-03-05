<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:bundle basename="app">
	current purse: <c:out value="${Authorization.purse}"/>

	<form action="../PhoneStation/Purse" method="post">
		<legend>addMoney</legend>
		<input id="purse" name="purse" required="required" type="text"
			placeholder="count" class="span4"/>
		<button class="btn btn-info btn-block" type="submit"><fmt:message key="submit" /></button>
	</form>
</fmt:bundle>