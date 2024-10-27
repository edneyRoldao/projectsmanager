<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
<head>
    <title>${data.project.id == null ? 'Create Project' : 'Edit Project'}</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/choices.js/public/assets/styles/choices.min.css" />
    <link rel="stylesheet" href="/css/project-form.style.css">
    <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon">

	<script src="https://cdn.jsdelivr.net/npm/choices.js/public/assets/scripts/choices.min.js"></script>

</head>

<body>
    <header>
        <nav class="navbar">
            <div class="container-fluid">
                <p class="nav fst-italic fs-3">Project Manager</p>
                <a href="/projects/all" class="btn-nav btn btn-primary btn-lg">Back to List</a>
            </div>
        </nav>
    </header>
    
    <main>
        <div class="container my-12">
            <h2 class="text-center mb-4" id="project-title">
				${data.project.id == null ? 'Create Project' : 'Edit Project'}
			</h2>
            <form id="registrationForm" action="/projects/save" method="POST">
				<input type="hidden" name="id" value="${data.project.id}">
				
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="project-name" class="form-label">Project Name</label>
                        <input type="text" value="${data.project.name}" name="name" class="form-control" id="project-name" placeholder="Project name" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="project-description" class="form-label">Project Description - ${data.project.description}</label>
                        <textarea name="description" class="form-control" id="project-description" rows="1" placeholder="Project description" required>${data.project.description}</textarea>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="project-manager" class="form-label">Project Manager</label>
						<select name="memberId" id="memberSelectId" class="form-select choices" required>
							<c:if test="${data.project.id == null}">
							    <option value="" disabled selected>Select a Manager</option>
							</c:if>
							<c:forEach var="item" items="${data.members}">
								<option value="${item.value.id}"  
										${item.selected ? 'selected' : ''}
										${!item.value.employee ? 'disabled' : ''}
									>
									${item.value.name}
								</option>
							</c:forEach>													
						</select>						
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="project-status" class="form-label">Status</label>
                        <select name="status" class="form-select" id="project-status" required>
							<c:if test="${data.project.id == null}">
							    <option value="" disabled selected>Select status</option>
							</c:if>							
							<c:forEach var="status" items="${data.statuses}">
								<option value="${status.value.name()}" 
										${status.selected ? 'selected' : ''}
								>
									${status.value.name().replace('_', ' ')}
								</option>
							</c:forEach>													
                        </select>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="project-classification" class="form-label">Classification</label>
                        <select name="risk" class="form-select" id="project-classification" required>
							<c:if test="${data.project.id == null}">
							    <option value="" disabled selected>Select classification</option>
							</c:if>							
							<c:forEach var="risk" items="${data.risks}">
								<option value="${risk.value.name()}" 
										${risk.selected ? 'selected' : ''}
								>
									${risk.value.name().replace('_', ' ')}
								</option>
							</c:forEach>																				
                        </select>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="project-total-budget" class="form-label">Total Budget</label>						
                        <input type="text"
							   moneyMask
							   max-length="12"
							   number-mask-prefix="R$"
						       value="${data.project.budget}" 
							   name="budget"
							   class="form-control" 
							   id="project-total-budget" 
							   placeholder="$ 0,00"
							   required>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="project-start-date" class="form-label">Start Date</label>
                        <input type="date" value="${data.project.initDate}" name="initDate" class="form-control" id="project-start-date">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="project-expected-end-date" class="form-label">Expected End Date</label>
                        <input type="date" value="${data.project.endDate}" name="endDate" class="form-control" id="project-expected-end-date">
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="project-actual-end-date" class="form-label">Actual End Date</label>
                        <input type="date" value="${data.project.realEndDate}" name="realEndDate" class="form-control" id="project-actual-end-date">
                    </div>
                </div>

                <button type="submit" id="submitButton" class="btn btn-primary">
					${data.project.id == null ? 'Create' : 'Edit'}
				</button>
            </form>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    
	<script src="/js/money-mask.js"></script>	
    <script src="/js/project-form.script.js"></script>
</body>
</html>
