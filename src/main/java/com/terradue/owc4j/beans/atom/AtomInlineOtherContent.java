/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

@XmlAccessorType(XmlAccessType.NONE)
public class AtomInlineOtherContent extends AtomContent {

	private AtomMediaType type;
	
	protected List<Element> xhtmlContent;

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

	@XmlAnyElement
    public List<Element> getXhtmlContent(){
        if(xhtmlContent == null)
        	xhtmlContent = new ArrayList<Element>();
        return xhtmlContent;
    }
    public void setXhtmlContent( List<Element> xhtmlContent ){
        this.xhtmlContent = xhtmlContent;
    }
}
