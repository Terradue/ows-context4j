/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class AtomEmailAddress {

	String email;

	/**
	 * 
	 */
	public AtomEmailAddress() {
		super();
	}
	
	/**
	 * @param email
	 * @throws Exception 
	 */
	public AtomEmailAddress(String email) throws Exception {
		setEmail(email);
	}
	
	/**
	 * @param email the email to set
	 * @throws Exception 
	 */
	public void setEmail(String email) throws Exception {
		Utils.checkPattern("email", email, ".+@.+");
		this.email = email;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.email;
	}
}
