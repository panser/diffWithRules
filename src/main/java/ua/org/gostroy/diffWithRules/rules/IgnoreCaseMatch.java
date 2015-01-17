package ua.org.gostroy.diffWithRules.rules;

import ua.org.gostroy.diffWithRules.annotation.MatchRule;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
@MatchRule
public class IgnoreCaseMatch {

    public Boolean compareRule(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

}
