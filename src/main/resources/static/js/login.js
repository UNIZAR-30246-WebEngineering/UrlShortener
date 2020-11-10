
$(document).ready(function () {
    container = $("#container");

    $("#signUp").click(function () {
        container.addClass("right-panel-active");


    });
    $("#signIn").click(function () {
        container.removeClass("right-panel-active");
    });

    $("#login-form").submit(function () {
        console.log("Entro al form de login");
        container.removeClass("right-panel-active");
         $.ajax({
            type: "POST",
            url: "http://localhost:8080/login",
            data: {username: $("#login-username").val(), password: $("#login-password").val()},
            success: function (msg) {
               document.cookie = "uuid=" + msg.uuid;
               console.log("entro al succes de login ");
               window.location.replace("https://localhost:8080/index1.html")
            },
            error: function (msg) {
                console.log("Error de login");
            }
        });

    });

    $("#register").click(function (){
        alert("ENTROOO");
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/register",
            data: {username: $("#register-username").val(), password: $("#register-password").val()},
            success: function (msg) {
               document.cookie = "uuid=" + msg.uuid;
            },
            error: function (msg) {
                alert("MAL");
            }
        });
    });
});

