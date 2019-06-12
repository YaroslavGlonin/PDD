package com.yaroslavsoftware.pdd;

public class PropertyFine {
	private String name;
	private String text;
	private String fine;
 
	public PropertyFine(String name, String text, String fine) {
		this.name = name;
		this.text = text;
		this.fine = fine;
	}
 
	public String gettext() {
		return this.text;
	}
 
	public String getname() {
		return this.name;
	}
	
	public String getfine() {
		return this.fine;
	}
 
}