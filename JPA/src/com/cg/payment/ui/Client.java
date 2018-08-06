package com.cg.payment.ui;


import java.util.Scanner;

import com.cg.payment.bean.Payment;
import com.cg.payment.service.IPaymentService;
import com.cg.payment.service.PaymentService;

public class Client {
	Scanner sc = new Scanner(System.in);
	IPaymentService service = new PaymentService();

	public static void main(String[] args) {
		Payment ac=new Payment();
        IPaymentService service=new PaymentService();
        ac.setName("Rakesh");
        ac.setMobileNo("1111111111");
        ac.setBalance(20000);
        ac.setEmail("rakesh@gmail.com");
	
        try {
			service.createAccount(ac);
		} catch (com.cg.payment.exception.PaymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       try {
		service.deposit("9999999999", 5000.0);
	} catch (com.cg.payment.exception.PaymentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}     
       try {
		service.withdraw("9999999999", 5000.0);
	} catch (com.cg.payment.exception.PaymentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       try {
		service.printTransactionDetails("9999999999");
	} catch (com.cg.payment.exception.PaymentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       try {
		service.fundTransfer("8888888888", "9999999999", 2000);
	} catch (com.cg.payment.exception.PaymentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
