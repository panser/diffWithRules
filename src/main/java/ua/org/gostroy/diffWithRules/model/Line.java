package ua.org.gostroy.diffWithRules.model;

import ua.org.gostroy.diffWithRules.rules.CallBackRule;

import java.util.List;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
public interface Line {
    CompareResult compareWithRules(Line line, List<CallBackRule> callBackRules);
}
