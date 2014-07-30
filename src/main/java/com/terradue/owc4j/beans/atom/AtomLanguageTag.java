/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class AtomLanguageTag {

	
	String text;
	
	/**
	 * 
	 */
	public AtomLanguageTag() {
		super();
	}
	
	/**
	 * @throws Exception 
	 * 
	 */
	public AtomLanguageTag(String text) throws Exception {
		super();
		setText(text);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return text;
	}
	
	/**
	 * @param type the type to set
	 * @throws Exception 
	 */
	public void setText(String text) throws Exception {
		Utils.checkPattern("text", text, "[A-Za-z]{1,8}(-[A-Za-z0-9]{1,8})*");
		this.text = text;
	}
	
	/**
	 * @return the type
	 */
	public String getText() {
		return text;
	}
	
}
