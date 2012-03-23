package com.googlecode.mgwt.ui.client.theme.base;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.ClassName;

/**
@author Daniel Kurka (original)
@author Daniel and Julian Gerson (alterations)
Forked from com.googlecode.mgwt.ui.client.theme.base.ListCss
*/
public interface FormCss extends CssResource {

	
	@ClassName("fieldName")
	String fieldName();
	
	@ClassName("inputWidget")
	String input();
	
	@ClassName("mgwt-List")
	public String listCss();

	@ClassName("mgwt-List-round")
	public String round();

	@ClassName("mgwt-List-group")
	public String group();

	@ClassName("mgwt-List-selected")
	public String selected();

	@ClassName("mgwt-List-first")
	public String first();

	@ClassName("mgwt-List-last")
	public String last();

	@ClassName("mgwt-List-Header")
	public String listHeader();
}