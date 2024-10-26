const appUtilsScript = (() => {		
	function enumFormatter() {
		const projectStatusColunmEl = Array.from(document.querySelectorAll('.enumFormatter'))
		projectStatusColunmEl.forEach(element => {
			const value = element.textContent.trim()
			element.textContent = value.replace(/_/g, ' ')
		})			
	}
	
	return {
		enumFormatter
	}
})()
