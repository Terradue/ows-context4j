
@XmlSchema(
	elementFormDefault = XmlNsForm.QUALIFIED,
	xmlns={
		@XmlNs(prefix="dc", namespaceURI="http://purl.org/dc/elements/1.1/"),
		@XmlNs(prefix="georss", namespaceURI="http://www.georss.org/georss"),
		@XmlNs(prefix="gml", namespaceURI="http://www.opengis.net/gml"),
		@XmlNs(prefix="owc", namespaceURI="http://www.opengis.net/owc/1.0"),
		@XmlNs(prefix="xsi", namespaceURI="http://www.w3.org/2001/XMLSchema-instance"),
	}
)    

package com.terradue.owc4j.beans.atom;

import javax.xml.bind.annotation.*;

