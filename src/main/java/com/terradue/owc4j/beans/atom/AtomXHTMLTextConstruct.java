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
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

/*
  <define name="atomXHTMLTextConstruct">
    <ref name="atomCommonAttributes"/>
    <attribute name="type">
      <value>xhtml</value>
    </attribute>
    <ref name="xhtmlDiv"/>
  </define>
  
 */

@XmlRootElement(name="xhtml:div")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomXHTMLTextConstruct extends AtomTextConstruct {
	
	@XmlAttribute
	private String type="xhtml";
	
	protected List<Element> xhtmlDiv;
	
	/**
	 * 
	 */
	public AtomXHTMLTextConstruct() {
		super();
	}
	
	@XmlAnyElement
    public List<Element> getXhtmlDiv() {
        if(xhtmlDiv == null)
        	xhtmlDiv = new ArrayList<Element>();
        return xhtmlDiv;
    }
    public void setXhtmlDiv( List<Element> xhtmlDiv ){
        this.xhtmlDiv = xhtmlDiv;
    }
}
