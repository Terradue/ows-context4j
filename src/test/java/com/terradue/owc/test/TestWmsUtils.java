/**
 * 
 */
package com.terradue.owc.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.xml.sax.SAXException;

import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.custom.BoundingBox;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.utils.OwcOptions;
import com.terradue.owc4j.utils.wms.WmsUtils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestWmsUtils {


	public static void main(String[] args) throws Exception {
		String capabilitiesUrl1 = "http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?SERVICE=WMS&VERSION=1.1.1&request=GetCapabilities";
								 //"http://ows.genesi-dec.eu/geoserver/385d7d71-650a-414b-b8c7-739e2c0b5e76/wms?SERVICE=WMS&SERVICE=WMS&VERSION=1.1.1&REQUEST=getCapabilities";
		String layer1 = "GEBCO_08_Grid";
				//"MER_RR__2PRLRA20120406_102429_000026213113_00238_52838_0211.N1.tif";

		String capabilitiesUrl2 = "http://geo.vliz.be/geoserver/MarineRegions/wms?Request=getCapabilities";
		String layer2 = "MarineRegions:boundaries";

		try {
			// set options
			OwcOptions options = new OwcOptions()
				.withCheckQueryable(false)
				.withBbox(new BoundingBox(-69,40,12,65));
			
			showVersion(capabilitiesUrl1);
			showVersion(capabilitiesUrl2);

			// istanziate owc document for the first wms url
			System.out.println("Generate OWC document...\n");
			OwcDocument owc = WmsUtils.getOwcFromWmsCapabilities(capabilitiesUrl1, layer1, options);
			
			// set some specific information
			OwcAtomFeed feed = owc.getFeed();
			feed.setId("OSWContext_05");
			feed.setTitle("OWS Context Example with FAO and i-Marine Data");
			feed.getAuthors().add(new AtomPersonConstruct("iMarine"));
			feed.setPublisher("Terradue Srl.");
			
			// add entries for next wms url
			List<OwcAtomEntry> entries = WmsUtils.getEntriesFromWmsCapabilities(capabilitiesUrl2, layer2, options);
			owc.getFeed().getEntries().addAll(entries);

			showXml(owc); // marshalling and print xml to the system.out
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	private static String url = 
//			"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities"
//			"https://dl.dropboxusercontent.com/u/24368142/t2/wmsCapabilities.xml";
//			"http://gdr.ess.nrcan.gc.ca/wmsconnector/com.esri.wms.Esrimap/energy_e?SERVICE=WMS&REQUEST=GetCapabilities"
//			"http://ows.genesi-dec.eu/geoserver/ows?service=wms&version=1.3.0&request=GetCapabilities"
			"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities"
//			"http://demo.lizardtech.com/lizardtech/iserv/ows?service=WMS&version=1.3.0&request=GETCAPABILITIES" // 1.1.0
			;

	/**
	 * @param capabUrl
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws MalformedURLException 
	 */
	private static void showVersion(String capabUrl) throws MalformedURLException, SAXException, IOException {
		String version = WmsUtils.getCapabilitiesVersion(capabUrl);
		if (version==null) {
			System.out.println("NO VERSION FOUND");
			return;
		}
		System.out.println("-"+capabUrl+"\n FOUND VERSION "+version+"\n");
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
