package test.converter;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlToJavaObject {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		try {
			//Document document = builder.parse(new File("10.20.161.220_22222_DLMSPush.xml"));
			
			Document document = builder.parse(new File("10.20.205.2_22222_DLMSPush.xml"));
			System.out.println("Root Element :-" + document.getDocumentElement().getNodeName());
			if (document.hasChildNodes()) {
				printNodeList(document.getChildNodes(), document.getDocumentElement().getNodeName());
			}
		} catch (Exception e) {

		}

	}

	private static void printNodeList(NodeList childNodes, String rootName) throws IOException {
		for (int i = 0; i < childNodes.getLength(); i++) {

			Node node = childNodes.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				if (!rootName.equals(node.getNodeName())) {
					System.out.println(" **************  New Node Name Started ****************** " + "\n");
					System.out.println("Node Name:  " + node.getNodeName() + "\n");
					System.out.println("Node Content:  " + node.getTextContent() + "\n");
				}

				if (node.hasAttributes()) {

					NamedNodeMap nodeMap = node.getAttributes();

					for (int j = 0; j < nodeMap.getLength(); j++) {

						Node nodeDown = nodeMap.item(j);
						System.out.println("Attr Name:  " + nodeDown.getNodeName() + "\n");
						System.out.println("Attr Value:  " + nodeDown.getTextContent() + "\n");

					}

				}
				System.out.println(" **************  New Node Name Ended ****************** " + "\n");

				if (node.hasChildNodes()) {

					printNodeList(node.getChildNodes(), rootName);
				}

			}

		}

	}
}