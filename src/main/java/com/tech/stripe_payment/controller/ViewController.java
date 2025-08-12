package com.tech.stripe_payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/payment")
	public String getPaymentPage() {
		
		return "payment";
	}
	

}
