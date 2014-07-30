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
public class GmlExterior {
	
	@XmlElement(name="LinearRing", namespace="http://www.opengis.net/gml")
	GmlLinearRing gmlLinearRing;
	
	public GmlExterior(){
		super();
	}
	
	public GmlExterior(GmlLinearRing gmlLinearRing){
		super();
		setGmlLinearRing(gmlLinearRing);
	}

	/**
	 * @return the gmlLinearRing
	 */
	public GmlLinearRing getGmlLinearRing() {
		return gmlLinearRing;
	}
	
	/**
	 * @param gmlLinearRing the gmlLinearRing to set
	 */
	public void setGmlLinearRing(GmlLinearRing gmlLinearRing) {
		this.gmlLinearRing = gmlLinearRing;
	}
	  
}
