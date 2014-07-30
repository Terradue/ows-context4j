/**
 * 
 */
package com.terradue.owc4j.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import com.terradue.owc4j.beans.atom.AtomCategory;
import com.terradue.owc4j.beans.atom.AtomDateConstruct;
import com.terradue.owc4j.beans.atom.AtomInlineTextContent;
import com.terradue.owc4j.beans.atom.AtomPersonConstruct;
import com.terradue.owc4j.beans.atom.AtomPlainTextConstruct;
import com.terradue.owc4j.beans.atom.AtomContent.Type;
import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcAtomFeed;
import com.terradue.owc4j.beans.owc.OwcDocument;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class Utils {

	public static void checkPattern(String title, String textToCheck, String pattern) throws Exception {
		if (textToCheck==null)
			return;
		if (textToCheck.contentEquals(""))
			return;
		if (textToCheck.matches(pattern))
			return;
		
		throw new Exception(title + "=\"" + textToCheck + "\" should match \"" + pattern + "\" pattern.");
	}
	
	public static String getBaseUrl(String url) throws URISyntaxException {
		URI uri = new URI(url);
		return uri.getScheme() + "://" +  uri.getHost() + uri.getPath();
	}
	
	public static void main(String args[]) {
		String urls[] = {"http://stackoverflow.com/questions/11733500/getting-url-parameter-in-java-and-extract-a-specific-text-from-that-url",
				"http://forum.html.it/forum/forumdisplay.php?s=&forumid=51",
				"https://www.cioa.it?request=getCapabilities"
				};
		for (String url: urls)
			try {
				System.out.println(url + "\n" + getBaseUrl(url)+"--------------------------------\n");
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @param string
	 * @param fees
	 * @param accessConstraints
	 * @return
	 */
	public static String formatStr(String str, Object...values) {
		for (int i=0; i<values.length; i++){
			if (values[i]==null)
				values[i]="";
		}
		return String.format(str, values);
	}
	
	public static OwcDocument getBaseOwcDocument() throws DatatypeConfigurationException{
		return getBaseOwcDocument(null, null, null);
	}

	public static OwcDocument getBaseOwcDocument(String url) throws DatatypeConfigurationException{
		return getBaseOwcDocument(null, null, url);
	}

	public static OwcDocument getBaseOwcDocument(String id, String title, String url) throws DatatypeConfigurationException{
		String briefTitle = "OWS Context Document, made by OWC4J",
				newTitle = title==null ? briefTitle + (url==null ? "" : " from "+url) : title;
				
		OwcDocument owc = new OwcDocument();
		OwcAtomFeed feed = owc.getFeed();
		feed.setId(id==null ? "OSWContext_" + UUID.randomUUID() : id);
		feed.setTitle(newTitle);
		feed.setProfile(briefTitle);
		feed.setDate(new AtomDateConstruct());
		feed.setUpdated(new AtomDateConstruct());
		
		// categories
		List<AtomCategory> categories = feed.getCategories();
		AtomCategory category = new AtomCategory();
		category.setLabel("This file is compliant with version 1.0 of OGC Context");
		category.setScheme("http://www.opengis.net/owc/specReference");
		category.setTerm("http://www.opengis.net/spec/owc/1.0/conf/atom");
		categories.add(category);
		
		// rights
		feed.setRights("Terradue Srl.\nCopyright (c) 2012. Some rights reserved. This feed is licensed under a Creative Commons Attribution 3.0 License.");
		
		return owc;				
	}

	/**
	 * @param id
	 * @param title
	 * @param defaultAuthor
	 * @param publisher
	 * @param content
	 * @param isHtmlContent
	 * @return
	 */
	public static OwcAtomEntry getBaseEntry(String id, String title, AtomPersonConstruct defaultAuthor, String publisher,
			String content, boolean isHtmlContent) {
		
		OwcAtomEntry entry = new OwcAtomEntry();
		entry.setId(id);

		entry.setTitle(title);
		
		// author & publisher
		entry.getAuthors().add(defaultAuthor);
		entry.setPublisher(publisher);
		
		// TODO: set enclosure, icon, via links?
		
		AtomInlineTextContent aitContent = new AtomInlineTextContent();
		aitContent.setType(isHtmlContent ? Type.html : Type.text);
		aitContent.setContent(content);
		entry.setContent(aitContent);
		
		return entry;
	}
	
}
