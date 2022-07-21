package com.desafio.domain

import com.desafio.domain.payer.Payer
import grails.gorm.transactions.Transactional
import com.desafio.domain.customer.Customer

import grails.plugin.springsecurity.annotation.Secured

@Transactional
class PayerService {

    def springSecurityService

    public Payer save(Customer customer, Map params) {
        Payer payer = new Payer(params)
        payer.customer = springSecurityService.getCurrentUser().customer 
        payer.name = params.name
        payer.email = params.email
        payer.cpfCnpj = params.cpfCnpj 
        payer.phone = params.phone 
        payer.postalCode = params.postalCode
        payer.address = params.address
        payer.addressNumber = params.addressNumber
        payer.province = params.province
        payer.city = params.city
        payer.state = params.state 
        payer.save(failOnError: true)
        return payer
    }
    
    public Payer update(Long payerId, Map params) {
        if (!payerId) throw new Exception("Erro ao realizar atualização de informação")
        Payer payer = Payer.get(params.long("payerId"))
        payer.name = params.name
        payer.cpfCnpj = params.cpfCnpj
        payer.address = params.address
        payer.province = params.province
        payer.city = params.city
        payer.state = params.state
        payer.addressNumber = params.addressNumber
        payer.postalCode = params.postalCode
        payer.email = params.email
        payer.phone = params.phone
        payer.save(failOnError: true)
        return payer
    }
}
