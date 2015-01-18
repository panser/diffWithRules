package ua.org.gostroy.diffWithRules.handler;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;
import ua.org.gostroy.diffWithRules.exception.ParseLineException;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.model.type.XmlNodeLine;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
public class XmlNodeHandler {

    public static Line parseLine(String line) throws ParseLineException {

        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new InputSource(new StringReader(line)));

            Element root = doc.getRootElement();
            List<Attribute> attributeList = root.getAttributes();

            Map<String, String> attributes = new HashMap<>();
            for (Attribute attribute : attributeList) {
                attributes.put(attribute.getName(), attribute.getValue());
            }

            Line parseLine = new XmlNodeLine(root.getName(), attributes, root.getText());
            return parseLine;

        } catch (IOException | JDOMException e) {
            throw new ParseLineException(e);
        }

    }
}
