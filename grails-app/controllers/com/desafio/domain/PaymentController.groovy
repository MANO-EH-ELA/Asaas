package com.desafio.domain

import com.desafio.base.BaseController
import com.desafio.domain.payment.Payment
import com.desafio.domain.payer.Payer
import com.desafio.domain.customer.Customer
import com.desafio.enums.PaymentMethod

import grails.converters.JSON

class PaymentController extends BaseController {
    
    def paymentService

    def index() {  
        Long customerId = params.long("id")
         List<Payment> paymentList = Payment.createCriteria().list(max: getLimitPage(), offset: getCurrentPage()){
            eq("customer", Customer.get(customerId)) 
        }
        [paymentList: paymentList, totalCount: Payment.count()]
    }

    def create() {
        Long customerId = params.long("id")
         List<Payer> payerList = Payer.createCriteria().list() {
            eq("customer", Customer.get(customerId)) 
        }
        [payerList: payerList, totalCount: Payer.count()]
        return [customerId: customerId, payerList: payerList]
    }

    def save() {
        try {
            Payment payment = paymentService.save(params) 
            if (payment.hasErrors()) {
                render([success: false, message: message(code: payment.errors.allErrors[0].defaultMessage ?: payment.errors.allErrors[0].codes[0])] as JSON)
                return
            }
            render([success: true] as JSON)
        } catch (Exception exception) {
            println exception
            render([success: false, message: message(code: 'unknow.error')] as JSON)
        }
    }

    def update() {
        try {
            Long id = params.long("id")
            paymentService.update(params.long("id"), params)
            render([success: true] as JSON)
        } catch (Exception exception) {
            render([success: false, message: message(code:'unknow.error')] as JSON)
        }
    }

    def show() {
        return [payment: Payment.get(params.long('id'))]
    }
}