package com.example.dddstart.chapter1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShippingInfo {
	private Receiver receiver;
	private Address address;
}
