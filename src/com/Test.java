package com;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.xml.sax.SAXException;

import thredds.wcs.v1_0_0_1.GetCapabilities.ResponsibleParty.ContactInfo;

public class Test {
public static void main(String[] args) {
	Tika tika=new Tika();
	InputStream is = null;

	try {
		is = new BufferedInputStream(new FileInputStream(new File("F:\\work\\Myeclips\\TikaTest\\src\\com\\Why.html"))); //new FileInputStream(new File("F:\\work\\Myeclips\\TikaTest\\src\\com\\test.xml"))
		Parser parser = new AutoDetectParser();
		BodyContentHandler handler = new BodyContentHandler(System.out);

		Metadata metadata = new Metadata();
		ParseContext context = new ParseContext();
		parser.parse(is, handler, metadata,context);

		for (String name : metadata.names()) {
			String value = metadata.get(name);

			if (value != null) {
				System.out.println("Metadata Name: " + name);
				System.out.println("Metadata Value: " + value);
			}
		}
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (TikaException e) {
		e.printStackTrace();
	} catch (SAXException e) {
		e.printStackTrace();
	} finally {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

}
