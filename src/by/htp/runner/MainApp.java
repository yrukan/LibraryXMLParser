package by.htp.runner;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.bean.Edition;
import by.htp.bean.LibraryDOMBuilder;
import by.htp.bean.LibrarySaxHandler;

public class MainApp {

	public static void main(String[] args) throws SAXException, IOException {
		/*// // DOM parser
		LibraryDOMBuilder domBuilder = new LibraryDOMBuilder();
		domBuilder.buildSetLibrary("resources/Library.xml");
		for (Edition e : domBuilder.getLibrary())
			System.out.println(e);*/
		
		XMLReader reader = XMLReaderFactory.createXMLReader();
		LibrarySaxHandler handler = new LibrarySaxHandler();
		reader.setContentHandler(handler);
		reader.parse(new InputSource("resources/Library.xml"));

		List<Edition> library = handler.getEditionsList();

		for (Edition edition : library) {
			System.out.println(edition.toString());
		}
	}

}
