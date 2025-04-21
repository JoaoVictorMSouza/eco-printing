var slider = document.getElementById("range-doacao");
var output = document.getElementById("valor-doacao");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}