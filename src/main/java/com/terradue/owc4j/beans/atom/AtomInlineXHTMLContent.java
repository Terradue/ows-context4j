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
public class AtomInlineXHTMLContent extends AtomContent {

	@XmlAttribute
	private String type="xhtml";
	
	protected List<Element> xhtmlContent;

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
