/**
 * 
 */
package com.terradue.owc4j.utils.wms;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.transform.stream.StreamSource;

import net.opengis.wms.v_1_1_1.Capability;
import net.opengis.wms.v_1_1_1.ContactInformation;
import net.opengis.wms.v_1_1_1.Get;
import net.opengis.wms.v_1_1_1.LatLonBoundingBox;
import net.opengis.wms.v_1_1_1.Layer;
import net.opengis.wms.v_1_1_1.LegendURL;
import net.opengis.wms.v_1_1_1.SRS;
import net.opengis.wms.v_1_1_1.Service;
import net.opengis.wms.v_1_1_1.Style;
import net.opengis.wms.v_1_1_1.WMTMSCapabilities;

import com.terradue.owc4j.Constants;
import com.terradue.owc4j.beans.atom.AtomContent.Type;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomLink;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.custom.BoundingBox;
import com.terradue.owc4j.beans.georss.GeorssWhere;
import com.terradue.owc4j.beans.georss.GmlExterior;
import com.terradue.owc4j.beans.georss.GmlLinearRing;
import com.terradue.owc4j.beans.georss.GmlPolygonElement;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.beans.owc.OwcLegendUrl;
import com.terradue.owc4j.beans.owc.OwcOffering;
import com.terradue.owc4j.beans.owc.OwcOperation;
import com.terradue.owc4j.beans.owc.OwcOperation.Method;
import com.terradue.owc4j.beans.owc.OwcStyleSet;
import com.terradue.owc4j.exceptions.WrongVersionException;
import com.terradue.owc4j.utils.OwcOptions;
import com.terradue.owc4j.utils.Utils;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class WmsCapabilities_1_1_1 extends AbstractWmsCapabilities {

	/**
	 * 
	 */
	public WmsCapabilities_1_1_1() {
	}
	
	@Override
	public SupportedWmsVersion getVersion() {
		return SupportedWmsVersion.v_1_1_1;
	}
	
	@Override
	public String getVersionStr() {
		return "1.1.1";
	}

	@Override
	public OwcDocument getOwcFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		Unmarshaller unmarshaller = getUnmarshaller();

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WMTMSCapabilities> wmsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(wmsCapabilitiesUrl), WMTMSCapabilities.class);

		// Retrieve WMSCapabilities instance
		WMTMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();

		Service service = wmsCapabilities.getService();
		if (service==null)
			throw new WrongVersionException(getVersion().toString());

		Capability capab = wmsCapabilities.getCapability();
		if (capab==null)
			throw new WrongVersionException(getVersion().toString());
		
		OwcDocument owc = Utils.getBaseOwcDocument(wmsCapabilitiesUrl);
		OwcAtomFeed feed = owc.getFeed();
		
		// set title
		feed.setTitle(service.getTitle());
		
		// set id
		feed.setId(getCapabilitiesOnlineResource(capab));
		
		// TODO: <xsl:apply-templates select="ows:ServiceProvider | wms:ContactInformation | ContactInformation"/>
		// TODO: <xsl:apply-templates select="ows:ServiceIdentification/ows:Abstract | wms:Service/wms:Abstract | Service/Abstract"/>
		
		// set publisher
		feed.setPublisher(getPublisher(service));
		
		// set georsswhere
		BoundingBox bbox = options.getBbox();
		if (bbox!=null)
			feed.setWhere(bbox);
		
		// TODO: set categories (293)
		
		List<OwcAtomEntry> entries = getEntriesFromWmsCapabilities(wmsCapabilities, pattern, options);
		feed.getEntries().addAll(entries);
		
		return owc;
	}

	
	/**
	 * @param wmsCapabilities
	 * @param pattern
	 * @param options
	 * @return
	 */
	private List<OwcAtomEntry> getEntriesFromWmsCapabilities(WMTMSCapabilities wmsCapabilities, String pattern, OwcOptions options) throws Exception {
		// check for right version
		Service service = wmsCapabilities.getService();
		if (service==null)
			throw new WrongVersionException(getVersion().toString());
		Capability capab = wmsCapabilities.getCapability();
		if (capab==null)
			throw new WrongVersionException(getVersion().toString());
		
		// get layers
		List<OwcAtomEntry> entries = new ArrayList<OwcAtomEntry>();		
		List<LayerInfo> layersInfo = getQueryableLayers(capab.getLayer(), options.checkQueryable(), null);
		
		// limit
		if (options.getLimit()>0)
			layersInfo = layersInfo.subList(0, options.getLimit());
		
		for (LayerInfo layerInfo : layersInfo)
			if (pattern==null || layerInfo.getLayer().getName().matches(pattern))
				entries.add(getEntryFromWmsLayer(wmsCapabilities, layerInfo, options));

		return entries;
	}

	@Override
	public List<OwcAtomEntry> getEntriesFromWmsCapabilities(String wmsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		Unmarshaller unmarshaller = getUnmarshaller();

		// Unmarshal the given URL, retrieve WMSCapabilities element
		JAXBElement<WMTMSCapabilities> wmsCapabilitiesElement = unmarshaller.unmarshal(new StreamSource(wmsCapabilitiesUrl), WMTMSCapabilities.class);

		// Retrieve WMSCapabilities instance
		WMTMSCapabilities wmsCapabilities = wmsCapabilitiesElement.getValue();
		
		return getEntriesFromWmsCapabilities(wmsCapabilities, pattern, options);
	}

	/**
	 * @param layer
	 * @return
	 */
	private List<LayerInfo> getQueryableLayers(Layer layer, boolean checkQueryable, String parentSrs) {
		List<LayerInfo> result = new ArrayList<LayerInfo>();
		
		if (layer != null) {
			String srs;
			List<String> srss = new ArrayList<String>();
			if (layer.getSRS()!=null)
				for (SRS s : layer.getSRS()){
					String value = s.getvalue();
					if (value.endsWith(","))
						value = value.substring(0, value.length()-1);
					srss.add(value);
				}
					
			
			if (srss.contains("EPSG:4326"))
				srs = "EPSG:4326";
			else if (srss.contains("CRS:84"))
				srs = "CRS:84";
			else if (srss.size()>0)
				srs = srss.get(0);
			else
				srs = parentSrs;
			
			boolean isQueryable = (checkQueryable ? 
					layer.getQueryable()=="1" :
					layer.getLayer().size()==0);
			if (isQueryable)
				// base case
				result.add(new LayerInfo(layer, srs));
			else {
				// recursive case
				List<Layer> layers = layer.getLayer();
				for (Layer l: layers)
					result.addAll(getQueryableLayers(l, checkQueryable, srs));
			}
		}

		return result;
	}
	

	/**
	 * @param service
	 * @return
	 */
	private String getPublisher(Service service) {
		try {
			return service.getContactInformation().getContactPersonPrimary().getContactOrganization();
		} catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * @param capab
	 * @return
	 */
	private String getCapabilitiesOnlineResource(Capability capab) {
		try {
			Get get = null;
			for (Object o : capab.getRequest().getGetCapabilities().getDCPType().get(0).getHTTP().getGetOrPost()){
				if (o instanceof Get){
					get = (Get)o;
					break;
				}
			}
			
			return get.getOnlineResource().getXlinkHref();
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * @param capability
	 * @return
	 */
	private String getOperationOnlineResource(Capability capab) {
		try {
			Get get = null;
			for (Object o : capab.getRequest().getGetMap().getDCPType().get(0).getHTTP().getGetOrPost()){
				if (o instanceof Get){
					get = (Get)o;
					break;
				}
			}
			return get.getOnlineResource().getXlinkHref();
		} catch(Exception e) {
			return null;
		}
	}

	/**
	 * @param capability
	 * @return
	 */
	private String getDataFormat(Capability capability) {
		try {
			return capability.getRequest().getGetMap().getFormat().get(0).getvalue();
		} catch (Exception e) {
			return null;
		}		
	}

	/**
	 * @param capability
	 * @return
	 */
	private String getExceptionFormat(Capability capability) {
		try {
			return capability.getException().getFormat().get(0).getvalue();
		} catch (Exception e) {
			return null;
		}		
	}

	/**
	 * @param capability
	 * @return
	 */
	private String getCapabilitiesFormat(Capability capability) {
		try {
			return capability.getRequest().getGetCapabilities().getFormat().get(0).getvalue();
		} catch (Exception e) {
			return null;
		}		
	}

	/**
	 * @param l
	 * @return
	 */
	private String getStyle(Layer l) {
		try {
			return l.getStyle().get(0).getName();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param l
	 * @return
	 * @throws Exception 
	 */
	private OwcAtomEntry getEntryFromWmsLayer(WMTMSCapabilities wmsCapabilities, LayerInfo layerInfo, OwcOptions options) throws Exception {
		Layer l = layerInfo.getLayer();
		String xrs = layerInfo.getSrs();
		String xrsName = "SRS";
		BoundingBox bbox = options.getBbox();
		boolean firstY = false; // true only if WMS 1.3.0 and EPSG:4326
		
		// vars
		Capability capability = wmsCapabilities.getCapability();
		String capabOnlineResource = getCapabilitiesOnlineResource(capability);
		String operationOnlineResource = getOperationOnlineResource(capability);		
		String dataFormat = getDataFormat(capability);
		String exceptionFormat = getExceptionFormat(capability);
		String capabFormat = getCapabilitiesFormat(capability);
		String offeringCode = getOfferingCode();

		// info recovering
		if (bbox==null) {
			//select="ancestor-or-self::wms:Layer/wms:EX_GeographicBoundingBox/wms:eastBoundLongitude | ancestor-or-self::Layer/LatLonBoundingBox/@maxx"/>
			LatLonBoundingBox bb = l.getLatLonBoundingBox();
			if (bb!=null)
				bbox = new BoundingBox(
						Double.parseDouble(bb.getMinx()), 
						Double.parseDouble(bb.getMiny()), 
						Double.parseDouble(bb.getMaxx()), 
						Double.parseDouble(bb.getMaxy()));
			// TODO get bbox from ancestor (for now only from itself or by parameter)
		}
		
		// entry
		OwcAtomEntry entry =  new OwcAtomEntry();
		entry.setId(capabOnlineResource==null ? l.getName() : (capabOnlineResource + l.getName()));
		entry.setTitle(l.getTitle() == null ? l.getName() : l.getTitle());
		
		
		/*395
		<!-- Repeat feed author and dc:publisher elements to ease xml fragment 
		extraction -->
	<xsl:apply-templates
		select="/*<BLANK>/ows:ServiceProvider | /wms:WMS_Capabilities/wms:Service/wms:ContactInformation | /WMT_MS_Capabilities/Service/ContactInformation"/>
		*/
		
		/*401
		TODO: where is dc:creator
		<xsl:apply-templates select="wms:Attribution/wms:Title | Attribution/Title"/>
		l.getAttribution().getTitle();
		 */
		
		//403 publisher
		Service service = wmsCapabilities.getService();
		ContactInformation contact = service.getContactInformation();
		if (contact!=null) {
			AtomPersonConstruct author = new AtomPersonConstruct();
			author.setName(contact.getContactPersonPrimary().getContactPerson());
			author.setEmail(contact.getContactElectronicMailAddress());
			author.setUri(capabOnlineResource);
			entry.getAuthors().add(author);
		}
		entry.setPublisher(contact.getContactPersonPrimary().getContactOrganization());
		
		// using standard XMLGregorianCalendar parser
		entry.setUpdated(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
		//entry.setRights("Fee:" + service.getFees()==null ? "" : service.getFees() + " / Constraints:" + service.getAccessConstraints());
		entry.setRights(Utils.formatStr("Fee: %s / Constraints: %s", service.getFees(), service.getAccessConstraints()));
		
		//420 georss:where
		entry.setWhere(bbox);
		
		
		double georatio = (bbox==null ? null : Math.abs((bbox.getMaxx()-bbox.getMinx()) / (bbox.getMaxy()-bbox.getMiny())));  
		String style = getStyle(l);
		
		//483 variable get_request
		String getRequest = Utils.getBaseUrl(operationOnlineResource);
		//if (!operationOnlineResource.endsWith("?"))
		//	getRequest += "?";
		getRequest += "?SERVICE="+SERVICE_NAME
				+ "&VERSION="+getVersionStr()
					+ "&REQUEST="+getDefaultOperation()
					+ "&BBOX="+bbox.toString(firstY)
					+ "&"+xrsName+"="+xrs
					+ "&WIDTH="+Math.floor(options.getMapHeight()*georatio)
					+ "&HEIGHT="+options.getMapHeight()
					+ "&LAYERS="+l.getName()
					+ "&FORMAT="+dataFormat
					+ "&BGCOLOR=0xffffff"
					+ "&TRANSPARENT=TRUE"
					+ "&EXCEPTIONS="+exceptionFormat;
		
		String getCapabilitiesRequest = capabOnlineResource + (capabOnlineResource.endsWith("?") ? "" : "?")
									+"SERVICE="+SERVICE_NAME
									+ "&VERSION="+getVersionStr()
									+ "&REQUEST=GetCapabilities";

		
		//512 enclosure link
		AtomLink link = new AtomLink();
		link.setRel("enclosure");
		link.setType(dataFormat); // respects the patterns
		link.setTitle(SERVICE_NAME + " output for " + l.getTitle());
		link.setHref(getRequest);
		entry.getLinks().add(link);
		
		
		String quicklookRequest = 
				Utils.formatStr("%sSERVICE=%s"
					+ "&VERSION=%s"
					+ "&REQUEST=GetMap"
					+ "&%s=%s"
					+ "&BBOX=%s"
					+ "&WIDTH=%s"
					+ "&HEIGHT=%s"
					+ "&LAYERS=%s"
					+ "&STYLES=%s"
					+ "&FORMAT=%s"
					+ "&BGCOLOR=0xffffff"
					+ "&TRANSPARENT=TRUE"
					+ "&EXCEPTIONS=%s", 
					operationOnlineResource, SERVICE_NAME, getVersionStr(), xrsName, xrs, bbox.toString(firstY), Math.floor(options.getIconHeight()*georatio), options.getIconHeight(), 
						l.getName(), style, dataFormat, exceptionFormat
				);
				
		//522  icon link
		link = new AtomLink();
		link.setRel("icon");
		link.setType(dataFormat);
		link.setTitle("Preview for " + l.getTitle());
		link.setHref(quicklookRequest);
		entry.getLinks().add(link);
		

//		String describedByRequest = operationOnlineResource
//			+ (operationOnlineResource.endsWith("?") ? "" : "?")
//				+"SERVICE="+SERVICE_NAME
//				+ "&VERSION="+getVersionStr()
//				+ "&REQUEST=describeFeatureType"
//				+ "&TYPENAME="+l.getName()
//			//SERVICE=',$service_name, '&VERSION=',$version,'&REQUEST=describeFeatureType&TYPENAME=',$name,'')"
		
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
		String strContent = ""		
						+"<br/>"
						+"<img border='1' align='right' height='" + options.getIconHeight()+"' src='" + quicklookRequest + "' />";
		// <<xsl:apply-templates select="wms:Attribution | Attribution" mode="html"/>>
		strContent += Utils.formatStr("<br/>" +
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
				SERVICE_NAME, getVersionStr(), getRequest, getDefaultOperation(), dataFormat, getCapabilitiesRequest, l.getAbstract());

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
		
		// operation getmap
		operation = new OwcOperation();
		operation.setMethod(Method.GET);
		operation.setCode(getDefaultOperation());
		operation.setHref(getRequest);
		offering.getOperations().add(operation);

		for (Style s : l.getStyle()){
			OwcStyleSet styleSet = new OwcStyleSet();
			styleSet.setName(s.getName());
			styleSet.setTitle(s.getTitle());
			styleSet.setAbstract(s.getAbstract());
			OwcLegendUrl legendUrl = new OwcLegendUrl();
			LegendURL lu = s.getLegendURL().get(0);
			if (lu!=null) {
				legendUrl.setHref(lu.getOnlineResource().getXlinkHref());
//				legendUrl.setType(lu.getFormat());
			}
			styleSet.setLegendUrl(legendUrl);
			offering.getStyleSets().add(styleSet);
		}
		
		entry.setOffering(offering);
		
		return entry;
	}


	public class LayerInfo {
		private Layer layer;
		private String srs;

		public LayerInfo(Layer layer, String srs) {
			this.layer = layer;
			this.srs = srs;
		}
		public LayerInfo(Layer layer) {
			this.layer = layer;
		}
		public String getSrs() {
			return srs;
		}
		public Layer getLayer() {
			return layer;
		}
		public void setSrs(String srs) {
			this.srs = srs;
		}
		public void setLayer(Layer layer) {
			this.layer = layer;
		}		
	}

}
