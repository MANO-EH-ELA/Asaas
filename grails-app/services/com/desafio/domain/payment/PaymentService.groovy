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

    PageRenderer groovyPageRenderer
    def emailService

    public Payment save(Map params) {
        Payment payment = new Payment()
        payment.value = new BigDecimal(params.value)
        payment.status = PaymentStatus.PENDING
        payment.billingType = PaymentMethod.valueOf(params.billingType)
        payment.dueDate = DateUtils.formatStringToDate(params.dueDate, "yyyy-MM-dd")
        payment.customer = Customer.get(Long.valueOf(params.customerId))
        payment.payer = Payer.get(Long.valueOf(params.payerId))
        payment.save(failOnError: true)

        notifyNewPayment(payment)

        return payment
    }

    public Payment confirmPayment(Long paymentId) {
        Payment payment = Payment.get(paymentId)
        payment.status = PaymentStatus.PAID
        payment.paymentDate = new Date()
        payment.save(flush: true, failOnError: true)

        notifyConfirmPayment(payment)
        
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
            setAsOverdue();
        }
    }
    
    public Payment setAsOverdue() { 
        payment.status = PaymentStatus.OVERDUE
        payment.save(failOnError:true)

        notifyOverduePayment(); 
    }


    public void notifyNewPayment(Payment payment) {
        String subject = "Asaas - Nova cobrança"
        emailService.sendEmail(payment.customer.email, subject, groovyPageRenderer.render(template: "/email/createdPaymentCustomerNotification", model: [payment: payment]))
        emailService.sendEmail(payment.payer.email, subject, groovyPageRenderer.render(template: "/email/createdPaymentPayerNotification", model: [payment: payment]))
    }

    public void notifyConfirmPayment(Payment payment) {
        String  subject = "Asaas - Pagamento confirmado"
        emailService.sendEmail(payment.customer.email, subject, groovyPageRenderer.render(template: "/email/confirmedPaymentCustomerNotification", model: [payment: payment]))
        emailService.sendEmail(payment.payer.email, subject, groovyPageRenderer.render(template: "/email/confirmedPaymentPayerNotification", model: [payment: payment]))
    }

    public void notifyOverduePayment(Payment payment) {
        String  subject = "Asaas - Cobrança vencida"
        emailService.sendEmail(payment.payer.email, subject, groovyPageRenderer.render(template: "/email/overduePaymentPayerNotification", model: [payment: payment]))
    }
}
