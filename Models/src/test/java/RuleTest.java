import org.junit.Assert;
import org.junit.Test;

public class RuleTest {

    @Test
    public void evaluateTest() {
        Rule testRule = new Rule();
        testRule.setOperator(Rule.Operator.LESS);
        testRule.setValue(2.0);
        Assert.assertFalse(testRule.evaluate(3.0));
        testRule.setValue(3.1);
        Assert.assertTrue(testRule.evaluate(3.0));

        testRule.setOperator(Rule.Operator.EQUAL);
        testRule.setValue(3.0);
        Assert.assertTrue(testRule.evaluate(3.0));
        testRule.setValue(3.001);
        Assert.assertFalse(testRule.evaluate(3.0));

        testRule.setOperator(Rule.Operator.MORE);
        Assert.assertTrue(testRule.evaluate(3.1));
    }
}
