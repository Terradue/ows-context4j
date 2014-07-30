/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="legendUrl", namespace="http://www.opengis.net/owc/1.0")
@XmlAccessorType(XmlAccessType.NONE)
public class OwcLegendUrl {

	@XmlAttribute(required=true)
	private String type;
	
	private OwcHttpUrl href;
	
	
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

}
