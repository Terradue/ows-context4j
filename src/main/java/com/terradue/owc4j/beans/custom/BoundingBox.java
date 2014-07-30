/**
 * 
 */
package com.terradue.owc4j.beans.custom;

import com.terradue.owc4j.Constants;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class BoundingBox {

	double maxx=0, maxy=0, minx=0, miny=0;
	String crs = Constants.EPSG_4326;
	
	/**
	 * 
	 */
	public BoundingBox() {
		super();
	}

	public BoundingBox(double minx, double miny, double maxx, double maxy) {
		super();
		this.maxx = maxx;
		this.maxy = maxy;
		this.minx = minx;
		this.miny = miny;
	}

	public BoundingBox(double minx, double miny, double maxx, double maxy, String crs) {
		super();
		this.maxx = maxx;
		this.maxy = maxy;
		this.minx = minx;
		this.miny = miny;
		this.crs = crs;
	}
	
	public BoundingBox(String bbox) throws Exception{
		super();
		String[] split = bbox.split(",");
		if (split.length!=4)
			throw new Exception("Not a good bbox representation: "+bbox);
		this.minx = Double.parseDouble(split[0]);
		this.miny = Double.parseDouble(split[1]);
		this.maxx = Double.parseDouble(split[2]);
		this.maxy = Double.parseDouble(split[3]);
	}

	public double getMaxx() {
		return maxx;
	}

	public void setMaxx(double maxx) {
		this.maxx = maxx;
	}

	public double getMaxy() {
		return maxy;
	}

	public void setMaxy(double maxy) {
		this.maxy = maxy;
	}

	public double getMinx() {
		return minx;
	}

	public void setMinx(double minx) {
		this.minx = minx;
	}

	public double getMiny() {
		return miny;
	}

	public void setMiny(double miny) {
		this.miny = miny;
	}
	
	public String getCrs() {
		return crs;
	}
	
	public void setCrs(String crs) {
		this.crs = crs;
	}
	
	public String toString(boolean firstY) {
		if (firstY)
			return miny +","+ minx +","+ maxy +","+ maxx;
		else
			return minx +","+ miny +","+ maxx +","+ maxy;
	}
	
	public String getLinearRingRepresentation(boolean withComma){
		String sep = (withComma ? "," : " ");
		return miny+sep+minx +" "+ miny+sep+maxx +" "+ maxy+sep+maxx +" "+ maxy+sep+minx +" "+ miny+sep+minx;
	}

	public String getLinearRingRepresentation(){
		return getLinearRingRepresentation(false);
	}
	
}
