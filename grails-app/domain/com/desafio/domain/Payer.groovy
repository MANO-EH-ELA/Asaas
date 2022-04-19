package com.desafio.domain.payer


class Payer {

    String nome
    String cpf
    String endereco
    String bairro
    String cidade
    String estado
    Integer numero
    Integer cep
    String email 
    String telefone


    static constraints = {
        nome size: 5..50, blank: false
        email size: 5..50, blank: false
    }
}