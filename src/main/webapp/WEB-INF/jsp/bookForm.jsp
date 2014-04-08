<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %> 

<c:url var="formAction" value="${requestScope.action}" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Book details</title>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${bookListUrl}">Books</a></li>
		</ul>
			
		<h1 class="vcard icon">Book details</h1>
		
		<form:form action="${formAction}" method="${requestScope.method}" modelAttribute="book" cssClass="main">
			<form:errors>
				<div class="warning"><form:errors /></div>
			</form:errors>
			
			<div class="panel grid">
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Name:</div>
					<div class="yui-u">
						<div><form:input path="name" cssClass="short" /></div>
						<form:errors path="name">
							<div class="errorMessage"><form:errors path="name" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">ISBN:</div>
					<div class="yui-u">
						<div><form:input path="isbn" cssClass="short" /></div>
						<form:errors path="isbn">
							<div class="errorMessage"><form:errors path="isbn" /></div>
						</form:errors>
					</div>
				</div>
				<div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Author:</div>
					<div class="yui-u">
						<div><form:input path="author" cssClass="short" /></div>
						<form:errors path="author">
							<div class="errorMessage"><form:errors path="author" /></div>
						</form:errors>
					</div>
				</div>
				<%-- <div class="gridRow yui-gf">
					<div class="fieldLabel yui-u first">Publish Date:</div>
					<div class="yui-u">
						<div><form:input path="publishDate" cssClass="medium" /></div>
						<form:errors path="publishDate">
							<div class="errorMessage"><form:errors path="publishDate" /></div>
						</form:errors>
					</div>
				</div> --%>
				<div class="gridRow yui-gf">
					<div class="yui-u first"></div>
					<div class="yui-u">
						<input type="submit" value="Save" />
					</div>
				</div>
			</div>
		</form:form>
	</body>
</html>
