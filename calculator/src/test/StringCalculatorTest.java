package test;


import org.junit.Assert;
import org.junit.Test;

import calculator.StringCalculator;

public class StringCalculatorTest {
    private StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyStringReturnsZero() {
        assertResult(0, "");
    }

    @Test
    public void singleNumberReturnsValue() {
        assertResult(1, "1");
    }

    @Test
    public void twoNumbersCommaDelimitedReturnsSum() {
        assertResult(3, "1,2");
    }

    @Test
    public void twoNumbersNewlineDelimitedReturnsSum() {
        assertResult(3, "1\n2");
    }

    @Test
    public void threeNumbersDelimitedEitherWayReturnsSum() {
        assertResult(6, "1,2,3");
        assertResult(6, "1\n2\n3");
        assertResult(6, "1\n2,3");
    }

    @Test(expectedExceptions = UnsupportedOperationException.class)
    public void negativeNumbersThrowException() {
        calculator.add("-1");
    }

    @Test
    public void silentlyIgnoreNumbersGreaterThanThousand() {
        assertResult(1000, "1000");
        assertResult(1001, "1000,1");
        assertResult(2, "1001,2");
    }

    @Test
    public void singleCharDelimiterCanBeDefined() {
        assertResult(3, "//#\n1#2");
    }

    @Test
    public void delimiterCanBeAnyLength() {
        assertResult(3, "//[***]\n1***2");
    }

    @Test
    public void manyMulticharDelimitersCanBeDefined() {
        assertResult(6, "//[##][***]\n1##2***3");
    }

    private void assertResult(int expected, String value) {
    	Assert.assertEquals(expected, calculator.add(value));
    }
}

