/**
 * 
 */
package com.terradue.owc4j.utils.wfs;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;

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
public abstract class AbstractWfsCapabilities {
	
	protected static final String SERVICE_NAME = "WFS";

	protected Unmarshaller getUnmarshaller() throws Exception {
		// Create JAXB context for WFS
		JAXBContext context = getJaxbContext();

		// Use the created JAXB context to construct an unmarshaller
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		return unmarshaller;

	}

	protected JAXBContext getJaxbContext() throws Exception {
		String contextName;
		
		switch (getVersion()) {
		case v_1_1_0: contextName = "net.opengis.wfs.v_1_1_0"; break;
		default: throw new Exception("supportedVersion null or incorrect.");
		}
		
		return JAXBContext.newInstance(contextName);
	}

	/**
	 * @return the version
	 */
	public abstract SupportedWfsVersion getVersion();
	public abstract String getVersionStr();
	
	public abstract List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception;
	
	public abstract OwcDocument getOwcFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception;

	public String getDefaultOperation() {
		return "GetFeature";
	}
	
	public String getServiceName(){
		return "WFS";
	}
	
	public String getOfferingCode(){
		return "http://www.opengis.net/spec/owc/1.0/req/atom/wfs";
	}
	
	public static AbstractWfsCapabilities wfsCapabilitiesFactory(String version) {
		if (version.contentEquals("1.0.0"))
			return new WfsCapabilities_1_1_0();

		// other cases
		return new WfsCapabilities_1_1_0();
	}

	public int getDefaultMaxFeatures(){
		return 10;
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
