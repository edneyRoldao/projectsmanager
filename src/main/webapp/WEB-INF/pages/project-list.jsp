<%@ page isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!doctype html>
<html lang="pt-br">
    <head>
        <title>Project list</title>
        <meta charset="utf-8" />
        <meta name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
            crossorigin="anonymous" />
        <link rel="stylesheet" href="/css/project-list.style.css">
        <link rel="shortcut icon" href="/assets/favicon.ico" type="image/x-icon">
    </head>

    <body>
        <header>
            <nav class="navbar">
                <div class="container-fluid">
                    <p class="nav fst-italic fs-3">Project Manager</p>
                </div>
            </nav>
        </header>
        <main>
            <div id="alert-container"></div>
            <div class="container">
                <div class="row mt-4">
                    <div class="col-md-12 mb-5">
                        <div class="d-flex inline mb-2">
                            <h2 class="col">Projects</h2>
                            <a class="btn-new-proj btn btn-primary btn-lg"
                                href="/register_edit/index.html">
                                New project</a>
                        </div>
                        <table
                            class="table table-striped table-hover table-borderless">
                            <thead>
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Manager in charge</th>
                                    <th scope="col"
                                        class="d-none d-sm-table-cell">Classification</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody class="lists">
								<c:forEach var="project" items="${projects}">
									<tr>
										<div id="projectInfo-${project.id}">
											<td>${project.name}</td>
											<td>${project.member.name}</td>
											<td class="enumFormatter">${project.risk}</td>
											<td class="enumFormatter">${project.status}</td>											
										</div>																																								
									    <td>
											<a class="btn btn-primary btn-sm" href="/projects/${project.id}">Detail</a>											
											<form id="delete-form-${project.id}" action="/projects/${project.id}/delete" method="POST" style="display: inline;">
												<button type="submit" class="btn btn-danger btn-sm" ${project.canBeDeleted ? '' : 'disabled'}>
													Delete
												</button>
											</form>
									    </td>
									</tr>
								</c:forEach>						
                            </tbody>
                        </table>
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
        <script src="/js/project-list.script.js"></script>
    </body>
</html>