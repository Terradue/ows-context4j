/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class OwcExtensionElement {
	
	@XmlAnyElement
	private List<Element> extentionElements;
	
	
	/**
	 * 
	 */
	public OwcExtensionElement() {
		super();
	}
	
	public OwcExtensionElement(Element element) {
		this.getExtentionElements().add(element);
	}
	
	public OwcExtensionElement(List<Element> elements) {
		setExtentionElements(elements);
	}
	
	
	/**
	 * @return the extentionElements
	 */
	public List<Element> getExtentionElements() {
		if (extentionElements==null)
			extentionElements = new ArrayList<Element>();
		return extentionElements;
	}
	
	/**
	 * @param extentionElements the extentionElements to set
	 */
	public void setExtentionElements(List<Element> extentionElements) {
		this.extentionElements = extentionElements;
	}
	
}
