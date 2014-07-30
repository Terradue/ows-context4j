/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Element;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class AtomSource extends AtomCommon {

	@XmlElement(name="author")
	private List<AtomPersonConstruct> authors;
	
	@XmlElementRef
	private List<AtomCategory> categories;
	
	@XmlElement(name="contributor")
	private List<AtomPersonConstruct> contributors;
	
	@XmlElement(required=false)
	AtomGenerator generator;
	
	@XmlElementRef
	AtomIcon icon;
	
	@XmlElement(required=true)
	private AtomId id;
	
	@XmlElementRef
	private List<AtomLink> links;
	
	@XmlElementRef
	AtomLogo logo;
	
	@XmlElements(value = { 
	        @XmlElement(name="rights", type=AtomPlainTextConstruct.class, required=false),
	        @XmlElement(name="rights", type=AtomXHTMLTextConstruct.class, required=false),
	})
	private AtomTextConstruct rights;
	
	@XmlElements(value = { 
			@XmlElement(name="subtitle", type=AtomPlainTextConstruct.class, required=false),
			@XmlElement(name="subtitle", type=AtomXHTMLTextConstruct.class, required=false),
	})
    AtomTextConstruct subTitle;
	
	@XmlElements(value = { 
	        @XmlElement(name="title", type=AtomPlainTextConstruct.class, required=true),
	        @XmlElement(name="title", type=AtomXHTMLTextConstruct.class, required=true),
	})
	private AtomTextConstruct title;
	
	@XmlElement
	AtomDateConstruct updated;
	
	@XmlAnyElement
	private List<Element> extentionElements;

	
	
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

	public List<AtomLink> getLinks() {
		if (links==null)
			links = new ArrayList<AtomLink>();
		return links;
	}
	
	public void setLinks(List<AtomLink> links) {
		this.links = links;
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

}
