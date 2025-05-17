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

      document.getElementById("infos-usuario").style.display = "block";
      document.getElementById("doacoes-usuario").style.display = "block";
      document.getElementById("formulario-doacao-usuario").style.display = "block";
      document.getElementById("nome").value = usuario.nome;
      document.getElementById("email").value = usuario.email;
      document.getElementById("id-usuario").value = usuario.id;

      this.preencherTabelaDoacoes(usuario);
      
      return true;
    } else {
      abrirToastErro(error.message || "Usuário não encontrado");
    }

    return false; 
  } catch (error) {
    abrirToastErro(error.message || "Erro ao consultar o usuário");
  }
}

function preencherTabelaDoacoes(usuario) {
  let table = document.getElementById("tabela-doacoes");

  let el = document.createElement("tbody");
  el.id = "tbody-doacoes";

  let tbody = table.appendChild(el);

  for (let i = 0; i < usuario.doacoes.length; i++) {
    let doacao = usuario.doacoes[i];

    var row = tbody.insertRow(i);
    row.insertCell(0).innerHTML = doacao.idDoacao;
    row.insertCell(1).innerHTML = doacao.qtdDoacao + " Kg";
    row.insertCell(2).innerHTML = formatarDataHora(doacao.dataDoacaoFront);
    row.insertCell(3).innerHTML = `<img src="/images/remove-icon.svg" alt="Remover" style="cursor:pointer; width:20px;" onclick="removerDoacao(${doacao.idDoacao})">`;
  }
}

$(document).ready(function () {
  $('#formulario-doacao-usuario').submit(async function(e) {
      e.preventDefault();

      await efetuarDoacao();
  });
});

async function removerDoacao(idDoacao) {
  const doacaoDTO = {
    idUsuario: document.getElementById("id-usuario").value,
    idDoacao: idDoacao,
  };

  try {
    const response = await fetch(`/doacao`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(doacaoDTO),
    });

    if (!response.ok) {
      abrirToastErro("Erro ao remover a doação.");
    }

    const data = await response.json();

    if (data.status === "OK") {
      limparDoacaoGerenciamento()
      alert("Doação removida com sucesso!");
    } else {
      limparDoacaoGerenciamento()
      abrirToastErro(data.mensagem || "Erro ao remover a doação.");
    }
  } catch (error) {
    limparDoacaoGerenciamento()
    abrirToastErro(error.message || "Erro ao remover a doação.");
  }

}


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
      limparDoacaoGerenciamento()
      alert("Doação efetuada com sucesso!");
    } else {
      limparDoacaoGerenciamento()
      abrirToastErro(data.mensagem || "Erro ao efetuar a doação.");
    }
  } catch (error) {
    limparDoacaoGerenciamento()
    abrirToastErro(error.message || "Erro ao efetuar a doação.");
  }
}

function limparDoacaoGerenciamento() {
  document.getElementById("cpf").value = "";
  document.getElementById("formulario-doacao-usuario").style.display = "none";
  document.getElementById("infos-usuario").style.display = "none";
  document.getElementById("doacoes-usuario").style.display = "none";
  document.getElementById("tbody-doacoes").remove();
  document.getElementById("nome").value = "";
  document.getElementById("email").value = "";
  document.getElementById("range-doacao").value = 0;
}