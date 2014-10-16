package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.digester.Digester;
import org.w3c.dom.Document;
public class DigesterXMLDocument {
	private Digester dig;
	private static Document doc;
	public DigesterXMLDocument() {
	dig = new Digester();
	dig.setValidating(false);
	dig.addObjectCreate("address-book", DigesterXMLDocument.class);
	dig.addObjectCreate("address-book/contact",Contact.class);
	dig.addSetProperties("address-book/contact","type", "type");
	dig.addCallMethod("address-book/contact/name","setName", 0);
	dig.addCallMethod("address-book/contact/address","setAddress", 0);
	dig.addCallMethod("address-book/contact/city","setCity", 0);
	dig.addCallMethod("address-book/contact/province","setProvince", 0);
	dig.addCallMethod("address-book/contact/postalcode","setPostalcode", 0);
	dig.addCallMethod("address-book/contact/country",	"setCountry", 0);
	dig.addCallMethod("address-book/contact/telephone",	"setTelephone", 0);
	}
	public synchronized Document getDocument(InputStream is)throws Exception {
	try {
		dig.parse(is);
	}
	catch (IOException e) {
		throw new Exception("Cannot parse XML document", e);
	}
		return doc;
	}
public static void main(String[] args) throws Exception {
		DigesterXMLDocument handler = new DigesterXMLDocument();
		Document doc = handler.getDocument(new FileInputStream(new File("F:\\work\\Myeclips\\TikaTest\\src\\com\\test.xml")));
		System.out.println(doc);
		}
}