package com;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.apache.tika.Tika;
import org.apache.tika.mime.MediaType;
import org.apache.tika.mime.MediaTypeRegistry;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.sax.ContentHandlerDecorator;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class SimpleTextExtractor {
	public static void main(String[] args) throws Exception {
		// Create a Tika instance with the default configuration
		Tika tika = new Tika();
		File file=new File("F:\\work\\Myeclips\\TikaTest\\src\\com\\chapter_2.pdf");
		   
		// from this read the file and print it
			String text = tika.parseToString(file);
			//System.out.print(text);
			
			//detect the fle type
			String type1= tika.detect(file);
			System.out.println(type1);
			
			//Here print all metadata
	/*		InputStream is= new BufferedInputStream(new FileInputStream(new File("F:\\work\\Myeclips\\TikaTest\\src\\com\\chapter_2.pdf")));
			Parser parser = new AutoDetectParser();
			BodyContentHandler handler = new BodyContentHandler();// if use the new BodyContentHandler(Sysout.out) then print the all data in text formate

			Metadata metadata = new Metadata();
			ParseContext context = new ParseContext();
			parser.parse(is, handler, metadata,context);

            // Extracting the all metadata from file and print it			
			for (String name : metadata.names()) {
				String value = metadata.get(name);

				if (value != null) {
					System.out.println("Metadata Name: " + name);
					System.out.println("Metadata Value: " + value);
				}
			}*/
			//End print all meta data
			System.out.println("------------------------------------");
			MediaType type = MediaType.parse("text/plain; charset=UTF-8");
			System.out.println("type: " + type.getType());
			System.out.println("subtype: " + type.getSubtype());
			Map<String, String> parameters = type.getParameters();
			System.out.println("parameters:");
			for (String name : parameters.keySet()) {
			System.out.println(" " + name + "=" + parameters.get(name));
			}
			
			MediaTypeRegistry registry = MediaTypeRegistry.getDefaultRegistry();
			for (MediaType type2 : registry.getTypes()) {
			Set<MediaType> aliases = registry.getAliases(type2);
			System.out.println(type2 + ", also known as " + aliases);
			}
			
			ContentHandlerDecorator decorator=new ContentHandlerDecorator(new BodyContentHandler());
			decorator.startDocument();

			org.apache.tika.parser.Parser parser2=new PDFParser();
	}
}

