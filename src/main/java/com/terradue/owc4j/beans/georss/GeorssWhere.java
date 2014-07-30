/**
 * 
 */
package com.terradue.owc4j.beans.georss;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.atom.AtomCommon;
import com.terradue.owc4j.beans.atom.AtomContent;
import com.terradue.owc4j.beans.atom.AtomInlineOtherContent;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomInlineXHTMLContent;
import com.terradue.owc4j.beans.atom.AtomOutOfLineContent;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class GeorssWhere extends AtomCommon {
	
	/**
	 * 
	 */
	public GeorssWhere() {
		super();
	}
	
	@XmlAnyElement
	private List<Element> anyElement;
	
	public List<Element> getAnyElement() {
		if (anyElement==null)
			anyElement = new ArrayList<Element>();
		return anyElement;
	}
	
	public void setAnyElement(List<Element> anyElement) {
		this.anyElement = anyElement;
	}

	
	@XmlElements(value = { 
	        @XmlElement(name="Point",		type=GmlPointElement.class, required=false, namespace="http://www.opengis.net/gml"),
	        @XmlElement(name="LineString",	type=GmlLineStringElement.class, required=false, namespace="http://www.opengis.net/gml"),
	        @XmlElement(name="Polygon",		type=GmlPolygonElement.class, required=false, namespace="http://www.opengis.net/gml"),
	        @XmlElement(name="Envelope",	type=GmlEnvelopeElement.class, required=false, namespace="http://www.opengis.net/gml"),
	})
	private GmlElement gmlElement;

	
	/**
	 * @param gmlElement the gmlElement to set
	 */
	public void setGmlElement(GmlElement gmlElement) {
		this.gmlElement = gmlElement;
	}
	
	public void setGmlElement(GmlPointElement gmlElement) {
		this.gmlElement = gmlElement;
	}

	public void setGmlElement(GmlLineStringElement gmlElement) {
		this.gmlElement = gmlElement;
	}

	public void setGmlElement(GmlPolygonElement gmlElement) {
		this.gmlElement = gmlElement;
	}

	public void setGmlElement(GmlEnvelopeElement gmlElement) {
		this.gmlElement = gmlElement;
	}

	
	/**
	 * @return the gmlElement
	 */
	public GmlElement getGmlElement() {
		return gmlElement;
	}
}
