/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
@XmlRegistry
public class ObjectFactory {

	public ObjectFactory() {
    }
	
	public OwcAtomFeed createOwcAtomFeed() {
		return new OwcAtomFeed();
	}
}
