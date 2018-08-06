package com.cg.payment.service;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;

public interface IPaymentService {
	String createAccount(Payment acc) throws PaymentException;
	double showBalance(String mobileNo) 
			throws PaymentException;
	Payment deposit(String mobileNo,double depositAmount) throws PaymentException;
	Payment withdraw(String mobileNo,double withdrawAmount) throws PaymentException;
	boolean fundTransfer(String sourceMobileNo,String destinationMobileNo,double transferAmount)
			throws PaymentException;
	Payment printTransactionDetails(String mobileNo) throws PaymentException;

}
