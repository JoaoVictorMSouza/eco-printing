$(document).on("keydown", "form", function(event) { 
    return event.key != "Enter";
});

$(document).ready(function(){
    $('#cpf').mask('000.000.000-00');
});

function formatarDataHora(isoString) {
  const data = new Date(isoString);
  const dia = String(data.getDate()).padStart(2, '0');
  const mes = String(data.getMonth() + 1).padStart(2, '0');
  const ano = data.getFullYear();
  const hora = String(data.getHours()).padStart(2, '0');
  const minuto = String(data.getMinutes()).padStart(2, '0');
  return `${dia}/${mes}/${ano} ${hora}:${minuto}`;
}