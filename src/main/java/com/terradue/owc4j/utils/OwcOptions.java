/**
 * 
 */
package com.terradue.owc4j.utils;

import com.terradue.owc4j.beans.custom.BoundingBox;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class OwcOptions {

	int limit = -1; // default no limit
	BoundingBox bbox = null;
	boolean checkQueryable = true;
	double mapHeight = 500;
	double iconHeight = 100;

	public OwcOptions() {
		super();
	}
	
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public BoundingBox getBbox() {
		return bbox;
	}

	public void setBbox(BoundingBox bbox) {
		this.bbox = bbox;
	}

	public void setBbox(String bbox) throws Exception {
		this.bbox = new BoundingBox(bbox);
	}

	public boolean checkQueryable() {
		return checkQueryable;
	}

	public void setCheckQueryable(boolean checkQueryable) {
		this.checkQueryable = checkQueryable;
	}
	
	public double getMapHeight() {
		return mapHeight;
	}
	
	public void setMapHeight(double mapHeight) {
		this.mapHeight = mapHeight;
	}
	
	public double getIconHeight() {
		return iconHeight;
	}

	public void setIconHeight(double iconHeight) {
		this.iconHeight = iconHeight;
	}
	

	public OwcOptions withCheckQueryable(boolean checkQueryable){
		this.checkQueryable = checkQueryable;
		return this;
	}

	public OwcOptions withBbox(BoundingBox bbox) {
		this.bbox = bbox;
		return this;
	}

	public OwcOptions withBbox(String bbox) throws Exception{
		this.bbox = new BoundingBox(bbox);
		return this;
	}
	
	public OwcOptions withLimit(int limit) {
		this.limit = limit;
		return this;
	}
	
	public OwcOptions withMapHeight(double mapHeight) {
		this.mapHeight = mapHeight;
		return this;
	}

	public OwcOptions withIconHeight(double iconHeight) {
		this.iconHeight = iconHeight;
		return this;
	}

}
