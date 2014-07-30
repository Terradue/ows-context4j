/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.XmlTransient;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlTransient
public abstract class AtomTextConstruct extends AtomCommon{
	
	public enum Type{ text, html };

}
