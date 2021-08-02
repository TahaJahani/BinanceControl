import org.junit.Assert;
import org.junit.Test;

public class RulesEvaluatorTest {
    @Test
    public void readRulesTest() {
        Assert.assertNull(RulesEvaluator.getReadRules());
        RulesEvaluator.readRules();
        Assert.assertNotNull(RulesEvaluator.getReadRules());
    }

}
