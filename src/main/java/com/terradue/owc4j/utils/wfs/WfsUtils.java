/**
 * 
 */
package com.terradue.owc4j.utils.wfs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.xml.sax.SAXException;

import com.terradue.owc4j.beans.owc.OwcAtomEntry;
import com.terradue.owc4j.beans.owc.OwcDocument;
import com.terradue.owc4j.utils.OwcOptions;


/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class WfsUtils {

	public static String getCapabilitiesVersion(String wfsCapabilitiesUrl) throws SAXException, MalformedURLException, IOException {
		return "1.0.0";
	}

	public static List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String layer, SupportedWfsVersion version, OwcOptions options) throws Exception {
		AbstractWfsCapabilities wfsCapabilities;
		switch (version) {
		case v_1_1_0: wfsCapabilities = new WfsCapabilities_1_1_0(); break;
		default: throw new Exception("Version null or incorrect.");
		}

		return wfsCapabilities.getEntriesFromWfs(wfsCapabilitiesUrl, layer, options);
	}
	public static List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String layer, SupportedWfsVersion version) throws Exception {
		return getEntriesFromWfs(wfsCapabilitiesUrl, layer, new OwcOptions());
	}

	public static List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		String version = getCapabilitiesVersion(wfsCapabilitiesUrl);
		AbstractWfsCapabilities wfsCapabilities = AbstractWfsCapabilities.wfsCapabilitiesFactory(version);
		return wfsCapabilities.getEntriesFromWfs(wfsCapabilitiesUrl, pattern, options);
	}
	public static List<OwcAtomEntry> getEntriesFromWfs(String wfsCapabilitiesUrl, String pattern) throws Exception {
		return getEntriesFromWfs(wfsCapabilitiesUrl, pattern, new OwcOptions());
	}

	
	public static OwcDocument getOwcFromWfs(String wfsCapabilitiesUrl, String pattern, OwcOptions options) throws Exception {
		String version = getCapabilitiesVersion(wfsCapabilitiesUrl);
		AbstractWfsCapabilities wfsCapabilities = AbstractWfsCapabilities.wfsCapabilitiesFactory(version);
		return wfsCapabilities.getOwcFromWfs(wfsCapabilitiesUrl, pattern, options);
	}
	public static OwcDocument getOwcFromWms(String wfsCapabilitiesUrl, String pattern) throws Exception {
		return getOwcFromWfs(wfsCapabilitiesUrl, pattern, new OwcOptions());
	}
}
