package com.bins.springcloud.shop.common.constants;

public class CommonHelper {
	
	public enum DeleteStatus {
		
		NO_DELETE(0, "未删除"),
		DELETED(1, "删除");
		
		private int code;
		private String msg;
		
		DeleteStatus(int code, String msg) {
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

	}

}
