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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.atom.AtomCommon;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

@XmlRootElement(name="operation", namespace="http://www.opengis.net/owc/1.0")
@XmlAccessorType(XmlAccessType.NONE)
public class OwcOperation extends AtomCommon {
	
	public enum Method { GET, POST };
	
	@XmlAttribute(required=true)
	private String code;

	private OwcHttpUrl href;
	
	@XmlAttribute(required=false)
	private String type;
	
	@XmlAttribute(required=true)
	private Method method=Method.GET;

	@XmlElement(name="result", namespace="http://www.opengis.net/owc/1.0", required=false)
	private List<OwcExtensionElement> result;

	@XmlAnyElement
	private List<Element> extentionElements;

	private List<OwcExtensionElement> payload;

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
	 * @param href the href to set
	 */
	public void setHref(OwcHttpUrl href) {
		this.href = href;
	}
	
	public void setHref(String url) throws Exception {
		this.href = new OwcHttpUrl(url);
	}
	
	public OwcHttpUrl getOwcHttpUrlHref() {
		return href;
	}

	@XmlAttribute(name="href", required=true)
	public String getHref() {
		if (href==null)
			return null;
		return href.toString();
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}
	
	/**
	 * @param method the method to set
	 */
	public void setMethod(Method method) {
		this.method = method;
	}
	
	/**
	 * @return the result
	 */
	public List<OwcExtensionElement> getResult() {
		if (result==null)
			result = new ArrayList<OwcExtensionElement>();
		return result;
	}
	
	/**
	 * @param result the result to set
	 */
	public void setResult(List<OwcExtensionElement> result) {
		this.result = result;
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
	
	/**
	 * @return the payload
	 */
	@XmlElement(name="payload", namespace="http://www.opengis.net/owc/1.0", required=false)
	public List<OwcExtensionElement> getPayload() {
		// to implement <choice> on attributes
		if (method==Method.GET)
			return null; // if "get", payload doesn't exist
		if (payload==null)
			payload = new ArrayList<OwcExtensionElement>();

		return payload;
	}
	
	/**
	 * @param payload the payload to set
	 */
	public void setPayload(List<OwcExtensionElement> payload) {
		this.payload = payload;
	}
	
}
