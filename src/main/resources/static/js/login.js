
$(document).ready(function () {
    container = $("#container");

    $("#signUp").click(function () {
        container.addClass("right-panel-active");


    });
    $("#signIn").click(function () {
        container.removeClass("right-panel-active");
    });

    $("#login-button").click(function () {
        if(validateLoginFields($("#login-username"), $("#login-password"))) {
            container.removeClass("right-panel-active");
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/login",
                data: {username: $("#login-username").val(), password: $("#login-password").val()},
                success: function (msg) {
                    document.cookie = "uuid=" + msg.uuid;
                    console.log("entro al success de login ");
                    window.location.replace("http://localhost:8080/index2.html")
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    if (jqXHR.status === 403) {
                        console.log("Entro al error de login");
                        var feedbackDiv = $("#login-feedback");
                        feedbackDiv.empty();
                        feedbackDiv.html("El usuario o la contraseña erróneos");
                    }
                }
            });
        }
    });

    $("#register").click(function (){
        if(validateLoginFields($("#register-username").val(), $("#register-password").val()))
        {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/register",
                data: {username: $("#register-username").val(), password: $("#register-password").val()},
                success: function (msg) {
                    document.cookie = "uuid=" + msg.uuid;
                    window.location.replace("http://localhost:8080/index2.html")
                },
                error: function (msg) {

                }
            });
        }
    });
});

function validateLoginFields(login, password){
    var ok = true;
    console.log("ENTRO");
    if(login.val() === ""){

        login.addClass("invalid");
        login.attr("placeholder", "Usuario vacío");
        ok = false;
    } else {
        login.removeClass("invalid");
    }

    if(password.val() === ""){
        password.addClass("invalid");
        password.attr("placeholder", "Contraseña vacía");
        ok = false;
    } else {
        password.removeClass("invalid");
    }

    return ok;
}

