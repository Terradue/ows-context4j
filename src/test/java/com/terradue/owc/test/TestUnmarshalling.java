/**
 * 
 */
package com.terradue.owc.test;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.beans.owc.OwcOperation;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestUnmarshalling {

	private static final String url =
//			"https://dl.dropboxusercontent.com/u/63073064/ows/examples/imarine.xml"
			"https://dl.dropboxusercontent.com/u/24368142/t2/imarine.xml"
			;

	public static void main(String args[]) {
		try {

			// Create JAXB context for WMS 1.3.0
			JAXBContext context;
			context = JAXBContext.newInstance(OwcDocument.class);

			// Use the created JAXB context to construct an unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();		
			
			// Unmarshal the given URL, retrieve WMSCapabilities element
			JAXBElement<OwcAtomFeed> jaxbEl = unmarshaller.unmarshal(new StreamSource(url), OwcAtomFeed.class);
			
			OwcAtomFeed feed = jaxbEl.getValue();
			System.out.println("Name="+feed.getAuthors().get(0).getName());
			System.out.println("Email="+feed.getEntries().get(0).getAuthors().get(0).getEmail());
			
			// show operations for each entry
			for (OwcAtomEntry entry: feed.getEntries()) {
				System.out.println("ENTRY: "+entry.getTitle());
				for (OwcOperation operation : entry.getOffering().getOperations()){
					System.out.println("   "+operation.getCode()+" : "+operation.getHref());
				}
			}
			
			System.out.println("ok");

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		//
		//
		//		// Retrieve WMSCapabilities instance
		//		WMTMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();
		//		// Iterate over layers, print out layer names
		//		Capability capab = wmsCapabilities.getCapability();
		//		Layer layerLgroup = capab.getLayer();
		//		List<Layer> layers = layerLgroup.getLayer();
		//		
		//		for (Layer layer: layers) {
		//			System.out.println(layer.getName());
		//		}

	}

}
