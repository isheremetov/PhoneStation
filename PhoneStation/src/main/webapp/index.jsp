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
<div class="container">
<form name="chooseLanguage">
	<select id="language" name="language" onchange="submit()">
    	<option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
    </select>
</form>
<div class="row">
	<div class="tab-content span4 offset4 well">
		<ul class="nav nav-tabs">
			<li class="active">
				<a href="#login" data-toggle="tab"><fmt:message key="authorization" /></a>
			</li>
			<li>
				<a href="#registration" data-toggle="tab"><fmt:message key="registration" /></a>
			</li>
		</ul>
		<div id="login" class="tab-pane active in">
			<form action="../PhoneStation/Authorization" method="post" name="authorizationForm" accept-charset="UTF-8">
				<legend><fmt:message key="authFormHeader" /></legend>
				<input id="phone" name="phone" required="required" type="text"
					placeholder="<fmt:message key="placeholderPhone" />" class="span4"/>
				<input id="password" name="password" required="required" type="password"
					placeholder="<fmt:message key="placeholderPassword" />" class="span4"/>
				<label class="checkbox">
					<input type="checkbox" name="rememberMe" value="1"> <fmt:message key="rememberMe" />
				</label>
				<button class="btn btn-info btn-block" type="submit"><fmt:message key="submit" /></button>
			</form>
		</div>
		<div id="registration" class="tab-pane fade">
			<form action="../PhoneStation/Registration" id="registrationForm" method="post" name="registrationForm" accept-charset="UTF-8">
				<legend><fmt:message key="registration" /></legend>
				<input id="phone" name="phone" required="required" type="text"
					placeholder="<fmt:message key="placeholderPhone" />" class="span4"/>
				<input id="password" name="password" required="required" type="password"
					placeholder="<fmt:message key="placeholderPassword" />" class="span4"/>
				<input id="passwordOneMore" name="passwordOneMore" required="required" type="password"
					placeholder="<fmt:message key="placeholderPasswordOneMore" />" class="span4" validate="custom_pass_eq"/>
				<input id="username" name="username" required="required" type="text"
					placeholder="<fmt:message key="username" />" class="span4"/>
				<input id="email" name="email" type="text" required="required"
					placeholder="<fmt:message key="email" />" class="span4" validate="email"/>
				<button class="btn btn-info btn-block" type="submit"><fmt:message key="registration" /></button>
			</form>
		</div>
	</div>
</div>
</div>
</body>
</fmt:bundle>
</html>