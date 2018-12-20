package com.xyzinnotech.projectplan.pojo;

import com.google.gson.Gson;

public class JSGantt {

	private int pID;
	
	private String pName;
	
	private String pStart;
	
	private String pEnd;
	
	private String pClass;
	
	private String pLink;
	
	private int pMile;
	
	private String pRes;
	
	private int pComp;
	
	private int pGroup;
	
	private int pParent;
	
	private int pOpen;
	
	private String pDepend;
	
	private String pCaption;
	
	private String pNotes;
	
	public JSGantt() {
		super();
		pNotes = "Test Notes";
		pLink = "https://3rdi.xyz";
		pOpen = 1;
		pCaption = "Test Caption";
		pRes = "Test Resource";
		
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpStart() {
		return pStart;
	}

	public void setpStart(String pStart) {
		this.pStart = pStart;
	}

	public String getpEnd() {
		return pEnd;
	}

	public void setpEnd(String pEnd) {
		this.pEnd = pEnd;
	}

	public String getpClass() {
		return pClass;
	}

	public void setpClass(String pClass) {
		this.pClass = pClass;
	}

	public String getpLink() {
		return pLink;
	}

	public void setpLink(String pLink) {
		this.pLink = pLink;
	}

	public int getpMile() {
		return pMile;
	}

	public void setpMile(int pMile) {
		this.pMile = pMile;
	}

	public String getpRes() {
		return pRes;
	}

	public void setpRes(String pRes) {
		this.pRes = pRes;
	}

	public int getpComp() {
		return pComp;
	}

	public void setpComp(int pComp) {
		this.pComp = pComp;
	}

	public int getpGroup() {
		return pGroup;
	}

	public void setpGroup(int pGroup) {
		this.pGroup = pGroup;
	}

	public int getpParent() {
		return pParent;
	}

	public void setpParent(int pParent) {
		this.pParent = pParent;
	}

	public int getpOpen() {
		return pOpen;
	}

	public void setpOpen(int pOpen) {
		this.pOpen = pOpen;
	}

	public String getpDepend() {
		return pDepend;
	}

	public void setpDepend(String pDepend) {
		this.pDepend = pDepend;
	}

	public String getpCaption() {
		return pCaption;
	}

	public void setpCaption(String pCaption) {
		this.pCaption = pCaption;
	}

	public String getpNotes() {
		return pNotes;
	}

	public void setpNotes(String pNotes) {
		this.pNotes = pNotes;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
