package com.zl.bean;

import java.util.Date;

public class Bill {
	private Integer id;//�˵����
	private String tradeName;//��Ʒ��
	private String unit;//��Ʒ��λ
	private String num;//��Ʒ����
	private String amount;//�˵����
	private String supplier;//��Ӧ��
	private Integer payment;//�Ƿ񸶿�
	private Date createDate;//����ʱ��
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		if(num==null||num==" "){
			this.num="ȱ��";
		}else{
			this.num = num;
		}		
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
