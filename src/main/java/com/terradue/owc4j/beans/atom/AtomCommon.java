/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.namespace.QName;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public class AtomCommon {
	
//   <optional>
//    <attribute name="xml:base">
//      <ref name="atomUri"/>
//    </attribute>
//  </optional>
	@XmlAttribute(name="xml:base", required=false)
	private String xmlBase;

//	<optional>
//		<attribute name="xml:lang">
//			<ref name="atomLanguageTag"/>
//		</attribute>
//	</optional>
	private AtomLanguageTag xmlLang;
	
//	<zeroOrMore>
//		<ref name="undefinedAttribute"/>
//	</zeroOrMore>
	@XmlAnyAttribute
    private Map<QName,Object> otherAttributes;
	
	/**
	 * @return the otherAttributes
	 */
	public Map<QName, Object> getOtherAttributes() {
		if (otherAttributes==null)
			// using linkedHashMap for order maintaining
			otherAttributes = new LinkedHashMap<QName, Object>();
		return otherAttributes;
	}
	
	/**
	 * @param otherAttributes the otherAttributes to set
	 */
	public void setOtherAttributes(Map<QName, Object> otherAttributes) {
		this.otherAttributes = otherAttributes;
	}
	
	/**
	 * @return the xmlBase
	 */
	public String getXmlBase() {
		return xmlBase;
	}
	
	/**
	 * @param xmlBase the xmlBase to set
	 */
	public void setXmlBase(String xmlBase) {
		this.xmlBase = xmlBase;
	}
	
	/**
	 * @return the xmlLang
	 */
	@XmlAttribute(name="xml:lang", required=false)
	public String getXmlLang() {
		if (xmlLang==null)
			return null;
		else
			return xmlLang.toString();
	}
	
	/**
	 * @return the xmlLang
	 */
	public AtomLanguageTag getAtomLanguageTagXmlLang() {
		return xmlLang;
	}
	
	/**
	 * @param xmlLang the xmlLang to set
	 */
	public void setXmlLang(AtomLanguageTag xmlLang) {
		this.xmlLang = xmlLang;
	}
	
	/**
	 * @param xmlLang the xmlLang to set
	 * @throws Exception 
	 */
	public void setXmlLang(String xmlLang) throws Exception {
		this.xmlLang = new AtomLanguageTag(xmlLang);
	}
	
}
