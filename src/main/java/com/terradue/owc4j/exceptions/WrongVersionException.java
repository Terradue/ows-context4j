/**
 * 
 */
package com.terradue.owc4j.exceptions;

import com.terradue.owc4j.utils.wms.SupportedWmsVersion;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class WrongVersionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2314432701813574892L;
	private String version;
	
	/**
	 * 
	 */
	public WrongVersionException(String version) {
		this.version = version;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Wrong Version: " + version+ "\n" + super.getMessage();
	}
}
