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