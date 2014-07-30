/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import com.terradue.owc4j.beans.atom.AtomCommon;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="styleSet", namespace="http://www.opengis.net/owc/1.0")
@XmlAccessorType(XmlAccessType.NONE)
public class OwcStyleSet extends AtomCommon {

	public enum DefaultValues { value0, value1 }
	
	@XmlAttribute(name="default", required=false)
	private String _default;
	
	@XmlElement(required=true)
	private String name;
	
	@XmlElement(required=true)
	private String title;
	
	@XmlElement(name="abstract", required=false)
	private String _abstract;
	
	@XmlElement
	private OwcLegendUrl legendUrl;
	
	@XmlElementRef(required=false)
	private OwcContent content;
	
	@XmlElementRef
	private List<OwcExtensionElement> extensionElement;
	
	
	/**
	 * @return the _default
	 */
	public String getDefault() {
		return _default;
	}
	
	public void setDefault(DefaultValues _default) {
		this._default = (_default == DefaultValues.value1 ? "1" : "0");
	}
	
	/**
	 * @return the _abstract
	 */
	public String getAbstract() {
		return _abstract;
	}
	
	/**
	 * @param _abstract the _abstract to set
	 */
	public void setAbstract(String _abstract) {
		this._abstract = _abstract;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param legendUrl the legendUrl to set
	 */
	public void setLegendUrl(OwcLegendUrl legendUrl) {
		this.legendUrl = legendUrl;
	}
	
	/**
	 * @return the legendUrl
	 */
	public OwcLegendUrl getLegendUrl() {
		return legendUrl;
	}
	
	/**
	 * @return the result
	 */
	public List<OwcExtensionElement> getExtensionElement() {
		if (extensionElement==null)
			extensionElement = new ArrayList<OwcExtensionElement>();
		return extensionElement;
	}
	
	/**
	 * @param result the result to set
	 */
	public void setExtensionElement(List<OwcExtensionElement> extensionElement) {
		this.extensionElement = extensionElement;
	}

}
