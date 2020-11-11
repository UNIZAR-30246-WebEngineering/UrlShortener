

var lines = [];
var num_pending_request = 10;
var num_processed_lines = 0;
var retval = "";

$(document).ready(function () {
    getData();
    $(".btn-get-started").click(function () {
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/link",
            data: {url: $("#id-url-input").val(), uuid: getCookie("uuid")},
            success: function (msg) {
                msg.clicks = 0;
                appendRow(msg);
            },
            error: function (msg, textStatus, xhr) {
                console.log(xhr.status)
                var feedbackDiv = $("#feedback");
                feedbackDiv.empty();
                feedbackDiv.html("No se puede recortar esa url");
            }
        });
    });

    $(function(){
        $("#upload_link").click(function(){
            $("#upload:hidden").trigger('click');

        });
    });

    $("#upload").change(function(e) {
        var ext = $("#upload").val().split(".").pop().toLowerCase();
        if($.inArray(ext, ["csv"]) == -1) {
            alert('Upload CSV');
            return false;
        }
        if (e.target.files != undefined) {
            var reader = new FileReader();
            reader.onload = function(e) {
                lines = e.target.result.split('\n');
                getShortURL();
            };
            reader.readAsText(e.target.files.item(0));
        }
        return false;
    });
});

function appendRow(msg){
    var markup = "<tr><td class=\"first-column\"><a href=http://" + msg.target+ ">" + msg.target +"</td>" +
        "<td><a href=" + msg.uri + ">" +msg.uri + "</a></td><td class=\"last-column\">" +msg.clicks + "</td></tr>";
    var tableBody = $("tbody");
    tableBody.append(markup);
    $("#feedback").empty();
}


function getShortURL() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/link",
        data: {url: lines[num_processed_lines] , uuid: getCookie("uuid")},
        success: function (msg) {
            num_processed_lines ++;
            console.log(num_processed_lines + "  " + msg.uri);
            retval += msg.uri + "\n";
            msg.clicks = 0;
            appendRow(msg);
            if (num_processed_lines < lines.length) {
                getShortURL();
            }else{
                download("result.csv", retval);
            }


        },
        error: function (msg) {
            num_processed_lines ++;
            console.log(num_processed_lines + "  ERROR   ");
            if (num_processed_lines < lines.length) {
                getShortURL();
            }else{
                download("result.csv", retval);
            }
        }
    });
}

function download(filename, text) {
    var element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
    element.setAttribute('download', filename);

    element.style.display = 'none';
    document.body.appendChild(element);

    element.click();

    document.body.removeChild(element);
}

function getData(){

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/userlinks",
        data: {uuid:getCookie("uuid")},
        success: function (msg) {
            links = msg.urlList;
            for(var i = 0; i < links.length; i ++){
                appendRow(links[i]);
                console.log(links[i].uri);
            }
        },
        error: function (msg) {
            alert("MAL");
        }
    });
}