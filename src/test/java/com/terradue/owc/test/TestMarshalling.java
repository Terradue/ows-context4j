/**
 * 
 */
package com.terradue.owc.test;

import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;

import com.terradue.owc4j.beans.atom.AtomCategory;
import com.terradue.owc4j.beans.atom.AtomDateConstruct;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomLink;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.atom.AtomPlainTextConstruct;
import com.terradue.owc4j.beans.atom.AtomContent.Type;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.beans.owc.OwcOffering;
import com.terradue.owc4j.beans.owc.OwcOperation;
import com.terradue.owc4j.beans.owc.OwcOperation.Method;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class TestMarshalling {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			// create a new OWC Document Object
			OwcDocument owc = new OwcDocument();
			OwcAtomFeed feed = owc.getFeed();
			feed.setId("OSWContext_04");

			// title
			feed.setTitle("OWS Context Example with FAO and i-Marine Data");

			AtomDateConstruct d = new AtomDateConstruct();
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(2013, 1, 6, 1, 10, 42);
			d.setDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
			feed.setUpdated(d);
			
			
			feed.setPublisher("Terradue Srl.");

			feed.getAuthors().add(new AtomPersonConstruct("iMarine"));

			// categories
			List<AtomCategory> categories = feed.getCategories();
			AtomCategory category = new AtomCategory();
			category.setLabel("This file is compliant with version 1.0 of OGC Context");
			category.setScheme("http://www.opengis.net/owc/specReference");
			category.setTerm("http://www.opengis.net/spec/owc/1.0/conf/atom");
			categories.add(category);		

			// entries
			addEntries(feed.getEntries());
			
			// MARSHALLING
			JAXBContext context = JAXBContext.newInstance(OwcDocument.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(owc.getFeed(), System.out);
			System.out.println("ok");
			
		} catch (JAXBException e) {
			System.out.println("Error during marchalling operation.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error during owc model instance creation.");
			e.printStackTrace();
		}

	}

	/**
	 * @param entries
	 * @throws Exception 
	 */
	private static void addEntries(List<OwcAtomEntry> entries) throws Exception {
		
		// sample entry
		OwcAtomEntry entry =  new OwcAtomEntry();
		entry.setId("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?/");
		entry.setTitle("GEBCO_08 Grid");
		
		// author
		AtomPersonConstruct author = new AtomPersonConstruct();
		author.setName("Pauline Weatherall");
		author.setEmail("enquiries@bodc.ac.uk");
		author.setUri("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?");
		author.getOtherAttributes().put(new QName("xmlns:dc"), "http://purl.org/dc/elements/1.1/");
		author.getOtherAttributes().put(new QName("xmlns:georss"), "http://www.georss.org/georss");
		author.getOtherAttributes().put(new QName("xmlns:gml"), "http://www.opengis.net/gml");
		author.getOtherAttributes().put(new QName("xmlns:owc"), "http://www.opengis.net/owc/1.0");
		author.getOtherAttributes().put(new QName("xmlns:ows"), "http://www.opengis.net/ows");
		entry.getAuthors().add(author);
		
		entry.setPublisher("British Oceanographic Data Centre (BODC)");
		
		// using standard XMLGregorianCalendar parser
		entry.setUpdated("2013-03-21T14:48:26Z");
		
		AtomPlainTextConstruct rights = new AtomPlainTextConstruct();
		rights.setContent("Fee: / Contraints:Imagery from this WMS is not to be used for navigation or any purpose " +
				"relating to safety at sea Although every effort has been made to make sure that the imagery is as " +
				"error free as possible, BODC and GEBCO give no warranty as to the quality or completeness of the " +
				"imagery or to the non-interruption or continuation of the service.");
		rights.getOtherAttributes().put(new QName("xmlns:dc"), "http://purl.org/dc/elements/1.1/");
		entry.setRights(rights);
		
		// links
		AtomLink link;

		link = new AtomLink();
		link.setRel("enclosure");
		link.setType("image/png"); // respects the patterns
		link.setTitle("WMS output for GEBCO_08 Grid");
		link.setHref("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&BBOX=-69,40,12,65&SRS=EPSG:4326&WIDTH=1620&HEIGHT=500&LAYERS=GEBCO_08_Grid&FORMAT=image/png&BGCOLOR=0xffffff&TRANSPARENT=TRUE&EXCEPTIONS=application/vnd.ogc.se_xml");
		entry.getLinks().add(link);

		link = new AtomLink();
		link.setRel("icon");
		link.setType("image/png");
		link.setTitle("Preview for GEBCO_08 Grid");
		link.setHref("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&SRS=EPSG:4326&BBOX=-69,40,12,65&WIDTH=324&HEIGHT=100&LAYERS=GEBCO_08_Grid&STYLES=&FORMAT=image/png&BGCOLOR=0xffffff&TRANSPARENT=TRUE&EXCEPTIONS=application/vnd.ogc.se_xml");
		entry.getLinks().add(link);

		link = new AtomLink();
		link.setRel("via");
		link.setType("application/vnd.ogc.wms_xml");
		link.setTitle("Original GetCapabilities document");
		link.setHref("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetCapabilities");
		entry.getLinks().add(link);
		
		// content
		AtomInlineTextContent content = new AtomInlineTextContent();
		content.setType(Type.html);
		content.setContent("<br/> <img border='1' align='right' height='100'src='http://www.gebco.net/data_and_products/gebco_web_services/"
				+"web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetMap&SRS=EPSG:4326&BBOX=-69,40,12,65&WIDTH=324"
				+"&HEIGHT=100&LAYERS=GEBCO_08_Grid&STYLES=&FORMAT=image/png&BGCOLOR=0xffffff&TRANSPARENT=TRUE&"
				+"EXCEPTIONS=application/vnd.ogc.se_xml'/>\n"
				+"<br/> This resource is available from a OGC WMS Service (version 1.1.1) and it contains the following access points: <ul> <li> <"
				+"a href='http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&"
				+"REQUEST=GetMap&BBOX=-69,40,12,65&SRS=EPSG:4326&WIDTH=1620&HEIGHT=500&LAYERS=GEBCO_08_Grid&FORMAT=image/png"
				+"&BGCOLOR=0xffffff&TRANSPARENT=TRUE&EXCEPTIONS=application/vnd.ogc.se_xml'>\n"
				+"GetMap </a> request in image/png (atom:link[@rel=\"enclosure\"]) </li> <li> <a "
				+"href='http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&"
				+"REQUEST=GetCapabilities'>\n"
				+"GetCapabilities </a> request (atom:link[@rel=\"via\"]) </li> </li> </ul> Bathymetry data from the GEBCO_08 Grid "
				+"<p style='font-size:small'>OGC Context CITE Testing XSLT (Extensible Stylesheet Language Transformations) by Terradue Srl.</p>");
		entry.setContent(content);
		
		addOffering(entry);

		entries.add(entry);

	}

	/**
	 * @param entry
	 * @throws Exception 
	 */
	private static void addOffering(OwcAtomEntry entry) throws Exception {
		OwcOffering offering = new OwcOffering();
		OwcOperation operation; 

		offering.setCode("http://www.opengis.net/spec/owc/1.0/req/atom/wms");
		
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode("GetCapabilities");
		operation.setHref("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&REQUEST=GetCapabilities");
		offering.getOperations().add(operation);
		
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode("GetMap");
		operation.setHref("http://www.gebco.net/data_and_products/gebco_web_services/web_map_service/mapserv?&SERVICE=WMS&VERSION=1.1.1&" +
				"REQUEST=GetMap&BBOX=-69,40,12,65&SRS=EPSG:4326&WIDTH=1620&HEIGHT=500&LAYERS=GEBCO_08_Grid&FORMAT=image/png&" +
				"BGCOLOR=0xffffff&TRANSPARENT=TRUE&EXCEPTIONS=application/vnd.ogc.se_xml");
		offering.getOperations().add(operation);

		entry.setOffering(offering);
	}

}
