import org.junit.Assert;
import org.junit.Test;

public class RulesEvaluatorTest {
    @Test
    public void readRulesTest() {
        //First change address of file in RulesEvaluator
        Assert.assertNull(RulesEvaluator.getReadRules());
        RulesEvaluator.readRules();
        Assert.assertNotNull(RulesEvaluator.getReadRules());
    }

}
