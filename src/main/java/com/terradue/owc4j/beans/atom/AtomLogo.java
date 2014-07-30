/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="logo", namespace="http://www.w3.org/2005/Atom")
public class AtomLogo extends AtomCommon {

	private AtomUri uri;
	
	/**
	 * 
	 */
	public AtomLogo() {
		super();
	}
	
	public AtomLogo(String uri) {
		setUri(uri);
	}
	
	public AtomLogo(AtomUri uri) {
		setUri(uri);
	}
	
	public AtomUri getAtomUri() {
		return uri;
	}
	
	/**
	 * @return the uri
	 */
	@XmlValue
	public String getUri() {
		if (uri==null)
			return null;
		else
			return uri.toString();
	}
	
	public void setUri(AtomUri uri) {
		this.uri = uri;
	}

	public void setUri(String text) {
		this.uri = new AtomUri(text);
	}

}
