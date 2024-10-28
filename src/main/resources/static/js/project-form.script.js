const projectFormScript = (() => {	
	validateInputDate()
	membersSelectAutocompleteConfig()

    function validateInputDate() {
        const startDateInput = document.getElementById('project-start-date');
        const expectedEndDateInput = document.getElementById('project-expected-end-date');
        const actualEndDateInput = document.getElementById('project-actual-end-date');

        startDateInput.addEventListener('change', function() {
            const startDate = startDateInput.value;
            expectedEndDateInput.min = startDate;
            actualEndDateInput.min = startDate;
        });
    }

    function membersSelectAutocompleteConfig() {
        document.addEventListener('DOMContentLoaded', function() {
            const choices = new Choices('#memberSelectId')
        });
    }

})()
