package by.htp.bean;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrarySaxHandler extends DefaultHandler {
	private List<Edition> editionsList = new ArrayList<Edition>();
	private Edition edition;
	private StringBuilder text;

	public List<Edition> getEditionsList() {
		return editionsList;
	}

	public void startDocument() throws SAXException {
		System.out.println("Parsing started.");
	}

	public void endDocument() throws SAXException {
		System.out.println("Parsing ended.");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		System.out.println("startElement -> " + "uri: " + uri + ", localName:"
				+ localName + ", qName: " + qName);
		text = new StringBuilder();
		if (qName.equals("edition")) {
			edition = new Edition();
			edition.setId((Integer.parseInt(attributes.getValue("id"))));
		}
	}

	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		LibraryTagName tagName = LibraryTagName.valueOf(qName.toUpperCase().replace(
				"-", "_"));
		switch (tagName) {
		case TYPE:
			edition.setType(text.toString());
			break;
		case NAME:
			edition.setName(text.toString());
			break;
		case AUTHOR:
			edition.setAuthor(text.toString());
			break;
		case YEAR:
			edition.setYear(Integer.parseInt(text.toString()));
			break;
		case OFFICE:
			edition.setReadingPlace(text.toString());
			break;
		case HOME:
			edition.setReadingPlace(text.toString());
			break;
		case READINGTIMEDAYS:
			edition.setReadingTime(Integer.parseInt(text.toString()));
			break;
		case EDITION:
			editionsList.add(edition);
			edition = null;
			break;
		}
	}

	public void warning(SAXParseException exception) {
		System.err.println("WARNING: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
	}

	public void error(SAXParseException exception) {
		System.err.println("ERROR: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("FATAL: line " + exception.getLineNumber() + ": "
				+ exception.getMessage());
		throw (exception);
	}
}