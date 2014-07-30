/**
 * 
 */
package com.terradue.owc4j.beans.georss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class GmlPolygonElement extends GmlElement {

	@XmlElement(name="exterior", namespace="http://www.opengis.net/gml")
	GmlExterior gmlExterior;

	public GmlPolygonElement(){
		super();
	}
	
	public GmlPolygonElement(GmlExterior gmlExterior){
		super();
		setGmlExterior(gmlExterior);
	}
	
	/**
	 * @return the gmlExterior
	 */
	public GmlExterior getGmlExterior() {
		return gmlExterior;
	}
	
	/**
	 * @param gmlExterior the gmlExterior to set
	 */
	public void setGmlExterior(GmlExterior gmlExterior) {
		this.gmlExterior = gmlExterior;
	}
}
