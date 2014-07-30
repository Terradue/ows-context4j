/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.atom.AtomCommon;


/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="offering", namespace="http://www.opengis.net/owc/1.0")
public class OwcOffering extends AtomCommon {
	
	@XmlAttribute(required=true)
	String code;
	
	@XmlElementRef
	List<OwcOperation> operations;
	
	@XmlElementRef
	List<OwcContent> contents;
	
	List<OwcStyleSet> styleSets;
	
	// TODO manage extension with exceptions
	@XmlAnyElement
	private List<Element> extentionElements;

	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the operations
	 */
	public List<OwcOperation> getOperations() {
		if (operations==null)
			operations = new ArrayList<OwcOperation>();
		return operations;
	}
	
	/**
	 * @param operations the operations to set
	 */
	public void setOperations(List<OwcOperation> operations) {
		this.operations = operations;
	}
	
	/**
	 * @return the contents
	 */
	public List<OwcContent> getContents() {
		if (contents==null)
			contents = new ArrayList<OwcContent>();
		return contents;
	}
	
	/**
	 * @param contents the contents to set
	 */
	public void setContents(List<OwcContent> contents) {
		this.contents = contents;
	}
	
	/**
	 * @return the styleSets
	 */
	public List<OwcStyleSet> getStyleSets() {
		if (styleSets==null)
			styleSets = new ArrayList<OwcStyleSet>();
		return styleSets;
	}
	
	/**
	 * @param styleSets the styleSets to set
	 */
	public void setStyleSets(List<OwcStyleSet> styleSets) {
		this.styleSets = styleSets;
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
