<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:bundle basename="app">

<form action="../PhoneStation/ServicesEdit" method="post" name="servicesForm" accept-charset="UTF-8">
			<table class="table table-striped">
				<caption><h4><fmt:message key="activeServices" /></h4></caption>
				<thead>
				<tr>
					<th>#</th>
					<th><fmt:message key="services" /></th>
					<th><fmt:message key="price" /></th>
					<th><fmt:message key="actions" /></th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="subscribedService" items="${subscribed}" varStatus="count" >
					<tr>
						<td><c:out value="${count.count}" /></td>
						<td>${subscribedService.name}</td>
						<td>${subscribedService.price}</td>
						<td>
							<label class="checkbox">
								<input type="checkbox" name="desubscribe[]" value="${subscribedService.id}">  <fmt:message key="deactivate" />
							</label>
						</td>
					</tr>
					</c:forEach>
              	</tbody>
            </table>
			<table class="table table-striped">
				<caption><h4><fmt:message key="inactiveServices" /></h4></caption>
            	<thead>
                	<tr>
                		<th>#</th>
               			<th><fmt:message key="services" /></th>
						<th><fmt:message key="price" /></th>
						<th><fmt:message key="actions" /></th>
            		</tr>
              	</thead>
              	<tbody>
              	<c:forEach var="notSubscribedService" items="${notSubscribed}" varStatus="count" >
                <tr>
					<td><c:out value="${count.count}" /></td>
					<td>${notSubscribedService.name}</td>
					<td>${notSubscribedService.price}</td>
					<td>
						<label class="checkbox">
							<input type="checkbox" name="subscribe[]" value="${notSubscribedService.id}">  <fmt:message key="activate" />
						</label>
					</td>
                </tr>
                </c:forEach>
              	</tbody>
            </table>
			<button class="btn btn-info btn-block" type="submit"><fmt:message key="save" /></button>
</form>
</fmt:bundle>