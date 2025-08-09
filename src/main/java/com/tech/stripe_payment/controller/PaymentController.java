package com.tech.stripe_payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.stripe_payment.DTO.PaymentDTO;
import com.tech.stripe_payment.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/checkout")
	public ResponseEntity<?> doPayment(@RequestBody PaymentDTO paymentDTO){
		
		return ResponseEntity.ok(paymentService.doPayemnt(paymentDTO).toString());
	}
	
	@GetMapping("/success")
	public String successUrl(){
		
		return "Payment Successful";
	}
	
	@GetMapping("/cancelled")
	public String cancelUrl(){
		
		return "Payment Cancelled";
	}
}
