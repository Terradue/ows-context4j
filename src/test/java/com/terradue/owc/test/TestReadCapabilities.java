/**
 * 
 */
package com.terradue.owc.test;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import net.opengis.ows.v_1_0_0.Operation;
import net.opengis.ows.v_1_0_0.RequestMethodType;
import net.opengis.wfs.v_1_1_0.FeatureTypeType;
import net.opengis.wfs.v_1_1_0.WFSCapabilitiesType;
import net.opengis.wms.v_1_3_0.Capability;
import net.opengis.wms.v_1_3_0.Layer;
import net.opengis.wms.v_1_3_0.WMSCapabilities;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestReadCapabilities {

	private static String url = 
			//		"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities"
			//		"https://dl.dropboxusercontent.com/u/24368142/t2/wmsCapabilities.xml";
			//		"http://gdr.ess.nrcan.gc.ca/wmsconnector/com.esri.wms.Esrimap/energy_e?SERVICE=WMS&REQUEST=GetCapabilities"
						"http://ows.genesi-dec.eu/geoserver/ows?service=wms&version=1.3.0&request=GetCapabilities"
//			"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities" // layers inside other layers
			;
	
	private static String urlWfs = 
			"http://www2.dmsolutions.ca/cgi-bin/mswfs_gmap?version=1.1.0&request=getcapabilities&service=wfs"
			;

	public static void main(String args[]) {
		//wmsCapabilities();
		wfsCapabilities();
	}
	
	public static void wfsCapabilities(){
		try {
			// Create JAXB context for WFS 1.1.0
			JAXBContext context;
			context = JAXBContext.newInstance("net.opengis.wfs.v_1_1_0");
			
			// Use the created JAXB context to construct an unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Unmarshal the given URL, retrieve WMSCapabilities element
			JAXBElement<WFSCapabilitiesType> wfsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(urlWfs), WFSCapabilitiesType.class);
			
			// Retrieve WFSCapabilitiesType instance
			WFSCapabilitiesType wfsCapabilities = wfsCapabilitiesElement.getValue();
			List<FeatureTypeType> features = wfsCapabilities.getFeatureTypeList().getFeatureType();
			System.out.println("n="+features.size());
			for (FeatureTypeType f : features){
				System.out.println("---------");
				System.out.println(f.getTitle());
				System.out.println(f.getName().getLocalPart());
			}
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void wmsCapabilities(){
		try {
			// Create JAXB context for WMS 1.3.0
			JAXBContext context;
			context = JAXBContext.newInstance("net.opengis.wms.v_1_3_0");
			
			// Use the created JAXB context to construct an unmarshaller
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			// Unmarshal the given URL, retrieve WMSCapabilities element
			JAXBElement<WMSCapabilities> wmsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(url), WMSCapabilities.class);
			
			// Retrieve WMSCapabilities instance
			WMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();
			// Iterate over layers, print out layer names
			Capability capab = wmsCapabilities.getCapability();
			Layer layerLgroup = capab.getLayer();
			List<Layer> layers = layerLgroup.getLayer();
			
			for (Layer layer: layers) {
				System.out.println(layer.getTitle());
				System.out.println("	"+layer.getName());
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
