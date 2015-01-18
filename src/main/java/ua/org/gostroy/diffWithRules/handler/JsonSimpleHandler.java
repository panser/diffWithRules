package ua.org.gostroy.diffWithRules.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.org.gostroy.diffWithRules.exception.ParseLineException;
import ua.org.gostroy.diffWithRules.model.Line;
import ua.org.gostroy.diffWithRules.model.type.JsonSimpleLine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Panov Sergey on 1/18/2015.
 */
public class JsonSimpleHandler {

    public static Line parseLine(String line) throws ParseLineException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(line);

            Map<String, String> values = new HashMap<>();
            Iterator<Map.Entry<String, JsonNode>> itr = root.fields();
            while (itr.hasNext()){
                Map.Entry<String, JsonNode> entry = itr.next();
                values.put(entry.getKey(), entry.getValue().asText());
            }

            Line parseLine = new JsonSimpleLine(values);
            return parseLine;

        } catch (Exception e) {
            throw new ParseLineException(e);
        }

    }

}
