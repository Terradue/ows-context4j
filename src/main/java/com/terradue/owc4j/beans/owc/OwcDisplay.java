/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRootElement(name="display", namespace="http://www.opengis.net/owc/1.0")
@XmlAccessorType(XmlAccessType.NONE)
public class OwcDisplay {
	
	@XmlElement(required=false)
	int pixelWidth;
	
	@XmlElement(required=false)
	int pixelHeight;
	
	@XmlElement(required=false)
	float mmPerPixel;

	@XmlAnyElement
	private List<Element> extentionElements;

	public int getPixelWidth() {
		return pixelWidth;
	}

	public void setPixelWidth(int pixelWidth) {
		this.pixelWidth = pixelWidth;
	}

	public int getPixelHeight() {
		return pixelHeight;
	}

	public void setPixelHeight(int pixelHeight) {
		this.pixelHeight = pixelHeight;
	}

	public float getMmPerPixel() {
		return mmPerPixel;
	}

	public void setMmPerPixel(float mmPerPixel) {
		this.mmPerPixel = mmPerPixel;
	}
	
	/**
	 * @return the extentionElements
	 */
	public List<Element> getExtentionElements() {
		if (extentionElements==null)
			extentionElements = new ArrayList<Element>();
		return extentionElements;
	}
	
	/**
	 * @param extentionElements the extentionElements to set
	 */
	public void setExtentionElements(List<Element> extentionElements) {
		this.extentionElements = extentionElements;
	}
	
}
