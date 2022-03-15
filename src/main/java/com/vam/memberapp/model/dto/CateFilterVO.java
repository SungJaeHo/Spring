package com.vam.memberapp.model.dto;

public class CateFilterVO {

	/* ī�װ� �̸� */
	private String CateName;
	
	/* ī�װ� �ѹ� */
	
	private String cateCode;
	
	/* ī�װ� ��ǰ �� */
	private int cateCount;
	
	/* ����, ���� �з� */
	private String cateGroup;

	public String getCateName() {
		return CateName;
	}

	public void setCateName(String cateName) {
		CateName = cateName;
	}

	public String getCateCode() {
		return cateCode;
	}

	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
		this.cateGroup = cateCode.split("")[0];
	}

	public int getCateCount() {
		return cateCount;
	}

	public void setCateCount(int cateCount) {
		this.cateCount = cateCount;
	}

	public String getCateGroup() {
		return cateGroup;
	}

	public void setCateGroup(String cateGroup) {
		this.cateGroup = cateGroup;
	}

	@Override
	public String toString() {
		return "CateFilterVO [CateName=" + CateName + ", cateCode=" + cateCode + ", cateCount=" + cateCount
				+ ", cateGroup=" + cateGroup + "]";
	}
	
	
}
