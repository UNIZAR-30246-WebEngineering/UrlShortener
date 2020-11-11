$(document).ready(
    function() {
        $("#shortener").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/link",
                    data: $(this).serialize(),
                    success: function(msg) {
                        $("#result").html(
                            "<div class='alert alert-success lead'><a target='_blank' href='" +
                            msg.uri +
                            "'>" +
                            msg.uri +
                            "</a></div>");
                    },
                    error: function() {
                        $("#result").html(
                            "<div class='alert alert-danger lead'>ERROR</div>");
                    }
                });
            });


        $("#csv").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/csv",
                    data: new FormData(this),
                    enctype: 'multipart/form-data',
                    processData: false,
                    contentType: false,
                    cache: false,
                    success: function(msg) {
                        $("#result").html(
                            "<div class='alert alert-success lead'>OK</div>");
                    },
                    error: function() {
                        $("#result").html(
                            "<div class='alert alert-danger lead'>ERROR</div>");
                    }
                });
            });
    });