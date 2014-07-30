/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
public class AtomOutOfLineContent extends AtomContent {

	private AtomMediaType type;
	
	private AtomUri src;

	
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
	
	@XmlAttribute(name="src", required=false)
	public String getSrc() {
		if (src==null)
			return null;
		else
			return src.toString();
	}
	
	public void setSrc(AtomUri src) {
		this.src = src;
	}

	public void setSrc(String src) {
		this.src = new AtomUri(src);
	}


}
