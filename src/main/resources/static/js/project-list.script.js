function renderProjects() {
	function goToProjectDetailPage(id) {
		console.log('=======')
		console.log(id)
	}
	 q
    const list = document.getElementById('project-list');
    const alertContainer = document.getElementById('alert-container');
    list.innerHTML = '';
    alertContainer.innerHTML = '';

    projects.forEach(project => {
        const row = document.createElement('tr');
        row.id = `project-${project.id}`;
        row.innerHTML = `
            <td>${project.name}</td>
            <td id="responsible-${project.id}">${project.manager}</td>
            <td id="classification-${project.id}" class="d-none d-sm-table-cell">${project.classification}</td>
            <td id="status-${project.id}">${project.status}</td>
            <td>
                <form id="delete-form-${project.id}" action="/projects/${project.id}/delete" method="POST" style="display: inline;">
                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                </form>
            </td>
        `;

        row.addEventListener('click', (event) => {
            if (event.target.tagName !== 'BUTTON') {
                window.location.href = `/consultation/index.html?id=${project.id}`;
            }
        });

        list.appendChild(row);
    });

    projects.forEach(project => {
        const deleteForm = document.getElementById(`delete-form-${project.id}`);
        deleteForm.addEventListener('submit', function (event) {
            if (project.status.toLocaleLowerCase() === 'finished' || project.status.toLocaleLowerCase() === 'started' || project.status.toLocaleLowerCase() === 'ongoing') {
                event.preventDefault();

                alertContainer.innerHTML = `
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        It is not possible to delete the project ${project.name} with status <strong>${project.status}</strong>.
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                `;
            }
        });
    });
}

window.onload = renderProjects;
