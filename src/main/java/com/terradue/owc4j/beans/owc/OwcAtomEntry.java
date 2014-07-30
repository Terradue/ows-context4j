/**
 * 
 */
package com.terradue.owc4j.beans.owc;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Element;

import com.terradue.owc4j.beans.atom.AtomCategory;
import com.terradue.owc4j.beans.atom.AtomCommon;
import com.terradue.owc4j.beans.atom.AtomContent;
import com.terradue.owc4j.beans.atom.AtomDateConstruct;
import com.terradue.owc4j.beans.atom.AtomId;
import com.terradue.owc4j.beans.atom.AtomInlineOtherContent;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomInlineXHTMLContent;
import com.terradue.owc4j.beans.atom.AtomLink;
import com.terradue.owc4j.beans.atom.AtomOutOfLineContent;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.atom.AtomPlainTextConstruct;
import com.terradue.owc4j.beans.atom.AtomSource;
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
@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(propOrder={"id","title","categories","authors","contributors","publisher","updated","date","where","published",
		"minScaleDenominator","maxScaleDenominator","links","rights","source","summary","content","offering","extensionElements"})
public class OwcAtomEntry extends AtomCommon {
	

//  <ref name="atomId"/>
	@XmlElement(required=true)
	private AtomId id;

//  <ref name="atomTitle"/>
	@XmlElements(value = { 
	        @XmlElement(name="title", type=AtomPlainTextConstruct.class, required=true),
	        @XmlElement(name="title", type=AtomXHTMLTextConstruct.class, required=true),
	})
	private AtomTextConstruct title;

//      <zeroOrMore>
//        <ref name="atomAuthor"/>
//      </zeroOrMore>
	@XmlElement(name="author")
	private List<AtomPersonConstruct> authors;
	
//      <zeroOrMore>
//        <ref name="atomCategory"/>
//      </zeroOrMore>
	@XmlElementRef
	private List<AtomCategory> categories;
	
//      <optional>
//        <ref name="atomContent"/>
//      </optional>
	@XmlElements(value = { 
	        @XmlElement(name="content", type=AtomOutOfLineContent.class, required=false),
	        @XmlElement(name="content", type=AtomInlineXHTMLContent.class, required=false),
	        @XmlElement(name="content", type=AtomInlineOtherContent.class, required=false),
	        @XmlElement(name="content", type=AtomInlineTextContent.class, required=false),
	})
	private AtomContent content;
	
//      <zeroOrMore>
//        <ref name="atomContributor"/>
//      </zeroOrMore>
	@XmlElement(name="contributor")
	private List<AtomPersonConstruct> contributors;
	
//  <ref name="atomUpdated"/>
	@XmlElement
	AtomDateConstruct updated;

	@XmlElement(name="where", namespace="http://www.georss.org/georss", required=false)
	GeorssWhere where;

//      <zeroOrMore>
//        <ref name="atomLink"/>
//  	</zeroOrMore>
	@XmlElementRef
	private List<AtomLink> links;
	
//      <optional>
//        <ref name="atomPublished"/>
// 		</optional>
	@XmlElement
	private AtomDateConstruct published;
	
//      <optional>
//        <ref name="atomRights"/>
//  	</optional>
	@XmlElements(value = { 
	        @XmlElement(name="rights", type=AtomPlainTextConstruct.class, required=false),
	        @XmlElement(name="rights", type=AtomXHTMLTextConstruct.class, required=false),
	})
	private AtomTextConstruct rights;
	
//      <optional>
//        <ref name="atomSource"/>
//  	</optional>
	@XmlElement(name="source", required=false)
	private AtomSource source;
	
//      <optional>
//        <ref name="atomSummary"/>
//  	</optional>
	@XmlElements(value = { 
	        @XmlElement(name="summary", type=AtomPlainTextConstruct.class, required=true),
	        @XmlElement(name="summary", type=AtomXHTMLTextConstruct.class, required=true),
	})
	private AtomTextConstruct summary;


//      <zeroOrMore>
//        <ref name="extensionElement"/>
//      </zeroOrMore>
	@XmlAnyElement
	private List<Element> extensionElements;
	

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
	 * @param atomContributors the atomContributors to set
	 */
	public void setContributors(List<AtomPersonConstruct> contributors) {
		this.contributors = contributors;
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


	public void setTitle(AtomPlainTextConstruct title) {
		this.title = title;
	}
	
	public void setTitle(AtomXHTMLTextConstruct title) {
		this.title = title;
	}
	
	public AtomTextConstruct getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = new AtomPlainTextConstruct(title);
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
	
	public List<AtomLink> getLinks() {
		if (links==null)
			links = new ArrayList<AtomLink>();
		return links;
	}
	
	public void setLinks(List<AtomLink> links) {
		this.links = links;
	}


	public void setContent(AtomInlineTextContent content) {
		this.content = content;
	}

	public void setContent(AtomInlineXHTMLContent content) {
		this.content = content;
	}

	public void setContent(AtomInlineOtherContent content) {
		this.content = content;
	}

	public void setContent(AtomOutOfLineContent content) {
		this.content = content;
	}
	
	/**
	 * @return the content
	 */
	public AtomContent getContent() {
		return content;
	}
	
	public void setPublished(AtomDateConstruct published) {
		this.published = published;
	}
	
	public void setPublished(XMLGregorianCalendar dateTime) {
		this.published = new AtomDateConstruct(dateTime);
	}
	
	public void setPublished(String dateTime) throws DatatypeConfigurationException {
		this.published = new AtomDateConstruct(dateTime);
	}

	public AtomDateConstruct getPublished() {
		return published;
	}

	
	public void setSummary(AtomPlainTextConstruct summary) {
		this.summary = summary;
	}
	
	public void setSummary(AtomXHTMLTextConstruct summary) {
		this.summary = summary;
	}
	
	public AtomTextConstruct getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = new AtomPlainTextConstruct(summary);
	}

	/**
	 * @return the extensionElements
	 */
	public List<Element> getExtensionElements() {
		if (extensionElements==null)
			extensionElements = new ArrayList<Element>();
		return extensionElements;
	}
	
	/**
	 * @param extensionElements the extensionElements to set
	 */
	public void setExtensionElements(List<Element> extensionElements) {
		this.extensionElements = extensionElements;
	}
	
	/**
	 * @param source the source to set
	 */
	public void setSource(AtomSource source) {
		this.source = source;
	}
	
	/**
	 * @return the source
	 */
	public AtomSource getSource() {
		return source;
	}
	
	
	
	//// OWC
	
	@XmlElementRef
	OwcOffering offering;
	
	@XmlElement(name="publisher", namespace="http://purl.org/dc/elements/1.1/", required=false)
	String publisher;

	@XmlElement(name="date", namespace="http://purl.org/dc/elements/1.1/", required=false)
	AtomDateConstruct date;
	
	@XmlElement(name="minScaleDenominator", namespace="http://www.opengis.net/owc/1.0", required=false)
	Float minScaleDenominator;
	
	@XmlElement(name="maxScaleDenominator", namespace="http://www.opengis.net/owc/1.0", required=false)
	Float maxScaleDenominator;
	
	/**
	 * @return the offering
	 */
	public OwcOffering getOffering() {
		return offering;
	}
	
	/**
	 * @param offering the offering to set
	 */
	public void setOffering(OwcOffering offering) {
		this.offering = offering;
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
	 * @return the minScaleDenominator
	 */
	public float getMinScaleDenominator() {
		return minScaleDenominator;
	}
	
	/**
	 * @param minScaleDenominator the minScaleDenominator to set
	 */
	public void setMinScaleDenominator(float minScaleDenominator) {
		this.minScaleDenominator = minScaleDenominator;
	}
	
	/**
	 * @return the maxScaleDenominator
	 */
	public float getMaxScaleDenominator() {
		return maxScaleDenominator;
	}
	
	/**
	 * @param maxScaleDenominator the maxScaleDenominator to set
	 */
	public void setMaxScaleDenominator(float maxScaleDenominator) {
		this.maxScaleDenominator = maxScaleDenominator;
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

}
