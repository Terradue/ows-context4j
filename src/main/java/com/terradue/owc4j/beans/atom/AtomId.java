/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 *  
  <!-- atom:id -->
  <define name="atomId">
    <element name="atom:id">
      <ref name="atomCommonAttributes"/>
      <ref name="atomUri"/>
    </element>
  </define>

 */
@XmlRootElement(name="id", namespace="http://www.w3.org/2005/Atom")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomId extends AtomCommon {

	@XmlValue
	private String content;
	
	/**
	 * 
	 */
	public AtomId() {
		super();
	}
	
	public AtomId(String content) {
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

	
}
