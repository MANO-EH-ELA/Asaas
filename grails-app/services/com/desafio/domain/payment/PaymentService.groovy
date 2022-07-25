package com.desafio.domain

import com.desafio.domain.payment.Payment
import com.desafio.domain.payer.Payer
import com.desafio.domain.customer.Customer
import com.desafio.enums.PaymentMethod
import com.desafio.enums.PaymentStatus
import com.desafio.utils.DateUtils

import grails.gorm.transactions.Transactional 
import grails.plugin.asyncmail.AsynchronousMailService
import grails.gsp.PageRenderer

@Transactional
class PaymentService {

    def paymentNotificationService

    public Payment save(Customer customer, Map params){
        Payment payment = new Payment() 
        payment.customer = customer 
        payment.value = new BigDecimal(params.value) 
        payment.dueDate = DateUtils.formatStringToDate(params.dueDate, "yyyy-MM-dd")
        payment.status = PaymentStatus.PENDING
        payment.method = PaymentMethod.valueOf(params.method)
        payment.payer = Payer.get(Long.valueOf(params.payerId))
        payment.save(failOnError: true)
        
        paymentNotificationService.notifyCreatedPayment(payment)
        
        return payment
    }

    public List<Payment> listStatus(PaymentStatus paymentStatus, Date dueDate) {
        List<Payment> paymentList = Payment.createCriteria().list() {
            eq("status", paymentStatus)
            le("dueDate", dueDate)
        }
        return paymentList
    }

    public Payment updateToOverdue() {
        Date dueDate = DateUtils.getYesterday()
        List<Payment> paymentList = listStatus(PaymentStatus.PENDING, dueDate)
        for (Payment payment : paymentList) {
            setAsOverdue()
        }
    }
    
    public Payment setAsOverdue(Long paymentId) { 
        Payment payment = Payment.get(paymentId)
        payment.status = PaymentStatus.OVERDUE
        payment.save(failOnError:true)

        paymentNotificationService.notifyOverduePayment() 
    }
}