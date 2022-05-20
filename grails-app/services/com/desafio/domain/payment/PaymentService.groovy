package com.desafio.domain

import com.desafio.domain.payment.Payment
import com.desafio.domain.payer.Payer
import com.desafio.domain.customer.Customer
import com.desafio.enums.PaymentMethod
import com.desafio.enums.PaymentStatus
import com.desafio.utils.FormatDateUtils

import grails.gorm.transactions.Transactional 


@Transactional
class PaymentService {

    public Payment save(Map params) {
        println(params)
        Payment payment = new Payment()
        payment.value = new BigDecimal(params.value)
        payment.status = PaymentStatus.PENDING
        payment.method = PaymentMethod.valueOf(params.method)
        payment.dueDate = FormatDateUtils.toDate(params.dueDate, "yyyy-mm-dd")
        payment.customer = Customer.get(params.long("customerId"))
        payment.payer = Payer.get(params.long("payerId"))
        payment.save(failOnError: true)
        return payment
     }

    public Payment update(Long id, Map params) {
        if (!id) throw new Exception("Erro ao realizar atualização de informação")
        Payment payment = Payment.get(id)
        payment.value = params.value
        payment.method = params.method
        payment.status = params.status
        payment.dueDate = params.dueDate
        payment.customer = params.customer
        payment.payer = params.payer
        payment.save(failOnError: true)
        return payment
    }
}