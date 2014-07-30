/**
 * 
 */
package com.terradue.owc4j.beans.atom;

import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * @author "Francesco Cerasuolo (francesco.cerasuolo@terradue.com)"
 *
 */
public class AtomDateConstruct extends AtomCommon {

	@XmlValue
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar dateTime;
	
	/**
	 * @throws DatatypeConfigurationException 
	 * 
	 */
	public AtomDateConstruct() throws DatatypeConfigurationException {
		super();
		this.setDateTime(new GregorianCalendar());
	}
	
	public AtomDateConstruct(XMLGregorianCalendar dateTime) {
		this.setDateTime(dateTime);
	}
	
	public AtomDateConstruct(String dateTime) throws DatatypeConfigurationException {
		this.setDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar("2013-03-21T14:48:26Z"));
	}
	
	

	/**
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(XMLGregorianCalendar dateTime) {
		this.dateTime = dateTime;
	}
	
	/**
	 * @param dateTime the dateTime to set
	 * @throws DatatypeConfigurationException 
	 */
	public void setDateTime(GregorianCalendar cal) throws DatatypeConfigurationException {
		setDateTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
	}
	
	/**
	 * @return the dateTime
	 */
	public XMLGregorianCalendar getDateTime() {
		return dateTime;
	}
	
}
