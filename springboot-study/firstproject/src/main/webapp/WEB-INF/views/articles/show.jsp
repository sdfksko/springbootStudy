<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- header section -->
<%@ include file="/WEB-INF/views/layouts/header.jsp" %>

<!-- body section -->
<table class="table">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row"><c:out value="${article.id}" /></th>
        <td><c:out value="${article.title}" /></td>
        <td><c:out value="${article.content}" /></td>
    </tr>
    </tbody>
</table>
<a href='/articles/<c:out value="${article.id}" />/edit' class="btn btn-primary">Edit</a>
<a href='/articles/<c:out value="${article.id}" />/delete' class="btn btn-danger">Delete</a>
<a href="/articles">Go to Article List</a>

<%@ include file="/WEB-INF/views/comments/comments.jsp" %>

<!-- footer section -->
<%@ include file="/WEB-INF/views/layouts/footer.jsp" %>