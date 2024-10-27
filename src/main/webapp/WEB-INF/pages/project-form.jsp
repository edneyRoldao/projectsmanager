<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <title>${project.id == null ? 'Create Project' : 'Edit Project'}</title>
</head>
<body>
	<h2>${project.id == null ? 'Create Project' : 'Edit Project'}</h2>	
	<form action="${project.id == null ? '/projects/create' : '/projects/update'}" method="post">
	    <input type="hidden" name="id" value="${project.id}">
	
	    <label>Member ID:</label>
	    <input type="text" name="memberId" value="${project.memberId}" required>
	    <br>
	
	    <label>Name:</label>
	    <input type="text" name="name" value="${project.name}" required>
	    <br>
	
	    <label>Description:</label>
	    <textarea name="description">${project.description}</textarea>
	    <br>
	
	    <label>Risk:</label>
	    <input type="text" name="risk" value="${project.risk}">
	    <br>
	
	    <label>Status:</label>
	    <input type="text" name="status" value="${project.status}">
	    <br>
	
	    <label>Budget:</label>
	    <input type="text" name="budget" value="${project.budget}">
	    <br>
	
	    <label>Init Date:</label>
	    <input id="initDateId" type="date" name="initDate" value="${project.initDate}">
	    <br>
	
	    <label>End Date:</label>
	    <input type="date" name="endDate" value="${project.endDate}">
	    <br>
	
	    <label>Real End Date:</label>
	    <input type="date" name="realEndDate" value="${project.realEndDate}">
	    <br>
	
	    <input type="submit" value="Save">
	</form>
	
	<a href="/projects">Back to List</a>
	
	<script>
		document.getElementById('initDateId').textContent = '10/05/1982'			
	</script>
	
</body>
</html>