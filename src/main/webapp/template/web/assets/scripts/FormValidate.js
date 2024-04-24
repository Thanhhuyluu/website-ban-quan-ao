function Validator(options){
    var selectorRules = {};
    var formElement = document.querySelector(options.form);
	var formAlert = document.querySelector('.form-alert');
    function Validate(inputElement, rule){
        var errorMessage;
        var errorElement = inputElement.parentElement.querySelector('.form-message');
		formAlert.classList.toggle('active');
        //lấy rules của selector
        var rules = selectorRules[rule.selector];
        //lặp qua từng rule & kiểm ra
        for(var i = 0; i < rules.length; i++){
            errorMessage= rules[i](inputElement.value);
            if(errorMessage) break;

        }
        if(errorMessage){
             errorElement.innerText = errorMessage;
             inputElement.parentElement.classList.add('invalid');
        }else{
             errorElement.innerText='';
             inputElement.parentElement.classList.remove('invalid');
        }
        return !errorMessage;
    }


    if(formElement){
        formElement.onsubmit = function(e){
            e.preventDefault();
            var isValidForm = true;
            options.rules.forEach(function (rule){
                var inputElement = formElement.querySelector(rule.selector);
                var isValid =  Validate(inputElement,rule);
                if(!isValid){
                        isValidForm = false;
                }
            });



            if(isValidForm){
                formElement.submit();
            }

        }



        options.rules.forEach(function (rule){

            if(Array.isArray(selectorRules[rule.selector])){
                selectorRules[rule.selector].push(rule.test);
            }else {
                selectorRules[rule.selector] = [rule.test];
            }
            
            var inputElement = formElement.querySelector(rule.selector);
            var errorElement = inputElement.parentElement.querySelector('.form-message');



            if(inputElement){
                inputElement.onblur = function(){
                  Validate(inputElement,rule);
                }
                inputElement.oninput = function(){
                    errorElement.innerText='';
                    inputElement.parentElement.classList.remove('invalid');
                }
            }
        })

    }

}



Validator.isRequired = function(selector, message){
    return {
        selector: selector,
        test: function (value){
            return value.trim()? undefined : message || 'Vui lòng nhập trường này'
        }
    }
}
Validator.isEmail = function(selector){
     return {
        selector: selector,
        test: function (value){
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value)?undefined : 'Trường này phải là email';
        }
    }
}

Validator.minLength = function(selector, min){
    return {
        selector: selector,
        test: function(value){
            return value.length >= min ? undefined: `Vui lòng nhập tối thiểu ${min} kí tự`;
        }
    }
}

Validator.isConfirmed = function(selector, getConfirmValue , message){
    return {
        selector: selector,
        test: function(value){
            return value === getConfirmValue() ? undefined : message ||  `Giá trị nhập vào không chính xác`;
        }
    }
}
function containsNonNumeric(str) {
    return /[^\d]/.test(str);
}

Validator.isPhoneNumber = function(selector){
    return {
        selector: selector,
        test: function(value){
            return value.length == 10 && containsNonNumeric(value) == false ? undefined: `Số điện thoại không hợp lệ` 
        }
    }
}

