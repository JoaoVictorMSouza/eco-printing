$(document).ready(function(){
    $('.cep').mask('00000-000');
});

async function consultarCepUsuario() {
    let cep = document.getElementById("cep").value;
    if (cep.length === 9) {
        let endereco = await consultarCep(cep);
        if (endereco) {
            document.getElementById("logradouro").value = endereco.logradouro;
            document.getElementById("bairro").value = endereco.bairro;
            document.getElementById("cidade").value = endereco.localidade;
            document.getElementById("uf").value = endereco.uf;
        } else {
            document.getElementById("logradouro").value = "";
            document.getElementById("bairro").value = "";
            document.getElementById("cidade").value = "";
            document.getElementById("uf").value = "";
        }
    }
}

function consultarCep(cep) {
    return fetch(`https://viacep.com.br/ws/${cep}/json/`)
        .then(response => response.json())
        .then(data => {
            if (data.erro) {
                abrirToastErro('CEP não encontrado.');
                return undefined;
            }
            return data;
        })
        .catch(error => {
            abrirToastErro('Erro ao consultar o CEP.');
            return undefined;
        });
}

function validarEndereco() {
    let cepEntrega = document.getElementById("cep").value;
    let logradouroEntrega = document.getElementById("logradouro").value;
    let numeroEntrega = document.getElementById("numero").value;
    let bairroEntrega = document.getElementById("bairro").value;
    let cidadeEntrega = document.getElementById("cidade").value;
    let ufEntrega = document.getElementById("uf").value;

    if (cepEntrega === "") {
        abrirToastErro("CEP de entrega é obrigatório.");
        return false;
    }

    if (logradouroEntrega === "") {
        abrirToastErro("Logradouro de entrega é obrigatório.");
        return false;
    }

    if (numeroEntrega === "") {
        abrirToastErro("Número de entrega é obrigatório.");
        return false;
    }

    if (bairroEntrega === "") {
        abrirToastErro("Bairro de entrega é obrigatório.");
        return false;
    }

    if (cidadeEntrega === "") {
        abrirToastErro("Cidade de entrega é obrigatório.");
        return false;
    }

    if (ufEntrega === "") {
        abrirToastErro("UF de entrega é obrigatório.");
        return false;
    }

    return true;
}

async function editarEndereco() {
    let endereco = {
        idUsuario: $("#id-usuario").val(),
        idEndereco: $("#id-endereco").val(),
        cep: $("#cep").val(),
        logradouro: $("#logradouro").val(),
        numero: $("#numero").val(),
        complemento: $("#complemento").val(),
        bairro: $("#bairro").val(),
        cidade: $("#cidade").val(),
        uf: $("#uf").val()
    }

    try {
        const response = await fetch("/endereco/editar", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(endereco)
        });

        const data = await response.json();

        if (response.ok && data.status === "OK") {
            return true;
        } else {
            abrirToastErro(data.mensagem || "Erro ao editar endereço.");
        }

        return false;
    } catch (error) {
        abrirToastErro(error.message || "Erro ao editar endereço.");
        return false;
    }
}