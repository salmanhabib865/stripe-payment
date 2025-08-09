package com.tech.stripe_payment.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionCreateParams.LineItem;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData;
import com.stripe.param.checkout.SessionCreateParams.LineItem.PriceData.ProductData;
import com.stripe.param.checkout.SessionCreateParams.Mode;
import com.stripe.param.checkout.SessionCreateParams.PaymentMethodType;
import com.tech.stripe_payment.DTO.PaymentDTO;

@Service
public class PaymentService {

	
	
	public JsonObject doPayemnt(PaymentDTO paymentDTO) {
		
		// Create the line item from DTO
        LineItem lineItem = LineItem.builder()
            .setQuantity(paymentDTO.getQuantity())
            .setPriceData(
                PriceData.builder()
                    .setCurrency(paymentDTO.getUnit().toLowerCase()) // e.g. "usd"
                    .setUnitAmount(paymentDTO.getAmount()) // in smallest currency unit (cents for USD)
                    .setProductData(
                        ProductData.builder()
                            .setName(paymentDTO.getName())
                            .build()
                    )
                    .build()
            )
            .build();
		
		SessionCreateParams sessionCreateParams = SessionCreateParams.builder()
				.addPaymentMethodType(PaymentMethodType.CARD)
				.setCustomerEmail(paymentDTO.getEmail())
				.setMode(Mode.PAYMENT)
				.setSuccessUrl("http://localhost:8085/api/payment/success")
				.setCancelUrl("http://localhost:8085/api/payment/cancelled")
				.addLineItem(lineItem).build();
		Session session = null;
		
		try {
			 session=Session.create(sessionCreateParams);
		} catch (StripeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JsonObject sessionObj = session.getRawJsonObject();
		return sessionObj;
		
		
		//session.setLineItems(null);
		
		
		
		
	}
}
