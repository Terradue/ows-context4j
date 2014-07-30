/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
public class AtomInlineTextContent extends AtomContent {

	@XmlAttribute(required=false)
	private Type type;

	@XmlValue
	private String content;
	
	
	/**
	 * 
	 */
	public AtomInlineTextContent() {
		super();
	}
	
	public AtomInlineTextContent(String content) {
		setContent(content);
	}
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	
}
