<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:bundle basename="app">
<form action="../PhoneStation/UserlistEdit" method="post" name="servicesForm" accept-charset="UTF-8">
			<table class="table table-striped">
				<caption><h4>userlist</h4></caption>
				<thead>
				<tr>
					<th>#</th>
					<th>phone</th>
					<th>username</th>
					<th>purse</th>
					<th>action</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="userlist" items="${userlist}" varStatus="count" >
					<tr>
						<td><c:out value="${count.count}" /></td>
						<td>${userlist.phone}</td>
						<td>${userlist.username}</td>
						<td>${userlist.purse}</td>
						<td>
						<c:choose>
							<c:when test="${userlist.active}">
								<label class="checkbox">
									<input type="checkbox" name="deactivate[]" value="${userlist.id}"> deactivate
								</label>
							</c:when>
							<c:otherwise>
								<label class="checkbox">
									<input type="checkbox" name="activate[]" value="${userlist.id}"> activate
								</label>
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					</c:forEach>
              	</tbody>
            </table>
			<button class="btn btn-info btn-block" type="submit"><fmt:message key="save" /></button>
</form>
</fmt:bundle>