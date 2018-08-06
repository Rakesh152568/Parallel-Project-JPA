package com.cg.payment.service;

import java.time.LocalDateTime;

import java.util.HashMap;

import com.cg.payment.bean.Payment;
import com.cg.payment.dao.IPaymentDao;
import com.cg.payment.dao.PaymentDao;
import com.cg.payment.exception.PaymentException;

public class PaymentService implements IPaymentService {
	IPaymentDao dao = new PaymentDao();

	public String createAccount(Payment acc) throws PaymentException {
		if (!acc.getMobileNo().matches("\\d{10}")) {
			throw new PaymentException("Mobile number should contain 10 digits");
			}
			if (acc.getName().isEmpty() || acc.getName() == null) {
			throw new PaymentException("Name cannot be empty");
			} else {
			if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {
			throw new PaymentException(
			"Name should start with capital letter and should contain only alphabets");
			}
			}
			if (acc.getBalance() < 0) {
			throw new PaymentException("Balance should be greater than zero");
			}
			if (!acc.getEmail().matches("[a-z]+@[a-z]+\\.com")) {
			throw new PaymentException("Enter valid emailid");
			}

			return dao.createAccount(acc);
	}

	public double showBalance(String mobileNo) throws PaymentException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaymentException("Mobile number should contain 10 digits");
			}
			return dao.showBalance(mobileNo);
	}

	public Payment deposit(String mobileNo, double depositAmount) throws PaymentException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new PaymentException("Mobile number should contain 10 digits");
			}
			//Account acc=dao.findOne(mobileNo);
			if (depositAmount <= 0) {
			throw new PaymentException(
			"Deposit amount must be greater than zero");
			}
			 
			return dao.deposit(mobileNo,depositAmount);
			//acc1.getBalance();
	}

	public Payment withdraw(String mobileNo, double withdrawAmount) throws PaymentException {
		if (!mobileNo.matches("\\d{10}")) {
					throw new PaymentException("Mobile number should contain 10 digits");
				}
				//Account acc=dao.findOne(mobileNo);
				//if (withdrawAmt > acc.getBalance() || withdrawAmt <= 0) {
				if ( withdrawAmount <= 0) {
				throw new PaymentException(
				"The amount to be withdrawn should be greater than available balance and greater than zero");
				}
				//acc.setBalance(acc.getBalance() - withdrawAmt);
				// acc.setDate(LocalDateTime.now());
				Payment acc1 = dao.withdraw(mobileNo,withdrawAmount);
				return acc1;
	}

	public boolean fundTransfer(String sourceMobileNo, String destinationMobileNo, double transferAmount)
			throws PaymentException {
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new PaymentException("Mobile number should contain 10 digits");
			}
			if (!destinationMobileNo.matches("\\d{10}")) {
			throw new PaymentException("Mobile number should contain 10 digits");
			}
			IPaymentService service = new PaymentService();
			Payment acc1 = service.withdraw(sourceMobileNo, transferAmount);
			Payment d2 = service.deposit(destinationMobileNo, transferAmount);
			return true;
	}

	public Payment printTransactionDetails(String mobileNo) throws PaymentException {
		return dao.printTransactionDetails(mobileNo);
	}

	
	
	
}


