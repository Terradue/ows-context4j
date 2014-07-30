/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="link", namespace="http://www.w3.org/2005/Atom")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"rel","type","title","href"})
public class AtomLink extends AtomCommon {
	
//    <attribute name="href">
//      <ref name="atomUri"/>
//    </attribute>
	private AtomUri href;

//    <optional>
//      <attribute name="rel">
//        <choice>
//          <ref name="atomNCName"/>
//          <ref name="atomUri"/>
//        </choice>
//      </attribute>
//    </optional>
	@XmlAttribute(required=false)
	private String rel;

//  <optional>
//    <attribute name="type">
//      <ref name="atomMediaType"/>
//    </attribute>
//  </optional>
	private AtomMediaType type;
	
//  <optional>
//  	<attribute name="title"/>
//	</optional>
	@XmlAttribute(required=false)
	private String title;

//    <optional>
//      <attribute name="hreflang">
//        <ref name="atomLanguageTag"/>
//      </attribute>
//    </optional>
	private AtomLanguageTag hreflang;

//    <optional>
//      <attribute name="length"/>
//    </optional>
	@XmlAttribute(required=false)
	private String length;

//    <ref name="undefinedContent"/>
	@XmlValue
	private String content;

	
	public AtomUri getHrefAtomUri() {
		return href;
	}
	
	@XmlAttribute(name="href", required=false)
	public String getHref() {
		if (href==null)
			return null;
		else
			return href.toString();
	}
	
	public void setHref(AtomUri uri) {
		this.href = uri;
	}

	public void setHref(String uri) {
		this.href = new AtomUri(uri);
	}
	
	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}
	
	/**
	 * @param rel the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	public void setRelAsNCName(String rel) throws Exception {
		if (rel==null || rel.length()==0)
			throw new Exception("rel NCName must have at leas 1 character.");
		
		Utils.checkPattern("rel NCName", rel, "[^:]*");
		this.rel = rel;
	}

	public void setRelAsUri(AtomUri rel) throws Exception {
		if (rel!=null && rel.getText()!=null)
			this.rel = rel.toString();
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(AtomMediaType type) {
		this.type = type;
	}
	
	public void setType(String type) throws Exception {
		this.type = new AtomMediaType(type);
	}
	
	/**
	 * @return the type
	 */
	public AtomMediaType getAtomMediaType() {
		return type;
	}
	
	/**
	 * @return the type
	 */
	@XmlAttribute(required=false)
	public String getType() {
		if (type==null)
			return null;
		return type.toString();
	}
	
	/**
	 * @return the hreflang
	 */
	@XmlAttribute(name="hreflang", required=false)
	public String getHreflang() {
		if (hreflang==null)
			return null;
		else
			return hreflang.toString();
	}
	
	/**
	 * @return the hreflang
	 */
	public AtomLanguageTag getAtomLanguageHreflang() {
		return hreflang;
	}
	
	/**
	 * @param hreflang the hreflang to set
	 */
	public void setHreflang(AtomLanguageTag hreflang) {
		this.hreflang = hreflang;
	}
	
	/**
	 * @param xmlLang the xmlLang to set
	 * @throws Exception 
	 */
	public void setHreflang(String hreflang) throws Exception {
		this.hreflang = new AtomLanguageTag(hreflang);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the length
	 */
	public String getLength() {
		return length;
	}
	
	/**
	 * @param length the length to set
	 */
	public void setLength(String length) {
		this.length = length;
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
