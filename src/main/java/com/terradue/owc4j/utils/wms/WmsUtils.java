/**
 * 
 */
package com.terradue.owc4j.utils.wms;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.terradue.owc4j.beans.atom.AtomContent.Type;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.beans.owc.OwcOffering;
import com.terradue.owc4j.beans.owc.OwcOperation;
import com.terradue.owc4j.beans.owc.OwcOperation.Method;
import com.terradue.owc4j.utils.OwcOptions;
import com.terradue.owc4j.utils.Utils;


/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class WmsUtils {

	private static class VersionFoundException extends Exception {

		private static final long serialVersionUID = -2447761428215003004L;
		private String version;

		public VersionFoundException(String version) {
			this.version = version;
		}

		public String getVersion() {
			return version;
		}
	}


	private static ContentHandler handler = new ContentHandler() {

		public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
			if (qName.contentEquals("WMS_Capabilities")) {
				String version = atts.getValue("version");
				throw new SAXException(new VersionFoundException(version));
			}
			else if (qName.contentEquals("WMT_MS_Capabilities")) {
				String version = atts.getValue("version");
				throw new SAXException(new VersionFoundException(version));
			}
			else
				throw new SAXException();
		}

		public void startPrefixMapping(String prefix, String uri) throws SAXException {}
		public void startDocument() throws SAXException {}		
		public void skippedEntity(String name) throws SAXException {}		
		public void setDocumentLocator(Locator locator) {}	
		public void processingInstruction(String target, String data) throws SAXException {}		
		public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {}
		public void endPrefixMapping(String prefix) throws SAXException {}
		public void endElement(String uri, String localName, String qName) throws SAXException {}
		public void endDocument() throws SAXException {}
		public void characters(char[] ch, int start, int length) throws SAXException {}
	};


	public static String getCapabilitiesVersion(String wmsCapabilitiesUrl) throws SAXException, MalformedURLException, IOException {
		
		XMLReader myReader = XMLReaderFactory.createXMLReader();
		myReader.setContentHandler(handler);
		String version=null;
		try {
			myReader.parse(new InputSource(new URL(wmsCapabilitiesUrl).openStream()));
		} catch (SAXException e) {
			if (e.getCause() instanceof VersionFoundException)
				version = ((VersionFoundException)(e.getCause())).getVersion();
		}
		return version;
	}

	public static List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String layer, SupportedWmsVersion version, OwcOptions options) throws Exception {
		AbstractWmsCapabilities wmsCapabilities;
		switch (version) {
		case v_1_3_0: wmsCapabilities = new WmsCapabilities_1_3_0(); break;
		case v_1_1_1: wmsCapabilities = new WmsCapabilities_1_1_1(); break; // TODO
		case v_1_1_0: wmsCapabilities = new WmsCapabilities_1_1_1(); break; // TODO
		default: throw new Exception("Version null or incorrect.");
		}

		return wmsCapabilities.getEntriesFromWmsCapabilities(wmsCapabilitiesUrl, layer, options);
	}
	public static List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String layer, SupportedWmsVersion version) throws Exception {
		return getEntriesFromWmsCapabilities(wmsCapabilitiesUrl, layer, new OwcOptions());
	}

	public static List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		String version = getCapabilitiesVersion(wmsCapabilitiesUrl);
		AbstractWmsCapabilities wmsCapabilities = AbstractWmsCapabilities.wmsCapabilitiesFactory(version);
		return wmsCapabilities.getEntriesFromWmsCapabilities(wmsCapabilitiesUrl, pattern, options);
	}
	public static List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern) throws Exception {
		return getEntriesFromWmsCapabilities(wmsCapabilitiesUrl, pattern, new OwcOptions());
	}

	
	public static OwcDocument getOwcFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		String version = getCapabilitiesVersion(wmsCapabilitiesUrl);
		AbstractWmsCapabilities wmsCapabilities = AbstractWmsCapabilities.wmsCapabilitiesFactory(version);
		return wmsCapabilities.getOwcFromWmsCapabilities(wmsCapabilitiesUrl, pattern, options);
	}
	public static OwcDocument getOwcFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern) throws Exception {
		return getOwcFromWmsCapabilities(wmsCapabilitiesUrl, pattern, new OwcOptions());
	}
	
	
	public static OwcAtomEntry getEntryFromWmsLayer(String id, String title, AtomPersonConstruct defaultAuthor, String publisher, String content, boolean isHtmlContent,
			String getCapabilitiesUrl, String getMapUrl) throws Exception {
		
		OwcAtomEntry entry =  Utils.getBaseEntry(id, title, defaultAuthor, publisher, content, isHtmlContent);
		
		// offering
		OwcOffering offering = new OwcOffering();
		OwcOperation operation; 
		
		offering.setCode("http://www.opengis.net/spec/owc/1.0/req/atom/wms");
		
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode("GetCapabilities");
		operation.setHref(getCapabilitiesUrl);
		offering.getOperations().add(operation);
		
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode("GetMap");
		operation.setHref(getMapUrl);
		offering.getOperations().add(operation);
		
		entry.setOffering(offering);
		
		return entry;
	}
	
}
