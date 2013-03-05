<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />

<fmt:bundle basename="app">
<form action="../PhoneStation/Payment" method="post" name="servicesForm" accept-charset="UTF-8">
<table class="table table-striped">
				<caption><h4>Dialogues</h4></caption>
				<thead>
				<tr>
					<th>#</th>
					<th>date</th>
					<th>duration</th>
					<th>price</th>
					<th>action</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach var="dialogue" items="${dialogue}" varStatus="count" >
					<tr>
						<td><c:out value="${count.count}" /></td>
						<td>${dialogue.dialogueDate}</td>
						<td>${dialogue.duration}</td>
						<td>${dialoguePayment[count.index]}</td>
						<td>
							<label class="checkbox">
								<input type="checkbox" checked="true" name="pay[]" value="${dialogue.id}">  pay
							</label>
						</td>
					</tr>
					</c:forEach>
              	</tbody>
            </table>
            <button class="btn btn-info btn-block" type="submit"><fmt:message key="save" /></button>
</form>
</fmt:bundle>