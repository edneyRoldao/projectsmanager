const projectDetailScript = (() => {	
	formatDates()
		
	function formatDates() {
		const dateElements = Array.from(document.querySelectorAll(".dateFormat"))

		dateElements.forEach(element => {
			const value = element.textContent.trim()
			
			if (value) {
				const date = new Date(value)

				const day = String(date.getDate()).padStart(2, '0')
				const month = String(date.getMonth() + 1).padStart(2, '0')
				const year = date.getFullYear()

				element.textContent = `${day}/${month}/${year}`							
			} else {
				element.textContent = 'Not provided'				
			}		
		})
	}

})()
