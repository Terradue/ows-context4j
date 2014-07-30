/**
 * 
 */
package com.terradue.owc4j.utils.wfs;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.stream.StreamSource;

import net.opengis.ows.v_1_0_0.DomainType;
import net.opengis.ows.v_1_0_0.Operation;
import net.opengis.ows.v_1_0_0.RequestMethodType;
import net.opengis.ows.v_1_0_0.ResponsiblePartySubsetType;
import net.opengis.ows.v_1_0_0.ServiceIdentification;
import net.opengis.ows.v_1_0_0.ServiceProvider;
import net.opengis.ows.v_1_0_0.WGS84BoundingBoxType;
import net.opengis.wfs.v_1_1_0.FeatureTypeType;
import net.opengis.wfs.v_1_1_0.WFSCapabilitiesType;

import com.terradue.owc4j.beans.atom.AtomContent.Type;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomLink;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.custom.BoundingBox;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.beans.owc.OwcOffering;
import com.terradue.owc4j.beans.owc.OwcOperation;
import com.terradue.owc4j.beans.owc.OwcOperation.Method;
import com.terradue.owc4j.exceptions.WrongVersionException;
import com.terradue.owc4j.utils.OwcOptions;
import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class WfsCapabilities_1_1_0 extends AbstractWfsCapabilities {

	/**
	 * 
	 */
	public WfsCapabilities_1_1_0() {
	}
	
	@Override
	public SupportedWfsVersion getVersion() {
		return SupportedWfsVersion.v_1_1_0;
	}
	
	@Override
	public String getVersionStr() {
		return "1.1.0";
	}

	@Override
	public OwcDocument getOwcFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		Unmarshaller unmarshaller = getUnmarshaller();

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WFSCapabilitiesType> wfsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(wfsCapabilitiesUrl), WFSCapabilitiesType.class);

		// Retrieve WFSCapabilities instance
		WFSCapabilitiesType wfsCapabilities = wfsCapabilitiesElement.getValue();

		ServiceIdentification serviceIdentification = wfsCapabilities.getServiceIdentification();
		if (serviceIdentification==null)
			throw new WrongVersionException(getVersion().toString());
		
//		Capability capab = wmsCapabilities.getCapability();
//		if (capab==null)
//			throw new WrongVersionException(getVersion().toString());
		
		OwcDocument owc = Utils.getBaseOwcDocument(wfsCapabilitiesUrl);
		OwcAtomFeed feed = owc.getFeed();
		
		// set title
		feed.setTitle(serviceIdentification.getTitle());
		
		// set id
		feed.setId(getCapabilitiesOnlineResource(wfsCapabilities));
		
		// TODO: <xsl:apply-templates select="ows:ServiceProvider | wms:ContactInformation | ContactInformation"/>
		// TODO: <xsl:apply-templates select="ows:ServiceIdentification/ows:Abstract | wms:Service/wms:Abstract | Service/Abstract"/>
		
		// set publisher
		feed.setPublisher(getPublisher(wfsCapabilities));
		
		// set georsswhere
		BoundingBox bbox = options.getBbox();
		if (bbox!=null)
			feed.setWhere(bbox);
		
		// TODO: set categories (293)
		
		List<OwcAtomEntry> entries = getEntriesFromWfs(wfsCapabilities, pattern, options);
		feed.getEntries().addAll(entries);
		
		return owc;
	}

	
	/**
	 * @param wfsCapabilities
	 * @param pattern
	 * @param options
	 * @return
	 */
	private List<OwcAtomEntry> getEntriesFromWfs(WFSCapabilitiesType wfsCapabilities, String pattern, OwcOptions options) throws Exception {
		ServiceIdentification serviceIdentification = wfsCapabilities.getServiceIdentification();
		if (serviceIdentification==null)
			throw new WrongVersionException(getVersion().toString());
		
		// get layers
		List<OwcAtomEntry> entries = new ArrayList<OwcAtomEntry>();
		List<FeatureTypeType> featureTypes = wfsCapabilities.getFeatureTypeList().getFeatureType();
		
		// limit
		if (options.getLimit()>0)
			featureTypes = featureTypes.subList(0, options.getLimit());
		
		for (FeatureTypeType featureType : featureTypes)
			if (pattern==null || featureType.getName().getLocalPart().matches(pattern))
				entries.add(getEntryFromWfsLayer(wfsCapabilities, featureType, options));

		return entries;
	}

	@Override
	public List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		Unmarshaller unmarshaller = getUnmarshaller();

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WFSCapabilitiesType> wfsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(wfsCapabilitiesUrl), WFSCapabilitiesType.class);

		// Retrieve WMSCapabilities instance
		WFSCapabilitiesType wfsCapabilities = wfsCapabilitiesElement.getValue();
		
		return getEntriesFromWfs(wfsCapabilities, pattern, options);
	}


	/**
	 * @param service
	 * @return
	 */
	private String getPublisher(WFSCapabilitiesType wfsCapabilities) {
		try {
			return wfsCapabilities.getServiceProvider().getProviderName();
		} catch(Exception e) {
			return null;
		}
	}
	
	
	/**
	 * @param wfsCapabilities
	 * @param operationName
	 * @return
	 */
	private Operation findOperation(WFSCapabilitiesType wfsCapabilities, String operationName) {
		for (Operation op : wfsCapabilities.getOperationsMetadata().getOperation()){
			if (op.getName().contentEquals(operationName))
				return op;
		}
		return null;
	}
	
	/**
	 * @param operation
	 * @param method
	 * @return
	 */
	private String findOperationHref(Operation operation, String method) {
		List<JAXBElement<RequestMethodType>> requests = operation.getDCP().get(0).getHTTP().getGetOrPost();
		for (JAXBElement<RequestMethodType> request : requests){
			if (request.getName().getLocalPart().contentEquals(method)){
				return request.getValue().getHref();
			}
		}
		
		return null;
	}

	/**
	 * @param operation
	 * @param string
	 * @return
	 */
	private String findOperationParameter(Operation operation, String parameterName) {
		for (DomainType dt : operation.getParameter()){
			if (dt.getName().contentEquals(parameterName))
				return dt.getValue().get(0);
		}
		return null;
	}

	
	/**
	 * @param wfsCapabilities
	 * @return
	 */
	private String getCapabilitiesOnlineResource(WFSCapabilitiesType wfsCapabilities) {
		try {
			// get operation GetCapabilities
			Operation operation = findOperation(wfsCapabilities, "GetCapabilities");
			
			// get href of GET method
			return findOperationHref(operation, "Get");
			
		} catch(Exception e) {
			return null;
		}
	}


	private String getOperationOnlineResource(WFSCapabilitiesType wfsCapabilities) {
		try {
			// get operation GetFeature
			Operation operation = findOperation(wfsCapabilities, getDefaultOperation());
			
			// get href of GET method
			return findOperationHref(operation, "Get");

		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * @param capability
	 * @return
	 */
	private String getDataFormat(WFSCapabilitiesType wfsCapabilities) {
		try {
			// get operation GetFeature
			Operation operation = findOperation(wfsCapabilities, getDefaultOperation());
			
			return findOperationParameter(operation, "outputFormat");
			
		} catch (Exception e) {
			return null;
		}		
	}



	/**
	 * @param capability
	 * @return
	 */
	private String getCapabilitiesFormat(WFSCapabilitiesType wfsCapabilities) {
		try {
			// get operation GetCapabilities			
			Operation operation = findOperation(wfsCapabilities, "GetCapabilities");

			return findOperationParameter(operation, "AcceptFormats");
			
		} catch(Exception e) {
			return null;
		}
	}



	/**
	 * @param l
	 * @return
	 */
//	private String getStyle(Layer l) {
//		try {
//			return l.getStyle().get(0).getName();
//		} catch (Exception e) {
//			return null;
//		}
//	}
	
	/**
	 * @param l
	 * @return
	 * @throws Exception 
	 */
	private OwcAtomEntry getEntryFromWfsLayer(WFSCapabilitiesType wfsCapabilities, FeatureTypeType f, OwcOptions options) throws Exception {
		BoundingBox bbox = options.getBbox();
		boolean firstY = false; // true only if WMS 1.3.0 and EPSG:4326
		
		// vars
		String capabOnlineResource = getCapabilitiesOnlineResource(wfsCapabilities);
		String operationOnlineResource = getOperationOnlineResource(wfsCapabilities);		
		String dataFormat = getDataFormat(wfsCapabilities);
		String capabFormat = getCapabilitiesFormat(wfsCapabilities);
		String offeringCode = getOfferingCode();
//
		// info recovering
		if (bbox==null) {
			//select="ancestor-or-self::wms:Layer/wms:EX_GeographicBoundingBox/wms:eastBoundLongitude | ancestor-or-self::Layer/LatLonBoundingBox/@maxx"/>
			WGS84BoundingBoxType bb = f.getWGS84BoundingBox().get(0);
//			LatLonBoundingBox bb = l.getLatLonBoundingBox();
			if (bb!=null)
				bbox = new BoundingBox(
						bb.getLowerCorner().get(0), 
						bb.getLowerCorner().get(1), 
						bb.getUpperCorner().get(0), 
						bb.getUpperCorner().get(1));
			// TODO get bbox from ancestor (for now only from itself or by parameter)
		}
		
		// entry
		OwcAtomEntry entry =  new OwcAtomEntry();
		String name = f.getName().getPrefix()+":"+f.getName().getLocalPart();
		entry.setId(capabOnlineResource==null ? name : (capabOnlineResource + name));
		entry.setTitle(f.getTitle() == null ? name : ""+f.getTitle());
//		
//		
//		/*395
//		<!-- Repeat feed author and dc:publisher elements to ease xml fragment 
//		extraction -->
//	<xsl:apply-templates
//		select="/*<BLANK>/ows:ServiceProvider | /wms:WMS_Capabilities/wms:Service/wms:ContactInformation | /WMT_MS_Capabilities/Service/ContactInformation"/>
//		*/
//		
//		/*401
//		TODO: where is dc:creator
//		<xsl:apply-templates select="wms:Attribution/wms:Title | Attribution/Title"/>
//		l.getAttribution().getTitle();
//		 */
//		
		//403 publisher
		ServiceProvider service = wfsCapabilities.getServiceProvider();
		ResponsiblePartySubsetType contact = service.getServiceContact();
		if (contact!=null) {
			AtomPersonConstruct author = new AtomPersonConstruct();
			author.setName(contact.getIndividualName());
			if (contact.getContactInfo()!=null 
					&& contact.getContactInfo().getAddress()!=null
					&& contact.getContactInfo().getAddress().getElectronicMailAddress()!=null
					&& contact.getContactInfo().getAddress().getElectronicMailAddress().size()>0)
			author.setEmail(""+contact.getContactInfo().getAddress().getElectronicMailAddress().get(0));
			
			if (service.getProviderSite()!=null)
				author.setUri(service.getProviderSite().getHref());
			entry.getAuthors().add(author);
		}
		entry.setPublisher(service.getProviderName());
		
		// set updated, using standard XMLGregorianCalendar parser
		entry.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		
		// set rights
		ServiceIdentification serviceIdentification = wfsCapabilities.getServiceIdentification();
		if (serviceIdentification != null)
			entry.setRights("Fee:" + serviceIdentification.getFees()==null ? "" : serviceIdentification.getFees() + " / Constraints:" + serviceIdentification.getAccessConstraints());
		
		//420 georss:where
		entry.setWhere(bbox);
		
		
//		String style = getStyle(l);
		
		//483 variable get_request
		String getRequest = Utils.getBaseUrl(operationOnlineResource);
		if (!operationOnlineResource.endsWith("?"))
			getRequest += "?";
		getRequest += "SERVICE="+SERVICE_NAME
				+ "&VERSION="+getVersionStr()
					+ "&REQUEST="+getDefaultOperation()
					+ "&BBOX="+bbox.toString(firstY)
					+ "&OUTPUTFORMAT="+dataFormat
					+ "&TYPENAME="+name
					+ "&MAXFEATURES="+getDefaultMaxFeatures();
					
		String getCapabilitiesRequest = capabOnlineResource + (capabOnlineResource.endsWith("?") ? "" : "?")
									+"SERVICE="+SERVICE_NAME
									+ "&VERSION="+getVersionStr()
									+ "&REQUEST=GetCapabilities";

		
		//512 enclosure link
		AtomLink link = new AtomLink();
		link.setRel("enclosure");
		link.setType(dataFormat); // respects the patterns
		link.setTitle(SERVICE_NAME + " output for " + f.getTitle());
		link.setHref(getRequest);
		entry.getLinks().add(link);
		
		
		String describedByRequest = operationOnlineResource
			+ (operationOnlineResource.endsWith("?") ? "" : "?")
				+"SERVICE="+SERVICE_NAME
				+ "&VERSION="+getVersionStr()
				+ "&REQUEST=describeFeatureType"
				+ "&TYPENAME="+name;
			//SERVICE=',$service_name, '&VERSION=',$version,'&REQUEST=describeFeatureType&TYPENAME=',$name,'')"
		
		//543 describedby link
		link = new AtomLink();
		link.setRel("describedby");
		link.setType("text/xml");
		link.setTitle("Description of Features");
		link.setHref(describedByRequest);
		entry.getLinks().add(link);

		//553 via link
		link = new AtomLink();
		link.setRel("via");
		link.setType(capabFormat);
		link.setTitle("Original GetCapabilities document");
		link.setHref(getCapabilitiesRequest);
		entry.getLinks().add(link);

		// TODO <xsl:apply-templates select="wms:DataURL | DataURL | wms:MetadataURL | MetadataURL"/>
		
		
		// content
		AtomInlineTextContent content = new AtomInlineTextContent();
		content.setType(Type.html);
		String strContent = Utils.formatStr("<br/>" +
				"This resource is available from a OGC %s Service (version %s)" +
				" and contains the following access points:" +
				"<ul>" +
				"	<li>" +
				"		<a href='%s'>%s</a>" +
				"		request in %s (atom:link[@rel=\"enclosure\"])" +
				"	</li>" +
				"	<li>" +
				"		<a href='%s'>GetCapabilities</a>" +
				"		request in (atom:link[@rel=\"via\"])" +
				"	</li>" +
				// <xsl:apply-templates select="wms:DataURL | DataURL | wms:MetadataURL | MetadataURL" mode="html"/>
				"</ul>" +
				"%s <p style='font-size:small'>OGC Context CITE Testing XSLT (Extensible Stylesheet Language Transformations) by Terradue Srl.</p>" +
				"",
				SERVICE_NAME, getVersionStr(), getRequest, getDefaultOperation(), dataFormat, getCapabilitiesRequest, f.getAbstract());

		//strContent = strContent.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		content.setContent(strContent);
		entry.setContent(content);
		
		//615 offering
		OwcOffering offering = new OwcOffering();
		offering.setCode(offeringCode);
		
		// operation getCapabilities
		OwcOperation operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode("GetCapabilities");
		operation.setHref(getCapabilitiesRequest);
		offering.getOperations().add(operation);
		
		// operation getrequest
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode(getDefaultOperation());
		operation.setHref(getRequest);
		offering.getOperations().add(operation);

		entry.setOffering(offering);
		
		return entry;
		
	}

}
