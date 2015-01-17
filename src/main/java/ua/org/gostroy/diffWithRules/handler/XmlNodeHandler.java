package ua.org.gostroy.diffWithRules.handler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ua.org.gostroy.diffWithRules.exception.ParseLineException;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.model.type.XmlNodeLine;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
public class XmlNodeHandler {

    public static Line parseLine(String line) throws ParseLineException{

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(line)));
            document.getDocumentElement().normalize();
            Element element = document.getDocumentElement();

            NamedNodeMap namedNodeMap = element.getAttributes();
            Map<String, String> attributes = new HashMap<>();
            for (int i = 0; i < namedNodeMap.getLength(); i++) {
                Node node = namedNodeMap.item(i);
                attributes.put(node.getNodeName(), node.getNodeValue());
            }

            Line parseLine = new XmlNodeLine(element.getTagName(), attributes, element.getTextContent());
            return parseLine;

        }catch (IOException|ParserConfigurationException|SAXException e) {
            throw new ParseLineException(e);
        }

    }
}
