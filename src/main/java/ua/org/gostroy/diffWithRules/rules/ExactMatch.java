package ua.org.gostroy.diffWithRules.rules;

import ua.org.gostroy.diffWithRules.annotation.MatchRule;

/**
 * Created by Panov Sergey on 1/16/2015.
 */
@MatchRule
public class ExactMatch {

    public Boolean compareRule(String str1, String str2) {
        return str1.equals(str2);
    }
}
