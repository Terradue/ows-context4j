/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class AtomMediaType {
	
	String type;
	
	/**
	 * 
	 */
	public AtomMediaType() {
		super();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public AtomMediaType(String type) throws Exception {
		super();
		setType(type);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 * @throws Exception 
	 */
	public void setType(String type) throws Exception {
		Utils.checkPattern("type", type, ".+/.+");
		this.type = type;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
}
