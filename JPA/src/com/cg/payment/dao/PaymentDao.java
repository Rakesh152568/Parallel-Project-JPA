package com.cg.payment.dao;

import java.sql.Connection;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.payment.bean.Payment;
import com.cg.payment.exception.PaymentException;

public class PaymentDao implements IPaymentDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
	 
	EntityManager em = emf.createEntityManager();
	public String createAccount(Payment acc) throws PaymentException {
		em.getTransaction().begin();
		em.persist(acc);
		em.getTransaction().commit();
		return acc.getMobileNo();
	}

	public double showBalance(String mobileNo) throws PaymentException {
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Payment> query=em.createQuery(str,Payment.class);
		query.setParameter(1,mobileNo);
		Payment ac=query.getSingleResult();
		if(mobileNo.equals(ac.getMobileNo())) {
		return ac.getBalance();
		}else {
		throw new PaymentException("number doesnot exists");
		}
	}

	public Payment deposit(String mobileNo, double depAmount) throws PaymentException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Payment> query=em.createQuery(str,Payment.class);
		query.setParameter(1,mobileNo);
		Payment ac=query.getSingleResult();
		
		if(ac==null) {
		throw new PaymentException("does not exists");
		}
		double d=ac.getBalance()+depAmount;
		ac.setBalance(d);
		em.merge(ac);
		 
		 
		em.getTransaction().commit();
		return ac;
		 
	}

	public Payment withdraw(String mobileNo, double withdraw) throws PaymentException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileNo=?";
		TypedQuery<Payment> query=em.createQuery(str,Payment.class);
		query.setParameter(1,mobileNo);
		Payment ac=query.getSingleResult();
		if(ac==null) {
		throw new PaymentException("does not exists");
		}
		double d=ac.getBalance()-withdraw;
		ac.setBalance(d);
		em.merge(ac);
		 
		 
		em.getTransaction().commit();
		return ac;
	}

	public Payment printTransactionDetails(String mobileNo) throws PaymentException {
			String str="select a from Account a where a.mobileNo=?";
			TypedQuery<Payment> query=em.createQuery(str,Payment.class);
			query.setParameter(1,mobileNo);
			Payment ac=query.getSingleResult();
			if(ac!=null) {
			return ac;
			}else {
			throw new PaymentException("number doesnot exists");
			}
			}
	

	 
	}
	



