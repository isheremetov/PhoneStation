<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<!DOCTYPE html>
<html lang="${language}">
<fmt:bundle basename="app">
<head>
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="css/bootstrap-responsive.css" rel="stylesheet" media="screen">
	<link href="css/body.css" rel="stylesheet" media="screen">
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.validate.js"></script>
	<script src="js/bootstrap.validate.en.js"></script>
	<script src="js/bootstrap.validate.ru.js"></script>
	<title>
		<fmt:message key="title" />
	</title>
	<script type="text/javascript">

		$(document).ready(function() {
  			$('#registrationForm').bt_validate();
		  	//Custom check function
  			$.bt_validate.method('custom_pass_eq', function(value) {
      			return ($('#password').val() == $('#passwordOneMore').val());
    			},
    			'<fmt:message key="passwAreNotEquals" />'
  			);
		 	//Ajax check function
  			$.bt_validate.method(
      			'usercheck',
      			$.bt_validate.ajax_check({
        		url: '/usercheck.php',
        		type: 'POST',
        		return_type: 'text',
        		get_data: function() { return {'email': $('#email').val()} },
        		get_success: function(res) { return (res == '1'); },
        		msg_ok: 'This email is free',
        		msg_checking: 'Checking ...',
        		msg_fail: 'This email is olready used'})
    		);
		});
	</script>
</head>

<body>
<div class="pull-right">
    <form name="chooseLanguage">
	<select id="language" name="language" onchange="submit()">
    	<option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
	</form>
</div>
<div class="page-header">
   	<h3><fmt:message key="hello" />, <c:out value="${Authorization.username}" />!</h3>
</div>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="well span2">
			<!--Sidebar content-->
			<c:import url="jsp/admin/leftMenu.jsp" />
		</div>
		<div class="well span10">
			<!--Body content-->
			<c:choose>
    			<c:when test="${param.page == \"ats\"}">
					<c:import url="jsp/admin/ats.jsp" />
				</c:when>
				<c:when test="${param.page == \"userlist\"}">
					<c:import url="jsp/admin/userlist.jsp" />
				</c:when>
				<c:otherwise>
					404: page not found.
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
</body>
</fmt:bundle>
</html>