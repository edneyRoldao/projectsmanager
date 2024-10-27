const moneyMask = (function () {        
    const defaultMoneyMaskMaxLength = 9
    document.querySelectorAll('[moneyMask]').forEach(input => {
        if (input.tagName === 'INPUT') {
            let lastValue = '0,00'
            const prefix = input.getAttribute('number-mask-prefix') || ''
            const maxLength = parseInt(input.getAttribute('max-length')) || defaultMoneyMaskMaxLength
            const maxValue = !!input.getAttribute('max-value') ? parseFloat(input.getAttribute('max-value')) : null 

            function _isHigherThanMaxValue(value) {
                if (!maxValue) return false
                
                while (value.length < 3) {
                    value = "0" + value
                }

                let int = value.substring(0, value.length -2)
                const decimal = value.substring(value.length - 2)
                const valueParsed = parseFloat(`${int}.${decimal}`)
                
                return valueParsed > maxValue
            }

            input.addEventListener('keyup', (event) => {
                let inputValue = event.target.value

                // removing everything except numbers
                inputValue = inputValue.replace(/[^0-9]+/g, '')

                // removing zeros at left side
                while (inputValue.charAt(0) === '0') {
                    inputValue = inputValue.substring(1)
                }

                // check max value
                if (_isHigherThanMaxValue(inputValue)) {
                    event.target.value = `${prefix} ${lastValue}`
                    return
                }

                // limitting max value
                if (inputValue.length > maxLength) {
                    inputValue = inputValue.substring(0, maxLength)
                }

                // fulfill decimal number
                while (inputValue.length < 3) {
                    inputValue = "0" + inputValue
                }

                // formatting decimal number 
                let intValue = inputValue.substring(0, inputValue.length -2)
                const decimalValue = inputValue.substring(inputValue.length - 2)
                
                intValue = intValue.replace(/\B(?=(\d{3})+(?!\d))/g, ".")

                const currentValue = `${intValue},${decimalValue}`

                _changeAttributeValueToNotifyMutationObserverWhenValueChanged(lastValue, currentValue, input)

                lastValue = currentValue
                event.target.value = `${prefix} ${currentValue}`
            })
        }
    })

    function _changeAttributeValueToNotifyMutationObserverWhenValueChanged(oldValue, currentValue, input) {
        if (oldValue != currentValue) {
            input.setAttribute('mask-value', currentValue)
        }
    }

})()
