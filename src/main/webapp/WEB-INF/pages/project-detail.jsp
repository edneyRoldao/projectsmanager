<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <title>Project Detail</title>
        <meta charset="utf-8" />
        <meta name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous" />
        
		<link rel="stylesheet" href="/css/project-detail.style.css">
        <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon">		
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
            <div id="alert-container"></div>

            <div class="container">
                <div class="row mt-3">
                    <div class="col-md-12">
                        <dl class="row">
                            <h2 class="project-details">Project Details</h2>
                            <dt class="col-sm-3">Name:</dt>
                            <dd class="col-sm-9" id="project-name">
								${project.name}
							</dd>

                            <dt class="col-sm-3">Manager in charge:</dt>
                            <dd class="col-sm-9" id="project-manager">
								${project.member.name}
							</dd>

                            <dt class="col-sm-3">Description:</dt>
                            <dd class="col-sm-9" id="project-description">
								${project.description}
							</dd>

                            <dt class="col-sm-3">Classification:</dt>
                            <dd class="col-sm-9 enumFormatter" id="project-classification">
								${project.risk}
							</dd>

                            <dt class="col-sm-3">Status:</dt>
                            <dd class="col-sm-9 enumFormatter" id="project-status">
								${project.status}
							</dd>

                            <dt class="col-sm-3">Start Date:</dt>
                            <dd class="col-sm-9 dateFormat" id="project-start-date">
								${project.initDate}
							</dd>

                            <dt class="col-sm-3">Expected End Date:</dt>
                            <dd class="col-sm-9 dateFormat" id="project-expected-end-date">
								${project.endDate}
							</dd>

                            <dt class="col-sm-3">Actual End Date:</dt>
                            <dd class="col-sm-9 dateFormat" id="project-actual-end-date">
								${project.realEndDate}
							</dd>

                            <dt class="col-sm-3">Total Budget:</dt>
                            <dd class="col-sm-9 decimalFormat" id="project-total-budget">
								${project.budget}
							</dd>
                        </dl>

                        <div id="form-container" class="d-flex align-items-center gap-3">
						    <a id="edit-button" class="btn btn-primary" href="/projects/${project.id}/edit">Edit</a>							
							<form id="delete-form-${project.id}" method="POST" action="/projects/${project.id}/delete">
						        <button type="submit" class="btn btn-danger" ${project.canBeDeleted ? '' : 'disabled'}>
									Delete
								</button>
						    </form>
						</div>
                    </div>
                </div>
            </div>
        </main>
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
            integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
            crossorigin="anonymous"></script>

		<script src="/js/app-utils.script.js"></script>
		<script src="/js/project-detail.script.js"></script>			
    </body>
</html>
