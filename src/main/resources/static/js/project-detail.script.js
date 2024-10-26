const projectDetailScript = (() => {	
	formatDates()
	decimalFormat()
	appUtilsScript.enumFormatter()
	
	function formatDates() {
		const dateEl = Array.from(document.querySelectorAll(".dateFormat"))

		dateEl.forEach(element => {
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
	
	function decimalFormat() {
		const decimalEl = Array.from(document.querySelectorAll(".decimalFormat"))
		
		decimalEl.forEach(element => {
			const value = element.textContent.trim()			
			element.textContent = new Intl.NumberFormat('de-DE', {
		        minimumFractionDigits: 2,
		        maximumFractionDigits: 2
		    }).format(value);
		})
	}

})()
