<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/urls.jspf" %>

<c:url var="bookJsUrl" value="/scripts/books.js" />
<c:url var="searchByBookUrl" value="/books/search.html" />

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Search results for books like "<c:out value="${param.name}" />"</title>
		<script type="text/javascript" src="${booksJsUrl}"></script>
	</head>
	<body>
		<ul id="breadcrumbs">
			<li><a href="${homeUrl}">Home</a></li>
			<li><a href="${bookListUrl}">Books</a></li>
		</ul>
	
		<h1>Search results for books like "<c:out value="${param.name}" />"</h1>
		
		<c:choose>
			<c:when test="${empty bookList}">
				<p>No books found.</p>
			</c:when>
			<c:otherwise>
				<table id="bookList" class="sortable">
					<thead>
						<tr>
							<th>Name</th>
							<th>Isbn</th>
							<th>Author</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="book" items="${bookList}">
							<c:url var="bookUrl" value="/books/${book.id}.html" />
							<tr id="book-${book.id}">
								<td><span class="vcard icon"><a href="${bookUrl}">${book.name}</a></span></td>
								<td>${book.isbn}</td>
								<td>${book.author}</td>
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
		
		<p>&laquo; Back to <a href="${bookListUrl}">books</a></p>
	</body>
</html>
