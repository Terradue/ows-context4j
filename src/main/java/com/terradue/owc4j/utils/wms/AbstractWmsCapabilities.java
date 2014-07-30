/**
 * 
 */
package com.terradue.owc4j.utils.wms;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.transform.stream.StreamSource;

import net.opengis.wms.v_1_1_0.Capability;
import net.opengis.wms.v_1_1_0.Layer;
import net.opengis.wms.v_1_1_0.WMTMSCapabilities;

import com.terradue.owc4j.Constants;
import com.terradue.owc4j.beans.atom.AtomCategory;
import com.terradue.owc4j.beans.atom.AtomDateConstruct;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.utils.OwcOptions;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public abstract class AbstractWmsCapabilities {
	
	protected static final String SERVICE_NAME = "WMS";

	protected Unmarshaller getUnmarshaller() throws Exception {
		// Create JAXB context for WMS
		JAXBContext context = getJaxbContext();

		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		return unmarshaller;

	}

	protected JAXBContext getJaxbContext() throws Exception {
		String contextName;
		
		switch (getVersion()) {
		case v_1_3_0: contextName = "net.opengis.wms.v_1_3_0"; break;
		case v_1_1_1: contextName = "net.opengis.wms.v_1_1_1"; break;
		case v_1_1_0: contextName = "net.opengis.wms.v_1_1_0"; break;
		default: throw new Exception("supportedVersion null or incorrect.");
		}
		
		return JAXBContext.newInstance(contextName);
	}

	/**
	 * @return the version
	 */
	public abstract SupportedWmsVersion getVersion();
	public abstract String getVersionStr();
	
	public abstract List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception;
	
	public abstract OwcDocument getOwcFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception;

	public String getDefaultOperation() {
		return "GetMap";
	};
	
	public String getServiceName(){
		return "WMS";
	}
	
	public String getOfferingCode(){
		return "http://www.opengis.net/spec/owc/1.0/req/atom/wms";
	}
	
	public static AbstractWmsCapabilities wmsCapabilitiesFactory(String version) {
		if (version.contentEquals("1.3.0"))
			return new WmsCapabilities_1_3_0();
		if (version.contentEquals("1.1.1"))
			return new WmsCapabilities_1_1_1();

		// other cases
		return new WmsCapabilities_1_1_1();
	}

	/**
	 * @param crss
	 * @return
	 */
	protected String getCrs(List<String> crss) {
		boolean crssNotEmpty = (crss!=null && crss.size()>0);

		// try to get preferred crs
		if (crssNotEmpty && crss.contains(Constants.EPSG_4326))
			return Constants.EPSG_4326;

		if (crssNotEmpty && crss.contains(Constants.CRS_84))
			return Constants.CRS_84;

		if (crssNotEmpty)
			return crss.get(0);

		return null;
	}

}
