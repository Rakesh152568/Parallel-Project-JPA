package com.cg.payment.dao;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;

public interface IPaymentDao {
	String createAccount(Payment acc) throws PaymentException;
	double showBalance(String mobileNo) throws PaymentException;
	Payment deposit(String mobileNo,double depAmount) throws PaymentException;
	Payment withdraw(String mobileNo,double withdraw) throws PaymentException;
	Payment printTransactionDetails(String mobileNo) throws PaymentException;

}
