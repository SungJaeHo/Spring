package com.vam.memberapp.model.dto;

public class CateFilterVO {

	/* 카테고리 이름 */
	private String CateName;
	
	/* 카테고리 넘버 */
	
	private String cateCode;
	
	/* 카테고리 상품 수 */
	private int cateCount;
	
	/* 국내, 국외 분류 */
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
