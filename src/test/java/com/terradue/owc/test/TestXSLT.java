/**
 * 
 */
package com.terradue.owc.test;

import java.io.InputStream;
import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestXSLT {

	private static String url = 
//			"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities"
//		"https://dl.dropboxusercontent.com/u/24368142/t2/wmsCapabilities.xml";
//		"http://gdr.ess.nrcan.gc.ca/wmsconnector/com.esri.wms.Esrimap/energy_e?SERVICE=WMS&REQUEST=GetCapabilities"
			"http://ows.genesi-dec.eu/geoserver/ows?service=wms&version=1.3.0&request=GetCapabilities"
//			"http://demo.cubewerx.com/demo/cubeserv/cubeserv.cgi?SERVICE=wms&VERSION=1.3.0&REQUEST=GetCapabilities"
//			"http://demo.lizardtech.com/lizardtech/iserv/ows?service=WMS&version=1.3.0&request=GETCAPABILITIES" // 1.1.0
			;
	private static String urlXsl = "http://dl.dropboxusercontent.com/u/24368142/ows2owc.xsl"; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			
			InputStream xslInputStream = new URL(urlXsl).openStream();
			StreamSource xslStream = new StreamSource(xslInputStream);        
			Transformer transformer = factory.newTransformer(xslStream);
			
			InputStream capabInputStream = new URL(url).openStream();
			StreamSource in = new StreamSource(capabInputStream);
			
			String outputHTML = null;
			StreamResult out = new StreamResult(outputHTML);
			transformer.transform(in, out);
			System.out.println("The generated HTML file is:" + outputHTML);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
