package com.nattav.model;

import com.nattav.model.common.CommonStatusObject;

public class RestAPIDataObject {

	private CommonStatusObject status;
	private Object data;
	public CommonStatusObject getStatus() {
		return status;
	}
	public void setStatus(CommonStatusObject status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
//	public CommonResponse(ResponseStatusObject status, Object data) {
//		super();
//		this.status = status;
//		this.data = data;
//	}
}
