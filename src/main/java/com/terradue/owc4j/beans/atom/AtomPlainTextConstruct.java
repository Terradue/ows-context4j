/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

/*
  <!-- Text Constructs -->
  <define name="atomPlainTextConstruct">
    <ref name="atomCommonAttributes"/>
    <optional>
      <attribute name="type">
        <choice>
          <value>text</value>
          <value>html</value>
        </choice>
      </attribute>
    </optional>
    <text/>
  </define>

 */


@XmlAccessorType(XmlAccessType.NONE)
public class AtomPlainTextConstruct extends AtomTextConstruct {
	
	
	@XmlValue
	private String content;
	
	@XmlAttribute(required=false)
	private String type;
	
	/**
	 * 
	 */
	public AtomPlainTextConstruct() {
		super();
	}
	
	public AtomPlainTextConstruct(String content) {
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
		this.type = type.toString();
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
}
