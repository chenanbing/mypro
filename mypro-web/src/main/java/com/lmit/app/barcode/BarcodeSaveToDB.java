package com.lmit.app.barcode;

public class BarcodeSaveToDB implements BarcodeSaveService{

	@Override
	public void save(String barcode) {
		System.out.println("barcode=============="+barcode);
	}

	@Override
	public void finish() {
		
	}

}
