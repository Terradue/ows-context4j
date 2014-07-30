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
  <!-- atom:generator -->
  <define name="atomGenerator">
    <element name="atom:generator">
      <ref name="atomCommonAttributes"/>
      <optional>
        <attribute name="uri">
          <ref name="atomUri"/>
        </attribute>
      </optional>
      <optional>
        <attribute name="version"/>
      </optional>
      <text/>
    </element>
  </define>


 */
@XmlRootElement(name="generator", namespace="http://www.w3.org/2005/Atom")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomGenerator extends AtomCommon {
	
	private AtomUri uri;
	
	@XmlAttribute(required=false)
	private String version;

	@XmlValue
	private String content;
	
	/**
	 * 
	 */
	public AtomGenerator() {
		super();
	}
	
	public AtomGenerator(String content) {
		setContent(content);
	}
	
	public AtomUri getAtomUri() {
		return uri;
	}
	
	@XmlAttribute(name="uri", required=false)
	public String getUri() {
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

	
	/**
	 * @param term the term to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @return the term
	 */
	public String getVersion() {
		return version;
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
