package com.bins.springcloud.shop.user.common;

public class UserHelper {
	
	public enum Status {
		NO_ACTIVITY(0, "未激活"),
		NORMAL(1, "正常"),
		DISABLE(2, "禁用"),
		ABNORMAL(3, "异常"),
		FROZEN15M(4, "冻结15分钟"),
		FROZEN30M(5, "冻结30分钟"),
		FROZEN24H(6, "冻结24小时"),
		DELETE(7, "删除");
		
		private int code;
		private String msg;
		
		Status(int code, String msg) {
			this.code = code;
			this.msg = msg;
		}

		public int getCode() {
			return code;
		}

		public String getMsg() {
			return msg;
		}
		
		@Override
		public String toString() {
			return this.code + "," + this.msg;
		}
		
		public static String getStatusName(int code) {
			for (Status status : Status.values()) {
				if (code == status.code) {
					return status.msg;
				}
			}
			return "";
		}

	}
}