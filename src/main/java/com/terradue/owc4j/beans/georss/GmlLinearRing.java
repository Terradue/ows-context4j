/**
 * 
 */
package com.terradue.owc4j.beans.georss;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
public class GmlLinearRing {

		@XmlElement(name="posList", namespace="http://www.opengis.net/gml")
		String gmlPosList;
		
		public GmlLinearRing(){
			super();
		}
		
		public GmlLinearRing(String posList) throws Exception{
			super();
			setGmlPosList(posList);
		}

		public GmlLinearRing(List<Double> posList) throws Exception{
			super();
			setGmlPosList(posList);
		}

		/**
		 * @return the gmlPosList
		 */
		public String getGmlPosList() {
			return gmlPosList;
		}

		/**
		 * @param gmlPosList the gmlPosList to set
		 */
		public void setGmlPosList(String gmlPosList) {
			this.gmlPosList = gmlPosList;
		}
		
		/**
		 * @param gmlPosList the gmlPosList to set
		 * @throws Exception 
		 */
		public void setGmlPosList(List<Double> posList) throws Exception {
			if (posList.size()<2)
				throw new Exception("At leas a couple of position must be inserted");
			if (posList.size()%2!=0)
				throw new Exception("PosList must be on couples");
			
			String str = posList.get(0)+" "+posList.get(1);
			for (int i=2; i<posList.size(); i++){
				str += " " + posList.get(i);
			}
			this.gmlPosList = str;
		}
	
		 
	}
