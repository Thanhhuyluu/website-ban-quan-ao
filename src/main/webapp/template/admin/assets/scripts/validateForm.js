var formIsValid = true;

        function validateInput(inputId, errorMessage) {
            const input = document.getElementById(inputId);
            const errorMessageElement = document.getElementById(inputId + "-error");

            if (!input || !errorMessageElement) {
                console.error("Input element or error message element not found");
                return;
            }

            const value = input.value;

            // Kiểm tra email
            if (input.type === 'email') {
                if (!value || !value.match(/^\S+@\S+\.\S+$/)) {
                    errorMessageElement.innerText = errorMessage;
                    input.focus();
                    formIsValid = false; // Đặt trạng thái form là không hợp lệ
                    return;
                }
            }
            // Kiểm tra số điện thoại
            else if (input.type === 'tel') {
                if (!value || !value.match(/^\d{10}$/)) {
                    errorMessageElement.innerText = errorMessage;
                    input.focus();
                    formIsValid = false; // Đặt trạng thái form là không hợp lệ
                    return;
                }
            }

            errorMessageElement.innerText = "";
        }

        function validateForm() {
            formIsValid = true; // Đặt trạng thái form là hợp lệ trước khi kiểm tra
            validateInput('email', 'Email không hợp lệ');
            validateInput('phoneNumber', 'Số điện thoại không hợp lệ');
            return formIsValid; // Trả về trạng thái form (true nếu hợp lệ, ngược lại là false)
        }