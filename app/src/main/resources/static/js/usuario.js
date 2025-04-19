$(document).ready(function(){
    $('#cpf').mask('000.000.000-00');
});

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