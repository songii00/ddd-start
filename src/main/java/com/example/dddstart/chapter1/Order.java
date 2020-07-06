package com.example.dddstart.chapter1;

import java.util.List;

public class Order {
	private OrderState state;
	private ShippingInfo shippingInfo;
	private List<OrderLine> orderLines;
	private int totalAmount;

	public Order(List<OrderLine> orderLines, ShippingInfo shippingInfo){
		setOrderLines(orderLines);
		setShippingInfo(shippingInfo);
	}

	private void setShippingInfo(ShippingInfo shippingInfo) {
		if(shippingInfo == null){
			throw new IllegalArgumentException("no shipping info");
		}
		this.shippingInfo = shippingInfo;
	}

	private void setOrderLines(List<OrderLine> orderLines) {
		verifyAtLeastOneOrMoreOrderLines(orderLines);
		this.orderLines = orderLines;
		calculateTotalAmounts();
	}

	private void calculateTotalAmounts() {
		this.totalAmount = orderLines.stream().mapToInt(x -> x.getAmounts().getValue()).sum();

	}

	private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
		if(orderLines == null || orderLines.isEmpty()){
			throw new IllegalArgumentException("no order line");
		}
	}

	public void changeShippingInfo(ShippingInfo shippingInfo){
		verifyNotYetShipped();
		setShippingInfo(shippingInfo);
	}

	private void verifyNotYetShipped() {
		if(state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING){
			throw new IllegalArgumentException("already shipped!");
		}
	}

	public void changeShipped(){
		//로직 검사
		this.state = OrderState.SHIPPED;
	}

	// 업무관련 규칙을 주문 도메인 모델인 Order 이나 OrderState 에서 구현
	private boolean isShippingChangeable() {
		return state == OrderState.PAYMENT_WAITING || state == OrderState.PREPARING;
	}

	public enum OrderState{
		PAYMENT_WAITING,
		PREPARING,
		SHIPPED,
		DELIVERING,
		DELIVERY_COMPLETED,
		CANCELD;

		public boolean isShippingChangeable(){
			return false;
		}
	}

	public class OrderNo {
		private int id;
	}

}
