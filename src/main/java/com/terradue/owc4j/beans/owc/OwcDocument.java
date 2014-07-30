/**
 * 
 */

package com.terradue.owc4j.beans.owc;


/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

// TODO: threat interleave cases (now all is interleaved)
// TODO: improve undefinedContent element (now it's plain text)
// TODO: improve undefinedAttribute (excepts control)
// TODO: check anyElement (now using @XmlAnyElement annotation)
// TODO: add rules validation
// TODO: check xhtmlDiv mapping
// TODO: manage type in atomplain and atominline
// TODO: <!-- Redefine the Simple Extension to exclude owc:* elements -->

public class OwcDocument {
	
	private OwcAtomFeed feed;
	
	public OwcAtomFeed getFeed() {
		if (feed==null)
			feed = new OwcAtomFeed();
		return feed;
	}

	public void setFeed(OwcAtomFeed feed) {
		this.feed = feed;
	}
	
}

/*
  
*/