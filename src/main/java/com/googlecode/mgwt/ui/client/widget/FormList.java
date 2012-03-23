/*Daniel Gerson
 * 
 * ORIGINAL BELOW
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.googlecode.mgwt.ui.client.widget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.ui.client.MGWTStyle;
import com.googlecode.mgwt.ui.client.theme.base.FormCss;
import com.googlecode.mgwt.ui.client.theme.base.ListCss;
import com.googlecode.mgwt.ui.client.theme.base.MGWTClientBundleBaseThemeIPad;



/**
 * Forked from com.googlecode.mgwt.ui.client.widget.WidgetList
@Author Daniel Kurka (original)
@Author Daniel and Julian Gerson (alterations)
 */
public class FormList extends Composite implements HasWidgets {

	private static class WidgetListEntry extends Composite implements HasWidgets {
		private static class LIFlowPanel extends ComplexPanel {

			public LIFlowPanel() {
				setElement(Document.get().createLIElement());

			}

			@Override
			public void add(Widget w) {
				add(w, getElement());
			}
		}

		private Panel container;
		private FieldPanel fieldPanel;
		private InputPanel inputPanel;

		public WidgetListEntry(FormCss css) {
			container = new LIFlowPanel();
			fieldPanel = new FieldPanel();
			inputPanel = new InputPanel();
			container.add(fieldPanel);
			container.add(inputPanel);
			
			fieldPanel.setStyleName(css.fieldName());
			inputPanel.setStyleName(css.input());
			
			/*
			//swapping around the primary css styles
			String holdStyle = container.getStylePrimaryName();
			container.setStylePrimaryName(fCss.containerLI());
			container.addStyleName(holdStyle);*/
			
			initWidget(container);

		}

		/**
		 * This should never be called.
		 */
		@Deprecated
		@Override
		public void add(Widget w) {
		}
		
		public void add(String fieldName, Widget w)
		{
			fieldPanel.setText(fieldName);
			inputPanel.add(w);
		}

		@Override
		public void clear() {
			container.clear();

		}

		@Override
		public Iterator<Widget> iterator() {
			return container.iterator();
		}

		@Override
		public boolean remove(Widget w) {
			return container.remove(w);
		}
	}

	private static class ULFlowPanel extends ComplexPanel {

		public ULFlowPanel() {
			setElement(Document.get().createULElement());
		}

		@Override
		public void add(Widget w) {
			add(w, getElement());
		}
	}
	
	private static class FieldPanel extends ComplexPanel {
		public FieldPanel() {
			setElement(Document.get().createDivElement());
		}

		public void setText(String fieldName) {
			String safe = SafeHtmlUtils.htmlEscape(fieldName);
			getElement().setInnerText(safe);
		}
	}
	
	private static class InputPanel extends ComplexPanel {
		public InputPanel() {
			setElement(Document.get().createDivElement());
		}
		
		@Override
		public void add(Widget w)
		{
			add(w, getElement());
		}
	}

	private int childCount;
	private List<WidgetListEntry> children = new ArrayList<WidgetListEntry>();

	private Panel container;
	private Map<Widget, WidgetListEntry> map;
	protected final FormCss css;

	/**
	 * Construct a widget list using the default css
	 */
	public FormList() {
		//this(MGWTStyle.getTheme().getMGWTClientBundle().getListCss());
		this(getTheme());
	}
	
	public static FormCss getTheme()
	{
		MGWTClientBundleBaseThemeIPad bundle = GWT.create(MGWTClientBundleBaseThemeIPad.class);
		FormCss fCss = bundle.getFormCss();
		
		return fCss;
	}

	/**
	 * Construct a widget list using a specific css
	 * 
	 * @param css the css to use
	 */
	public FormList(FormCss css) {
		this.css = css;
		css.ensureInjected();
		container = new ULFlowPanel();
		initWidget(container);

		setStylePrimaryName(css.listCss());

		map = new HashMap<Widget, WidgetListEntry>();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#add(com.google.gwt.user.client.ui.Widget)
	 */
	/** This should never be called. Please use {@link FormList#add(String, Widget)}*/
	@Override
	public void add(Widget w) {
	}
	
	public void add(String fieldName, Widget w) {
		
		WidgetListEntry widgetListEntry = new WidgetListEntry(css);
		widgetListEntry.add(fieldName, w);
		if (childCount == 0) {
			widgetListEntry.addStyleName(css.first());
		}
		map.put(w, widgetListEntry);
		container.add(widgetListEntry);
		children.add(widgetListEntry);

		if (childCount > 0) {
			children.get(childCount - 1).removeStyleName(css.last());
		}
		widgetListEntry.addStyleName(css.last());

		childCount++;
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#clear()
	 */
	/** {@inheritDoc} */
	@Override
	public void clear() {
		container.clear();
		map.clear();
		children.clear();
		childCount = 0;

	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#iterator()
	 */
	/** {@inheritDoc} */
	@Override
	public Iterator<Widget> iterator() {
		return map.keySet().iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see com.google.gwt.user.client.ui.HasWidgets#remove(com.google.gwt.user.client.ui.Widget)
	 */
	/** {@inheritDoc} */
	@Override
	public boolean remove(Widget w) {
		WidgetListEntry entry = map.remove(w);
		if (entry == null)
			return false;
		// did we remove the last child?
		if (children.get(childCount - 1) == entry) {
			// are there others in the list?
			if (childCount - 2 >= 0) {
				children.get(childCount - 2).addStyleName(css.last());
			}
		}

		// did we remove the first child
		if (children.get(0) == entry) {
			if (children.size() > 1) {
				children.get(1).addStyleName(css.first());
			}
		}
		childCount--;
		children.remove(w);
		return container.remove(entry);

	}

	/**
	 * Should the list be displayed with rounded corners
	 * 
	 * @param round true to display with rounded corners
	 */
	public void setRound(boolean round) {
		if (round) {
			addStyleName(css.round());
		} else {
			removeStyleName(css.round());
		}
	}

}
