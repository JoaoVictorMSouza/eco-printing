$(document).on("keydown", "form", function(event) { 
    return event.key != "Enter";
});

$(document).ready(function(){
    $('#cpf').mask('000.000.000-00');
});