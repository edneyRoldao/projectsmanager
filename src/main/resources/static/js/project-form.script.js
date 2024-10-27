const projects = [
    { id: 1, name: 'Project Alpha', startDate: '2023-01-10', manager: 'John Silva', expectedEndDate: '2023-12-31', actualEndDate: null, totalBudget: 100000, description: 'Developing a new feature for the platform.', classification: 'Medium', status: 'Analysis completed' },
    { id: 2, name: 'Project Beta', startDate: '2022-05-15', manager: 'Maria Souza', expectedEndDate: '2023-06-30', actualEndDate: '2023-07-05', totalBudget: 200000, description: 'Upgrading the infrastructure and server capacity.', classification: 'High', status: 'Finished' },
    { id: 3, name: 'Project Gamma', startDate: '2021-08-01', manager: 'Gustavo Henrique', expectedEndDate: '2022-12-01', actualEndDate: null, totalBudget: 50000, description: 'Research and development of a new product.', classification: 'Low', status: 'Analysis approved' },
    { id: 4, name: 'Project Delta', startDate: '2023-03-01', manager: 'Ana Pereira', expectedEndDate: '2024-01-15', actualEndDate: null, totalBudget: 75000, description: 'Implementation of new security protocols.', classification: 'Medium', status: 'Under analysis' },
    { id: 5, name: 'Project Epsilon', startDate: '2023-09-10', manager: 'Lucas Oliveira', expectedEndDate: '2024-07-01', actualEndDate: null, totalBudget: 150000, description: 'Developing a mobile application for internal use.', classification: 'Low', status: 'Started' },
    { id: 6, name: 'Project Zeta', startDate: '2022-11-20', manager: 'Fernanda Costa', expectedEndDate: '2023-05-15', actualEndDate: '2023-05-20', totalBudget: 120000, description: 'Migration of legacy systems to cloud infrastructure.', classification: 'High', status: 'Finished' },
    { id: 7, name: 'Project Eta', startDate: '2023-02-05', manager: 'Carlos Santos', expectedEndDate: '2023-12-31', actualEndDate: null, totalBudget: 95000, description: 'Analysis and design of a new CRM system.', classification: 'Medium', status: 'Ongoing' },
    { id: 8, name: 'Project Theta', startDate: '2021-10-10', manager: 'Clara Lima', expectedEndDate: '2022-09-30', actualEndDate: '2022-10-05', totalBudget: 170000, description: 'Implementation of a new customer support system.', classification: 'High', status: 'Finished' },
    { id: 9, name: 'Project Iota', startDate: '2023-04-01', manager: 'Ricardo Almeida', expectedEndDate: '2024-03-31', actualEndDate: null, totalBudget: 60000, description: 'Developing new internal analytics tools.', classification: 'Low', status: 'Under analysis' },
    { id: 10, name: 'Project Kappa', startDate: '2023-06-15', manager: 'Juliana Martins', expectedEndDate: '2024-06-01', actualEndDate: null, totalBudget: 80000, description: 'Development of a new customer portal.', classification: 'Medium', status: 'Started' }
];

function getProjectIdFromURL() {
    const params = new URLSearchParams(window.location.search);
    return params.get('id');
}

function loadProjectData() {
    const projectId = getProjectIdFromURL();
    const titleElement = document.getElementById('project-title')
    
    if (projectId) {
        const project = projects.find(p => p.id === parseInt(projectId));
        if (project) {
            completeForm(project);
            titleElement.textContent = "Project Edit"
        } else {
            alert('Project not found.');
        }
    }
}

function completeForm(data) {
    document.getElementById('project-name').value = data.name;
    document.getElementById('project-description').value = data.description;
    document.getElementById('project-manager').value = data.manager;

    const statusMap = {
        'Started': 'STARTED',
        'Planned': 'PLANNED',
        'Ongoing': 'ONGOING',
        'Finished': 'FINISHED',
        'Cancelled': 'CANCELLED',
        'Analysis completed': 'ANALYSIS_DONE',
        'Under analysis': 'UNDER_ANALYSIS',
        'Analysis approved': 'ANALYSIS_APPROVED'
    };

    const classificationMap = {
        'Low': 'LOW',
        'Medium': 'MEDIUM',
        'High': 'HIGH'
    };

    document.getElementById('project-status').value = statusMap[data.status];
    document.getElementById('project-classification').value = classificationMap[data.classification];
    document.getElementById('project-total-budget').value = data.totalBudget;
    document.getElementById('project-start-date').value = data.startDate;
    document.getElementById('project-expected-end-date').value = data.expectedEndDate;
    document.getElementById('project-actual-end-date').value = data.actualEndDate || '';

    setDateRestrictions(data.startDate);
}

function setDateRestrictions() {
    const startInput = document.getElementById('project-start-date');
    const expectedEndInput = document.getElementById('project-expected-end-date');
    const actualEndInput = document.getElementById('project-actual-end-date');

    startInput.addEventListener('change', function() {
        expectedEndInput.setAttribute('min', startInput.value);
        actualEndInput.setAttribute('min', startInput.value);
    });
}

window.onload = function() {
    setDateRestrictions();
    loadProjectData(); 
};
