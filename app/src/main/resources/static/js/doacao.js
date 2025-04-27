var slider = document.getElementById("range-doacao");
var output = document.getElementById("valor-doacao");
output.innerHTML = slider.value;

slider.oninput = function() {
  output.innerHTML = this.value;
}

async function consultarCpfUsuario() {
  var cpf = document.getElementById("cpf").value;

  try {
    const response = await fetch(`/usuario/consultarUsuarioByCpf?cpf=${encodeURIComponent(cpf)}`);
    const data = await response.json();

    if (data.valido) {
      const usuario = data.usuario;
      // Aqui você pode fazer o que quiser com os dados do usuário, como exibi-los na tela
      document.getElementById("infos-usuario").style.display = "block";
      document.getElementById("nome").value = usuario.nome;
      document.getElementById("email").value = usuario.email;
      document.getElementById("id-usuario").value = usuario.id;
      // Adicione mais campos conforme necessário
      return true;
    } else {
      abrirToastErro(error.message || "Usuário não encontrado");
    }

    return false; 
  } catch (error) {
    abrirToastErro(error.message || "Erro ao consultar o usuário");
  }
}

$(document).ready(function () {
  $('#formulario-doacao-usuario').submit(async function(e) {
      e.preventDefault();

      await efetuarDoacao();
  });
});


async function efetuarDoacao() {
  const doacaoDTO = {
    idUsuario: document.getElementById("id-usuario").value,
    qtdDoacao: document.getElementById("range-doacao").value,
  };

  try {
    const response = await fetch("/doacao", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(doacaoDTO),
    });

    if (!response.ok) {
      abrirToastErro("Erro ao efetuar a doação." );
    }

    const data = await response.json();

    if (data.status === "OK") {
      limparDoacao()
      alert("Doação efetuada com sucesso!");
    } else {
      limparDoacao()
      abrirToastErro(data.mensagem || "Erro ao efetuar a doação.");
    }
  } catch (error) {
    limparDoacao()
    abrirToastErro(error.message || "Erro ao efetuar a doação.");
  }
}

function limparDoacao() {
  document.getElementById("cpf").value = "";
  document.getElementById("infos-usuario").style.display = "none";
  document.getElementById("nome").value = "";
  document.getElementById("email").value = "";
  document.getElementById("range-doacao").value = 0;
}