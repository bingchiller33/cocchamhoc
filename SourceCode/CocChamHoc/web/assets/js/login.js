function Validator(options) {

    function getParentElement(element, selector) {
        while (element.parentElement) {
            if (element.parentElement.matches(selector)) {
                return element.parentElement;
            }
            element = element.parentElement;
        }
    }


    var selectorRules = {};

    // hàm thực hiện validate
    function validate(inputElement, rule) {
        var errorElement = getParentElement(inputElement, options.formGroupSelector).querySelector(options.querySelector);
        var formSubmit = document.querySelector("#form-submit");
        var emailValue = document.querySelector("#email").value;
        var passwordValue = document.querySelector("#password").value;
        console.log(emailValue);
        console.log(typeof passwordValue);
        var errorMessage;

        // lấy ra các rule của selector
        var rules = selectorRules[rule.selector];

        // lặp qua từng rule và ktra
        for (var i = 0; i < rules.length; ++i) {
            errorMessage = rules[i](inputElement.value);
            if (errorMessage) break;
        }

        if (errorMessage) {
            errorElement.innerText = errorMessage;
            getParentElement(inputElement, options.formGroupSelector).classList.add('invalid');
        } else {
            errorElement.innerText = '';
            getParentElement(inputElement, options.formGroupSelector).classList.remove('invalid');
        }
        return !errorMessage;
    }

    // lấy element của form cần validate
    var formElement = document.querySelector(options.form);

    if (formElement) {

        formElement.onsubmit = function (e) {
            e.preventDefault();

            var isFormValid = true;

            // thực hiện qua từng rule và validate
            options.rules.forEach(function (rule) {

                var inputElement = formElement.querySelector(rule.selector);
                var isValide = validate(inputElement, rule);
                if (!isValide) {
                    isFormValid = false;
                }
            });

            if (isFormValid) {
                // trường hợp submit với javaScript
                if (typeof options.onSubmit === 'function') {
                    var enableInputs = formElement.querySelectorAll('[name]:not([disabled])');

                    var formValues = Array.from(enableInputs).reduce(function (values, input) {
                        (values[input.name] = input.value);
                        return values;
                    }, {});
                    options.onSubmit(formValues);
                } else {
                    // submit với hành vi mặc định của form
                    formElement.submit();
                }
            }
        };
        // xử lý lặp quá mỗi rule và xử lý (lắng nghe sự kiên blur)
        options.rules.forEach(function (rule) {

            // lưu lại các rules cho mỗi input
            if (Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.test);
            } else {
                selectorRules[rule.selector] = [rule.test];
            }

            var inputElements = formElement.querySelectorAll(rule.selector);

            Array.from(inputElements).forEach(function (inputElement) {
                // Xử lý trường hợp blur khỏi input
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                };
                // Xử lý mỗi khi người dùng nhập vào input
                inputElement.oninput = function () {
                    var errorElement = getParentElement(inputElement, options.formGroupSelector).querySelector(options.querySelector);
                    errorElement.innerText = '';
                    getParentElement(inputElement, options.formGroupSelector).classList.remove('invalid');
                };
            });
        });
    }

}


Validator.isRequired = function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            return value ? undefined : msg ||
                'Please enter this field!';
        }
    };
};
Validator.isEmail = function (selector, msg) {
    return {
        selector: selector,
        test: function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : msg || 'Email is not formatted correctly!';
        }
    };
};