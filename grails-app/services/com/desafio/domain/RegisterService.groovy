package com.desafio.domain

import com.desafio.domain.User
import com.desafio.domain.Role
import com.desafio.domain.UserRole
import com.desafio.domain.CustomUserDetails
import com.desafio.domain.customer.Customer

import grails.validation.ValidationException
import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.converters.JSON

@Transactional
class RegisterService {

    public register(Map params) {
        Customer customer = springSecurityService.getCurrentUser().customer  
        User user = connectUser(params.username, params.password, customer)
        connectRoleUser(user)
        return user
    }

    public User connectUser(String username, String password, Customer customer) {
        User user = new User()
        user.username = username
        user.password = password
        user.customer = springSecurityService.getCurrentUser().customer  
        user.save(flush: true, failOnError: true)
        return user
    }

    public Customer connectCustomer(String email) {
        Customer customer = springSecurityService.getCurrentUser().customer  
        customer.email = email
        customer.save(flush: true, failOnError: true)
    }

        public connectRoleUser(User user) {
        def role = Role.get(2)
        UserRole.create(user, role)
        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }

}
