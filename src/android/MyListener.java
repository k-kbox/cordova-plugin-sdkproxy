package com.j1game.pay;

public interface MyListener {

	public void onExitConfirm();
	public void onExitCancel();

	public void onPaySuccess();
	public void onPayFailure(String errCode, String errMsg);
	public void onPayCanceled();
}
