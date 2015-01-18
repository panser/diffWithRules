package ua.org.gostroy.diffWithRules.model;

import ua.org.gostroy.diffWithRules.exception.ParseLineException;
import ua.org.gostroy.diffWithRules.handler.JsonSimpleHandler;
import ua.org.gostroy.diffWithRules.handler.PropertyArrayHandler;
import ua.org.gostroy.diffWithRules.handler.XmlNodeHandler;
import ua.org.gostroy.diffWithRules.model.type.NotFormatedLine;

import java.io.*;
import java.util.LinkedList;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public class CompareFile {
    private LinkedList<Line> lines = new LinkedList<>();

    public LinkedList<Line> getLines() {
        return lines;
    }

    public void deserialize(File file) throws FileNotFoundException, IOException {

        BufferedReader bufferFile = new BufferedReader(new FileReader(file));

        while (bufferFile.ready()) {
            String line = bufferFile.readLine();
            line.trim();

            // parse XML
            try {
//                Line parseLine = XmlNodeHandler.parseLine(line);
                Line parseLine = XmlNodeHandler.parseLine(line);
                lines.add(parseLine);
                continue;
            } catch (ParseLineException e) {
            }

            // parse array of key=value
            try {
                Line parseLine = PropertyArrayHandler.parseLine(line);
                lines.add(parseLine);
                continue;
            } catch (Exception e) {
            }

            // parse simple JSON
            try {
                Line parseLine = JsonSimpleHandler.parseLine(line);
                lines.add(parseLine);
                continue;
            } catch (Exception e) {
            }

            //if line is not correct XML or prop=value line
            NotFormatedLine notFormatedLine = new NotFormatedLine(line);
            lines.add(notFormatedLine);
        }
    }

}
