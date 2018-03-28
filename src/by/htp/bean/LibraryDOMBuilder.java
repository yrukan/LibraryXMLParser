package by.htp.bean;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LibraryDOMBuilder {
	private Set<Edition> library;
	private DocumentBuilder docBuilder;

	public LibraryDOMBuilder() {
		this.library = new HashSet<Edition>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.out.println("Ошибка конфигурации парсера: " + e);
		}
	}

	public Set<Edition> getLibrary() {
		return library;
	}

	public void buildSetLibrary(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();

			NodeList editionsList = root.getElementsByTagName("edition");
			for (int i = 0; i < editionsList.getLength(); i++) {
				Element editionElement = (Element) editionsList.item(i);
				Edition edition = buildEdition(editionElement);
				library.add(edition);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private Edition buildEdition(Element editionElement) {

		Edition edition = new Edition();
		Integer id = Integer.parseInt(editionElement.getAttribute("id"));
		edition.setId(id);
		edition.setType(getElementTextContent(editionElement, "type"));
		edition.setName(getElementTextContent(editionElement, "name"));
		edition.setAuthor(getElementTextContent(editionElement, "author"));
		Integer year = Integer.parseInt(getElementTextContent(editionElement, "year"));
		edition.setYear(year);
		
		//Integer readingTime = Integer.parseInt(getElementTextContent(editionElement, "readingTimeDays"));
		//edition.setReadingTime(readingTime);
		
		/*if (getElementNodeName(editionElement, "office") != null)
		edition.setReadingPlace(getElementNodeName(editionElement, "office"));
		else 
			edition.setReadingPlace(getElementNodeName(editionElement, "home"));*/		
		

		return edition;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();		
		return text;
	}
	
	private static String getElementNodeName(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getNodeName();		
		return text;
	}

}
