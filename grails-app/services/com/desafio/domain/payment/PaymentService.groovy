package com.desafio.domain

import com.desafio.domain.payer.Payer
import com.desafio.domain.customer.Customer
import com.desafio.enums.PaymentMethod
import com.desafio.enums.PaymentStatus
import grails.gorm.transactions.Transactional 

@Transactional
class PaymentService {

    public Payment save(Map params) {
        Payment payment = new Payment()
        payment.value = new BigDecimal(params.value)
        payment.status = PaymentStatus.PENDING
        payment.billingType = PaymentMethod.valueOf(params.billingType)
        payment.dueDate = DateUtils.formatStringToDate(params.dueDate, "yyyy-MM-dd")
        payment.customer = Customer.get(Long.valueOf(params.customerId))
        payment.payer = Payer.get(Long.valueOf(params.payerId))
        payment.save(failOnError: true)
        return payment
    }

    public Payment confirmPayment(Long paymentId) {
        Payment payment = Payment.get(paymentId)
        payment.status = PaymentStatus.PAID
        payment.save(failOnError: true)
        return payment
    }

    public List<Payment> list(PaymentStatus paymentStatus, Date yesterday) {
        List<Payment> paymentList = Payment.createCriteria().list() {
            eq("status", paymentStatus)
            le("dueDate", yesterday)
        }
        return paymentList
    }

    public Payment updateToOverdue() {
        Date yesterday = DateUtils.getYesterday()
        List<Payment> paymentList = list(PaymentStatus.PENDING, yesterday)
        for (Payment payment : paymentList) {
            payment.status = PaymentStatus.OVERDUE
            payment.save(failOnError:true)
        }
    }
}