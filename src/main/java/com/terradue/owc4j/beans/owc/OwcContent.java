/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.atom.AtomCommon;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="content", namespace="http://www.opengis.net/owc/1.0")
@XmlAccessorType(XmlAccessType.NONE)
public class OwcContent extends AtomCommon {
	
	public enum OwcContentType { internal, external };
	
	private OwcContentType owcContentType = OwcContentType.external;
	
	@XmlAttribute
	private String type;
	
	private OwcHttpUrl href;
	
	@XmlAnyElement
	private List<Element> anyElement;
	
	/**
	 * @param href the href to set
	 */
	public void setHref(OwcHttpUrl href) {
		this.href = href;
	}
	
	public void setHref(String url) throws Exception {
		this.href = new OwcHttpUrl(url);
		this.owcContentType = OwcContentType.external;
	}
	
	public OwcHttpUrl getOwcHttpUrlHref() {
		return href;
	}

	@XmlAttribute(name="href")
	public String getHref() {
		// if (type is internal) don't rpresent href
		if (href==null || owcContentType==OwcContentType.internal)
			return null;
		return href.toString();
	}
	
	public List<Element> getAnyElement() {
		if (anyElement==null)
			anyElement = new ArrayList<Element>();
		if (owcContentType==OwcContentType.external)
			return null;
		
		return anyElement;
	}
	
	public void setAnyElement(List<Element> anyElement) {
		this.anyElement = anyElement;
		this.owcContentType = OwcContentType.internal;
	}

	/**
	 * @param owcContentType the owcContentType to set
	 */
	public void setOwcContentType(OwcContentType owcContentType) {
		this.owcContentType = owcContentType;
	}
	
	/**
	 * @return the owcContentType
	 */
	public OwcContentType getOwcContentType() {
		return owcContentType;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
