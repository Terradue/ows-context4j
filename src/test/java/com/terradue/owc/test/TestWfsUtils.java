/**
 * 
 */
package com.terradue.owc.test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.custom.BoundingBox;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.utils.OwcOptions;
import com.terradue.owc4j.utils.wfs.WfsUtils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestWfsUtils {


	public static void main(String[] args) throws Exception {
		String capabilitiesUrl1 = //"http://giswebservices.massgis.state.ma.us/geoserver/wfs?request=getcapabilities";
								// "http://www2.dmsolutions.ca/cgi-bin/mswfs_gmap?version=1.0.0&request=getcapabilities&service=wfs";
				//"http://giswebservices.massgis.state.ma.us/geoserver/wfs?request=getcapabilities&version=1.1.0";
				//"http://www2.dmsolutions.ca/cgi-bin/mswfs_gmap?version=1.1.0&request=getcapabilities&service=wfs";
				"http://dl.dropboxusercontent.com/u/24368142/t2/geoserver-getcapabilities1.xml";
		String layer1 = "massgis:AFREEMAN.CPA_COMBINED_V_PT_TIME";
				//"MER_RR__2PRLRA20120406_102429_000026213113_00238_52838_0211.N1.tif";

		String capabilitiesUrl2 = "http://geo.vliz.be/geoserver/MarineRegions/wms?Request=getCapabilities";
		String layer2 = "MarineRegions:boundaries";

		try {
			// set options
			OwcOptions options = new OwcOptions()
				.withLimit(3)
				.withBbox(new BoundingBox(-69,40,12,65));
			
			// istantiate owc document for the first wfs url
			System.out.println("Generate OWC document...\n");
			OwcDocument owc = WfsUtils.getOwcFromWfs(capabilitiesUrl1, null, options);
			
			// set some specific information
			OwcAtomFeed feed = owc.getFeed();
			feed.setId("OSWContext_05");
			feed.setTitle("OWS Context Example with FAO and i-Marine Data");
			feed.getAuthors().add(new AtomPersonConstruct("iMarine"));
			feed.setPublisher("Terradue Srl.");
			
			// add entries for next wms url
//			List<OwcAtomEntry> entries = WmsUtils.getEntriesFromWms(capabilitiesUrl2, layer2, options);
//			owc.getFeed().getEntries().addAll(entries);

			showXml(owc); // marshalling and print xml to the system.out
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * @throws JAXBException 
	 * 
	 */
	private static void showXml(OwcDocument owc) throws JAXBException {
		// MARSHALLING
		JAXBContext context = JAXBContext.newInstance(OwcDocument.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(owc.getFeed(), System.out);
	}
}
