package org.rsm.foobar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FooBarUtilTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final String invalidInputError = "[Error] Invalid input.";

    @BeforeEach
    public void setUp() {
        //reassign the standard output stream to a new PrintStream with a ByteArrayOutputStream
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        // restore to original state after test completes
        System.setOut(standardOut);
    }

    @Test
    void verifyFooBarConversionsWithEmptyList() {
        FooBarUtil.FooBar(",,,");
        assertEquals("", outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: \",,,\" ");
    }

    @Test
    void verifyFooBarConversionsWithStringEndingWithComma() {
        FooBarUtil.FooBar("1,2,");
        assertEquals("1,2", outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: \"1,2,\" ");
    }


    @ParameterizedTest
    @CsvSource(value = {
            "0:0",
            "-0:0",
            "+0:0",
            "0,0,0:0,0-copy,0-copy",
    }, delimiter = ':')
    void verifyFooBarConversionsWithZero(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+1,-1:1,-1",
            "1,2,4:1,2,4",
            "-1,-2,-4:-1,-2,-4",
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersNotDivisibleBy3or5(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,2,4,1,2,4:1,2,4,1-copy,2-copy,4-copy",
            "+1,+1,+1:1,1-copy,1-copy",
            "-1,-1,-1:-1,-1-copy,-1-copy",
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersNotDivisibleBy3or5WhenOccurringMoreThanOnce(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+3,-3:foo,foo",
            "3,6,9:foo,foo,foo",
            "-3,-6,-9:foo,foo,foo"
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy3(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+3,+3,+3:foo,foo-copy,foo-copy",
            "-3,-3,-3:foo,foo-copy,foo-copy",
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy3WhenOccurringMoreThanOnce(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+5,-5:bar,bar",
            "5,10,20:bar,bar,bar",
            "-5,-10,-20:bar,bar,bar"
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy5(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+5,+5,+5:bar,bar-copy,bar-copy",
            "-5,-5,-5:bar,bar-copy,bar-copy",
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy5WhenOccurringMoreThanOnce(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+15,-15:foobar,foobar",
            "15,30,45:foobar,foobar,foobar",
            "-15,-30,-45:foobar,foobar,foobar"
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy3and5(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "+15,+15,+15:foobar,foobar-copy,foobar-copy",
            "-15,-15,-15:foobar,foobar-copy,foobar-copy",
    }, delimiter = ':')
    void verifyFooBarConversionsWithNumbersDivisibleBy3and5WhenOccurringMoreThanOnce(String input, String expected) {
        FooBarUtil.FooBar(input);
        assertEquals(expected, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @CsvSource(value = {
            Integer.MAX_VALUE + ":" + Integer.MAX_VALUE,
            Integer.MIN_VALUE + ":" + Integer.MIN_VALUE,
            "2147483648:" + invalidInputError,
            "–2147483649:" + invalidInputError,

    }, delimiter = ':')
    void verifyIntegerBoundaryValues(String input, String output) {
        FooBarUtil.FooBar(input);
        assertEquals(output, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "  ",
            "1.3,2.3,4.3",
            "-1.3,-2.3,-4.3",
            "1 ,2",
            ",1,2",
            "1,,2",
            "a,b,v",
    })
    void verifyFooBarPrintsErrorMessageWhenInputIsInvalid(String input) {
        FooBarUtil.FooBar(input);
        assertEquals(invalidInputError, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input: " + input);
    }

    @ParameterizedTest
    @NullSource
    void verifyFooBarPrintsErrorMessageWhenInputIsNull(String input) {
        FooBarUtil.FooBar(input);
        assertEquals( invalidInputError, outputStreamCaptor.toString().trim(),
                "Expected message on console did not match for input null.");
    }


}