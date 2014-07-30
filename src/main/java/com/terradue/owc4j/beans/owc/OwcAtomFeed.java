/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Element;

import com.terradue.owc4j.Constants;
import com.terradue.owc4j.beans.atom.AtomCategory;
import com.terradue.owc4j.beans.atom.AtomDateConstruct;
import com.terradue.owc4j.beans.atom.AtomGenerator;
import com.terradue.owc4j.beans.atom.AtomIcon;
import com.terradue.owc4j.beans.atom.AtomId;
import com.terradue.owc4j.beans.atom.AtomLink;
import com.terradue.owc4j.beans.atom.AtomLogo;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.atom.AtomPlainTextConstruct;
import com.terradue.owc4j.beans.atom.AtomTextConstruct;
import com.terradue.owc4j.beans.atom.AtomXHTMLTextConstruct;
import com.terradue.owc4j.beans.custom.BoundingBox;
import com.terradue.owc4j.beans.georss.GeorssWhere;
import com.terradue.owc4j.beans.georss.GmlExterior;
import com.terradue.owc4j.beans.georss.GmlLinearRing;
import com.terradue.owc4j.beans.georss.GmlPolygonElement;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */

@XmlRootElement(name = "feed")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id","title","subTitle","icon","logo","categories","display","updated","date","where","authors","contributors","publisher","generator","links","rights","extentionElements","entries"})
public class OwcAtomFeed {
	
	/**
	 * 
	 */
	public OwcAtomFeed() {
		super();
	}
	
	public OwcAtomFeed(String id) {
		this.setId(id);
	}
	
	
//  <ref name="atomId"/>
	@XmlElement(required=true)
	AtomId id;
	
//  <ref name="atomTitle"/>
    @XmlElements(value = { 
            @XmlElement(name="title", type=AtomXHTMLTextConstruct.class, required=true),
            @XmlElement(name="title", type=AtomPlainTextConstruct.class, required=true),
    })
    AtomTextConstruct title;

	@XmlElement(name="author")
	List<AtomPersonConstruct> authors;
	
	@XmlElementRef
	List<AtomCategory> categories;

//  <zeroOrMore>
//    <ref name="atomContributor"/>
//  </zeroOrMore>
	@XmlElement(name="contributor")
	List<AtomPersonConstruct> contributors;

//  <optional>
//    <ref name="atomGenerator"/>
//  </optional>
	@XmlElement(required=false)
	AtomGenerator generator;
	
//  <optional>
//    <ref name="atomIcon"/>
//  </optional>
	@XmlElementRef
	AtomIcon icon;

//  <zeroOrMore>
//    <ref name="atomLink"/>
//  </zeroOrMore>
	@XmlElementRef
	private List<AtomLink> links;

//  <optional>
//    <ref name="atomLogo"/>
//  </optional>
	@XmlElementRef
	AtomLogo logo;

//  <optional>
//    <ref name="atomRights"/>
//  </optional>
	@XmlElements(value = { 
	        @XmlElement(name="rights", type=AtomPlainTextConstruct.class, required=false),
	        @XmlElement(name="rights", type=AtomXHTMLTextConstruct.class, required=false),
	})
	private AtomTextConstruct rights;

//  <optional>
//    <ref name="atomSubtitle"/>
//  </optional>
	@XmlElements(value = { 
			@XmlElement(name="subtitle", type=AtomPlainTextConstruct.class, required=false),
			@XmlElement(name="subtitle", type=AtomXHTMLTextConstruct.class, required=false),
	})
    AtomTextConstruct subTitle;

//  <ref name="atomUpdated"/>
	@XmlElement
	AtomDateConstruct updated;

//  <zeroOrMore>
//    <ref name="extensionElement"/>
//  </zeroOrMore>
	@XmlAnyElement
	private List<Element> extentionElements;

//<zeroOrMore>
//  <ref name="atomEntry"/>
//</zeroOrMore>
	@XmlElementRef
	List<OwcAtomEntry> entries;
	
	@XmlAttribute(name="xml:lang", required=false)
	private String xmlLang = "en";

	
	/**
	 * @return the atomAuthors
	 */
	public List<AtomPersonConstruct> getAuthors() {
		if (authors==null)
			authors = new ArrayList<AtomPersonConstruct>();
		return authors;
	}
	
	/**
	 * @param atomAuthors the atomAuthors to set
	 */
	public void setAuthors(List<AtomPersonConstruct> authors) {
		this.authors = authors;
	}
	
	/**
	 * @return the atomCategories
	 */
	public List<AtomCategory> getCategories() {
		if (categories==null)
			categories = new ArrayList<AtomCategory>();
		return categories;
	}
	
	/**
	 * @param atomCategories the atomCategories to set
	 */
	public void setCategories(List<AtomCategory> categories) {
		this.categories = categories;
	}
	
	/**
	 * @return the atomContributors
	 */
	public List<AtomPersonConstruct> getContributors() {
		if (contributors==null)
			contributors = new ArrayList<AtomPersonConstruct>();
		return contributors;
	}
	
	/**
	 * @param contributors the atomContributors to set
	 */
	public void setContributors(List<AtomPersonConstruct> contributors) {
		this.contributors = contributors;
	}
	
	/**
	 * @param atomGenerator the atomGenerator to set
	 */
	public void setGenerator(AtomGenerator generator) {
		this.generator = generator;
	}
	
	/**
	 * @return the atomGenerator
	 */
	public AtomGenerator getGenerator() {
		return generator;
	}
	
	/**
	 * @return the id
	 */
	public AtomId getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(AtomId id) {
		this.id = id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = new AtomId(id);
	}
	
	
	public void setTitle(AtomPlainTextConstruct title) {
		this.title = title;
	}
	
	public void setTitle(AtomXHTMLTextConstruct title) {
		this.title = title;
	}
		
	/**
	 * @return the title
	 */
	public AtomTextConstruct getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = new AtomPlainTextConstruct(title);
	}
	

	
	/**
	 * @return the entries
	 */
	public List<OwcAtomEntry> getEntries() {
		if (entries==null)
			entries = new ArrayList<OwcAtomEntry>();
		return entries;
	}
	
	/**
	 * @param entries the entries to set
	 */
	public void setEntries(List<OwcAtomEntry> entries) {
		this.entries = entries;
	}
	
	public void setRights(AtomPlainTextConstruct rights) {
		this.rights = rights;
	}
	
	public void setRights(AtomXHTMLTextConstruct rights) {
		this.rights = rights;
	}
	
	public AtomTextConstruct getRights() {
		return rights;
	}
	
	public void setRights(String rights) {
		this.rights = new AtomPlainTextConstruct(rights);
	}

	/**
	 * @return the extentionElements
	 */
	public List<Element> getExtentionElements() {
		if (extentionElements==null)
			extentionElements = new ArrayList<Element>();
		return extentionElements;
	}
	
	/**
	 * @param extentionElements the extentionElements to set
	 */
	public void setExtentionElements(List<Element> extentionElements) {
		this.extentionElements = extentionElements;
	}

	/**
	 * @return the icon
	 */
	public AtomIcon getIcon() {
		return icon;
	}
	
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(AtomIcon icon) {
		this.icon = icon;
	}
	
	/**
	 * @return the logo
	 */
	public AtomLogo getLogo() {
		return logo;
	}
	
	/**
	 * @param logo the logo to set
	 */
	public void setLogo(AtomLogo logo) {
		this.logo = logo;
	}

	public List<AtomLink> getLinks() {
		if (links==null)
			links = new ArrayList<AtomLink>();
		return links;
	}
	
	public void setLinks(List<AtomLink> links) {
		this.links = links;
	}

	public void setSubTitle(AtomPlainTextConstruct subTitle) {
		this.subTitle = subTitle;
	}
	
	public void setSubTitle(AtomXHTMLTextConstruct subTitle) {
		this.subTitle = subTitle;
	}
	
	public AtomTextConstruct getSubTitle() {
		return subTitle;
	}
	
	public void setSubTitle(String subTitle) {
		this.subTitle = new AtomPlainTextConstruct(subTitle);
	}

	
	/// OWC 
	
//  <optional>
//  <ref name="owcDisplay"/>
//</optional>
	@XmlElementRef
	OwcDisplay display;
	
//<element name="dc:publisher">
//  <text/>
//</element>
	@XmlElement(name="publisher", namespace="http://purl.org/dc/elements/1.1/", required=true)
	String publisher;
	
//<optional>
//  <element name="dc:date">
//    <ref name="atomDateConstruct"/>
//  </element>
//</optional>
	@XmlElement(name="date", namespace="http://purl.org/dc/elements/1.1/", required=false)
	AtomDateConstruct date;
	
	@XmlElement(name="where", namespace="http://www.georss.org/georss", required=false)
	GeorssWhere where;

	/**
	 * @param display the display to set
	 */
	public void setDisplay(OwcDisplay display) {
		this.display = display;
	}
	
	/**
	 * @return the display
	 */
	public OwcDisplay getDisplay() {
		return display;
	}
	
	/**
	 * @return the dcPublisher
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * @param dcPublisher the dcPublisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setDate(AtomDateConstruct date) {
		this.date = date;
	}
	
	public void setDate(XMLGregorianCalendar date) {
		this.date = new AtomDateConstruct(date);
	}
	
	public void setDate(String date) throws DatatypeConfigurationException {
		this.date = new AtomDateConstruct(date);
	}

	public AtomDateConstruct getDate() {
		return date;
	}
	
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(AtomDateConstruct updated) {
		this.updated = updated;
	}
	
	public void setUpdated(XMLGregorianCalendar dateTime) {
		this.updated = new AtomDateConstruct(dateTime);
	}
	
	/**
	 * @param string
	 * @throws DatatypeConfigurationException 
	 */
	public void setUpdated(String dateTime) throws DatatypeConfigurationException {
		this.updated = new AtomDateConstruct(dateTime);
	}

	/**
	 * @return the updated
	 */
	public AtomDateConstruct getUpdated() {
		return updated;
	}

	/**
	 * @return the where
	 */
	public GeorssWhere getWhere() {
		return where;
	}
	
	/**
	 * @param where the where to set
	 */
	public void setWhere(GeorssWhere where) {
		this.where = where;
	}
	
	
	public void setWhere(BoundingBox bbox) throws Exception {
		GeorssWhere where = new GeorssWhere();
		GmlPolygonElement polygon = new GmlPolygonElement(new GmlExterior(new GmlLinearRing(bbox.getLinearRingRepresentation())));
		where.setGmlElement(polygon);
		this.where = where;
	}
	
	
	
	/**
	 * @return the xmlLang
	 */
	public String getXmlLang() {
		return xmlLang;
	}
	
	/**
	 * @param xmlLang the xmlLang to set
	 */
	public void setXmlLang(String xmlLang) {
		this.xmlLang = xmlLang;
	}
	
	// facility to add the atom:link with profile rel (mandatory)
	public void setProfile(String title) {
		AtomLink link = new AtomLink();
		link.setRel("profile");
		link.setTitle(title);
		link.setHref(Constants.DEFAULT_ATOM_LINK_PROFILE_HREF);
		this.getLinks().add(link);
	}
	
}
