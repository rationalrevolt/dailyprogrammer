package com.sankar.rdp.RDP189Intermediate;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sankar.rdp.RDP189Intermediate.RomanConverter;

@RunWith(Parameterized.class)
public class RomanConverterTest {
    
    private String roman;
    private int decimal;
    
    public RomanConverterTest(String roman, int decimal) {
        this.roman = roman;
        this.decimal = decimal;
    }
    
    @Test public void
    converts_roman_to_decimal_correctly() {
        assertThat(String.format("Given %s", roman), decimal, is(RomanConverter.toDecimal(roman)));
    }
    
    @Test public void
    converts_decimal_to_roman_correctly() {
        assertThat(String.format("Given %d", decimal), RomanConverter.toRoman(decimal), is(roman));
    }
    
    @Test public void
    self_test_decimal_to_roman_to_decimal() {
        assertThat(String.format("Given %d", decimal), RomanConverter.toDecimal(RomanConverter.toRoman(decimal)), is(decimal));
    }
    
    @Parameters
    public static Collection<Object[]> tests() {
        return Arrays.asList(
                test("IV", 4),
                test("XXXIV", 34),
                test("CCLXVII", 267),
                test("DCCLXIV", 764),
                test("CMLXXXVII", 987),
                test("MCMLXXXIII", 1983),
                test("MMXIV", 2014),
                test("MMMM", 4000),
                test("MMMMCMXCIX", 4999),
                test("(V)", 5000),
                test("(V)CDLXXVIII", 5478),
                test("(V)M", 6000),
                test("(IX)", 9000),
                test("(X)M", 11000),
                test("(X)MM", 12000),
                test("(X)MMCCCXLV", 12345),
                test("(CCCX)MMMMCLIX", 314159),
                test("(DLXXV)MMMCCLXVII", 578267),
                test("(MMMCCXV)CDLXVIII", 3215468),
                test("(MMMMCCX)MMMMCDLXVIII", 4214468),
                test("(MMMMCCXV)CDLXVIII", 4215468),
                test("(MMMMCCXV)MMMCDLXVIII", 4218468),
                test("(MMMMCCXIX)CDLXVIII", 4219468),
                test("((XV)MDCCLXXV)MMCCXVI", 16777216),
                test("((CCCX)MMMMCLIX)CCLXV", 314159265),
                test("((MLXX)MMMDCCXL)MDCCCXXIV", 1073741824)
        );
    }
    
    private static Object[] test(String roman, int decimal) {
        return new Object[] {roman, decimal};
    }

}
