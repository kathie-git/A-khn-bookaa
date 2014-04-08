<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %> 

<c:url var="booksJsUrl" value="/scripts/books.js" />
<%-- <c:url var="searchByEmailUrl" value="/contacts/search.html" />  --%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Books</title>
		<script type="text/javascript" src="${booksJsUrl}"></script>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
		</ul>
	
		<h1>Books</h1>
		
		<c:if test="${param.saved}">
			<div class="info alert">Contact saved.</div>
		</c:if>
		<c:if test="${param.deleted}">
			<div class="info alert">Contact deleted.</div>
		</c:if>
		
		<c:choose>
			<c:when test="${empty bookList}">
				<p>Your book list is empty. <a href="${newBookUrl}">Create a new book.</a></p>
			</c:when>
			<c:otherwise>
				<div class="tableActionBar">
					${fn:length(bookList)} books |
					<span class="vcardAdd icon"><a href="${newBookUrl}">Create new book</a></span>
				</div>
				<table id="bookList" class="sortable">
					<thead>
						<tr>
							<th>Name</th>
							<th>Isbn</th>
							<th>Author</th>
							<!-- <th>Publish Date</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${bookList}">
							<c:url var="bookUrl" value="/books/${book.id}.html" />
							<tr id="book-${book.id}">
								<td><span class="vcard icon"><a href="${bookUrl}">${book.name}</a></span></td>
								<%-- <td><c:if test="${not empty book.name}"><span class="email icon"><a href="mailto:${contact.email}">${contact.email} </a></span></c:if></td> --%>
								<td>${book.isbn}</td>
								<td>${book.author}</td>
								<%-- <td>${book.publishDate}</td> --%>
								<td>
									<span class="vcardDelete icon"><a class="deleteBook" href="#">Delete</a></span>
									<form class="deleteForm" action="${bookUrl}" method="POST">
										<input type="hidden" name="_method" value="DELETE" />
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
		<div class="panel" style="padding: 10px 20px">
			<%-- <form action="${searchByEmailUrl}" method="get">
				Search by e-mail (partial OK):&nbsp;
				<input type="text" name="email" class="medium" />&nbsp;
				<input type="submit" value="Search" />
			</form> --%>
		</div>
	</body>
</html>
