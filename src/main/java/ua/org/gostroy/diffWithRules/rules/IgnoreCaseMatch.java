package ua.org.gostroy.diffWithRules.rules;

/**
 * Created by Panov Sergey on 1/17/2015.
 */
public class IgnoreCaseMatch implements CallBackRule {

    public Boolean compareRule(String str1, String str2) {
        return str1.equalsIgnoreCase(str2);
    }

}
