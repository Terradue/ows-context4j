/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class OwcHttpUrl {

	String url;

	/**
	 * 
	 */
	public OwcHttpUrl() {
		super();
	}
	
	/**
	 * @param url
	 * @throws Exception 
	 */
	public OwcHttpUrl(String url) throws Exception {
		setUrl(url);
	}
	
	/**
	 * @param email the url to set
	 * @throws Exception 
	 */
	public void setUrl(String url) throws Exception {
		Utils.checkPattern("url", url, "https?://.*");
		this.url = url;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.url;
	}
}
