package com.example.dddstart.chapter1;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Money {
	private int value;
	public Money add(Money money){
		return new Money(this.value + money.value);
	}
	public Money multiply(int multiplier){
		return new Money(value * multiplier);
	}
}
