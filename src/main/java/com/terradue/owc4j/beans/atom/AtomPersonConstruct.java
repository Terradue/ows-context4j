/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class AtomPersonConstruct extends AtomCommon {
	
//	private AtomCommon atomCommon;
	
	@XmlElement(required=true, namespace="http://www.w3.org/2005/Atom")
	private String name;
	
	private AtomUri uri;
	
	private AtomEmailAddress email;

	@XmlAnyElement
	private List<Element> extentionElements;

	/**
	 * 
	 */
	public AtomPersonConstruct() {
		super();
	}
	
	public AtomPersonConstruct(String name) {
		this.setName(name);
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AtomUri getUri() {
		return uri;
	}
	
	@XmlElement(name="uri", namespace="http://www.w3.org/2005/Atom")
	public String getAtomUri() {
		if (uri==null)
			return null;
		else
			return uri.toString();
	}
	
	public void setUri(AtomUri uri) {
		this.uri = uri;
	}

	public void setUri(String text) {
		this.uri = new AtomUri(text);
	}
	
	@XmlElement(name="email", namespace="http://www.w3.org/2005/Atom")
	public String getEmail() {
		if (email==null)
			return null;
		else
			return email.toString();
	}

	public void setEmail(String email) throws Exception {
		this.email = new AtomEmailAddress(email);
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
