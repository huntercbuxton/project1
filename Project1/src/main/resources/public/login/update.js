function initAjaxAuthRequest() {
    var form = document.getElementById("login-form");
    form.onsubmit = function() {
        var formData = new FormData(form);
        var action = form.getAttribute('action');
        requestAuth(formData, action);
    }
}

function requestAuth(formData, uri) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var response = JSON.parse(this.responseText);
            if (response['errCode'] !== 0) {
                showLoginErrMessage(response['response']);
            }
        }
    };
    xhr.open('POST', uri);
    xhr.send(formData);
}

function showLoginErrMessage(text) {
    var el = document.getElementById('login-err-msg');
    console.log('err message updated to ' + text);
    el.innerText = text;
}

//function requestSecuredPage() {
//     var xhr = new XMLHttpRequest();
//     xhr.open('GET', '/secure/main');
//     xhr.send();
//}


initAjaxAuthRequest();


//function UpdateInformation() {
//
//  var xhttp = new XMLHttpRequest();
//  xhttp.onreadystatechange = function() {
//    if (this.readyState == 4 && this.status == 200) {
//        var response = JSON.parse(this.responseText);
//        document.getElementById("email").innerText = "Email: "+response.email;
//        document.getElementById("password").innerText = "Password: "+response.password;
//    }
//  };
//  xhttp.open("POST", "/secure/manager", true);
//  xhttp.send();
//}
//UpdateInformation();
