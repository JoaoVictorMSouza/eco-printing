function validarCPF(numeroCpf) {
    const cpf = numeroCpf.replace(/[^\d]+/g, '');
    if (cpf.length !== 11 || /^(\d)\1+$/.test(cpf)) {
        abrirToastErro('CPF inválido');
        return false;
    }

    let soma = 0;
    let resto;

    for (let i = 1; i <= 9; i++) {
        soma += parseInt(cpf.substring(i - 1, i)) * (11 - i);
    }
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.substring(9, 10))) {
        abrirToastErro('CPF inválido');
        return false;
    }

    soma = 0;
    for (let i = 1; i <= 10; i++) {
        soma += parseInt(cpf.substring(i - 1, i)) * (12 - i);
    }
    resto = (soma * 10) % 11;
    if (resto === 10 || resto === 11) resto = 0;
    if (resto !== parseInt(cpf.substring(10, 11))) {
        abrirToastErro('CPF inválido');
        return false;
    }

    return true;
}

function validarSenha() {
    let senha = document.getElementById("senha").value;
    let confirmacaoSenha = document.getElementById("confirmacaoSenha").value;
    if (senha !== confirmacaoSenha) {
        abrirToastErro("As senhas não coincidem.");
        return false;
    }
    return true;
}

function stringToDate(dateString) {
    // Split the string into components
    const parts = dateString.split('-');
    // Create a new Date object (month is zero-indexed, so subtract 1)
    const date = new Date(parts[0], parts[1] - 1, parts[2]);
    return date;
}

$(document).ready(function () {
    $('#formulario-criar-usuario').submit(async function(e) {
        e.preventDefault();

        let isInformacoesValidas = await validarInformacoesCriarUsuario();

        if (isInformacoesValidas) {
            criarUsuario();
        }
    });
});

$(document).ready(function () {
    $('#formulario-editar-usuario').submit(async function(e) {
        e.preventDefault();

        var idUsuario = $("#id-usuario").val();

        let isInformacoesUsuarioValidas = await validarInformacoesEditarUsuario();

        let isInformacoesEnderecoValidas = validarEndereco();

        if (isInformacoesUsuarioValidas && isInformacoesEnderecoValidas) {
            var statusEditarUsuario = await editarUsuario();
            var statusEditarEndereco = await editarEndereco();
            if(statusEditarUsuario && statusEditarEndereco) {
                window.location.href = "/usuario/editar/" + idUsuario;
            }
        }
    });
});

async function validarEmail() {
    const email = document.getElementById("email").value;
    return new Promise((resolve, reject) => {
        $.get(`/usuario/consultarEmail?email=${encodeURIComponent(email)}`, function(data) {
            if (data.emailExiste) {
                abrirToastErro("Email já cadastrado!");
                resolve(false);
            } else {
                resolve(true);
            }
        }).fail(function() {
            reject(new Error("Erro ao consultar o email"));
        });
    });
}

async function validarInformacoesCriarUsuario(){
    try {
        fecharToast();

        let nome = document.getElementById("nome").value;
        let cpf = document.getElementById("cpf").value;
        let email = document.getElementById("email").value;
        let senha = document.getElementById("senha").value;
        let confirmacaoSenha = document.getElementById("confirmacaoSenha").value;
        let genero = document.getElementById("genero-usuario").value;
        let dataNascimento = document.getElementById("data-nascimento").value;
    
        if (nome === ""){
            abrirToastErro("Nome é obrigatório.");
            return false;
        }
        if (cpf === "" ){
            abrirToastErro("CPF é obrigatório.");
            return false;
        }

        if (!validarCPF(cpf)) {
            return false;
        }
    
        if (email === ""){
            abrirToastErro("Email é obrigatório.");
            return false;
        }

        let isEmailValido = await validarEmail();
        if (!isEmailValido) {
            return false;
        }
    
        if (senha === ""){
            abrirToastErro("Senha é obrigatória.");
            return false;
        }
    
        if (confirmacaoSenha === ""){
            abrirToastErro("Confirmação de senha é obrigatória.");
            return false;
        }

        if (!validarSenha()) {
            return false;
        }
    
        if (genero === "" || Number(genero) <= 0){
            abrirToastErro("Gênero é obrigatório.");
            return false;
        }

        if (dataNascimento === ""){
            abrirToastErro("Data de nascimento é obrigatória.");
            return false;
        }

        if (stringToDate(dataNascimento) > new Date()) {
            abrirToastErro("Data de nascimento inválida.");
            return false;
        }

        if (!validarEndereco()) {
            return false;
        }
    
        return true;
    } catch (error) {
        return false;
    }
}

async function validarInformacoesEditarUsuario(){
    try {
        fecharToast();

        let nome = document.getElementById("nome").value;
        let genero = document.getElementById("genero-usuario").value;
        let dataNascimento = document.getElementById("data-nascimento").value;
    
        if (nome === ""){
            abrirToastErro("Nome é obrigatório.");
            return false;
        }
    
        if (genero === "" || Number(genero) <= 0){
            abrirToastErro("Gênero é obrigatório.");
            return false;
        }

        if (dataNascimento === ""){
            abrirToastErro("Data de nascimento é obrigatória.");
            return false;
        }

        if (stringToDate(dataNascimento) > new Date()) {
            abrirToastErro("Data de nascimento inválida.");
            return false;
        }
    
        return true;
    } catch (error) {
        return false;
    }
}

function criarUsuario() {
    let endereco = {
        cep: $("#cep").val(),
        logradouro: $("#logradouro").val(),
        numero: $("#numero").val(),
        complemento: $("#complemento").val(),
        bairro: $("#bairro").val(),
        cidade: $("#cidade").val(),
        uf: $("#uf").val()
    }

    let usuario = {
        nome: $("#nome").val(),
        cpf: $("#cpf").val(),
        email: $("#email").val(),
        senha: $("#senha").val(),
        confirmacaoSenha: $("#confirmacaoSenha").val(),
        idGeneroUsuario: $("#genero-usuario").val(),
        dataNascimento: stringToDate($("#data-nascimento").val()),
        enderecos: [endereco]
    }

    let url = "/usuario/criar";

    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(usuario),
        success: function(data) {
            if (data) {
                if (data.status === "OK") {
                    window.location.href = "/login";
                } else {
                    abrirToastErro(data.mensagem);
                }
            } else {
                abrirToastErro("Erro ao inserir usuário.");
            }
        },
        error: function(data) {
            if (data.responseText) {
                abrirToastErro(data.responseText);
            }
        }
    });
}

async function editarUsuario() {
    let usuario = {
        id: $("#id-usuario").val(),
        nome: $("#nome").val(),
        idGeneroUsuario: $("#genero-usuario").val(),
        dataNascimento: stringToDate($("#data-nascimento").val())
    }

    try {
        const response = await fetch("/usuario/editar", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(usuario)
        });

        const data = await response.json();

        if (response.ok && data.status === "OK") {
            return true;
        } else {
            abrirToastErro("Erro ao editar usuário.");
        }

        return false;
    } catch (error) {
        abrirToastErro("Erro ao editar usuário.");
        return false;
    }
}