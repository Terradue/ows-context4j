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
   <define name="atomCategory">
    <element name="atom:category">
      <ref name="atomCommonAttributes"/>
      <attribute name="term"/>
      <optional>
        <attribute name="scheme">
          <ref name="atomUri"/>
        </attribute>
      </optional>
      <optional>
        <attribute name="label"/>
      </optional>
      <ref name="undefinedContent"/>
    </element>
  </define>

 */
@XmlRootElement(name="category")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomCategory extends AtomCommon {
	
	@XmlAttribute(required=true)
	private String term;
	
	private AtomUri scheme;
	
	@XmlAttribute(required=false)
	private String label;
	
	@XmlValue
	private String content;
	
	public AtomUri getSchemeAtomUri() {
		return scheme;
	}
	
	@XmlAttribute(name="scheme", required=false)
	public String getScheme() {
		if (scheme==null)
			return null;
		else
			return scheme.toString();
	}
	
	public void setScheme(AtomUri uri) {
		this.scheme = uri;
	}

	public void setScheme(String text) {
		this.scheme = new AtomUri(text);
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
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * @param term the term to set
	 */
	public void setTerm(String term) {
		this.term = term;
	}
	
	/**
	 * @return the term
	 */
	public String getTerm() {
		return term;
	}
	
}
