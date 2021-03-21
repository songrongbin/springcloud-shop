package com.bins.springcloud.shop.order.enums;

public enum OrderStatusEnum {
	
	UN_PAID(1, "未支付", "未支付"),
	PAID(2, "已支付", "已支付"),
	DELIVERIED(3, "已发货", "已发货"),
	RECEIVING(4, "待收货", "待收货"),
	CONFIRMED(5, "已确认", "已确认"),
	UN_PAID_CANCELLED(6, "已取消", "未支付取消"),
	PAID_CANCELLED(7, "已取消", "支付后取消");
	
	private int code;
	
	private String name;
	
	private String desc;
	
	private OrderStatusEnum(int code, String name, String desc) {
		this.code = code;
		this.name = name;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static String getName(Integer index) {
		if (index == null) {
			return null;
		}
        for (OrderStatusEnum o : OrderStatusEnum.values()) {  
            if (o.getCode() == index) {  
                return o.name;
            }  
        }  
        return null; 
    }

}
