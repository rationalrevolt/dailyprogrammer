package com.sankar.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sankar.test.RDP188Easy.F1;
import com.sankar.test.RDP188Easy.F2;
import com.sankar.test.RDP188Easy.F3;
import com.sankar.test.RDP188Easy.F4;
import com.sankar.test.RDP188Easy.F5;
import com.sankar.test.RDP188Easy.F6;
import com.sankar.test.RDP188Easy.ProductionDateFixer;

@RunWith(Parameterized.class)
public class RDP188EasyTest {
    
    private ProductionDateFixer fixer;
    
    private String input;
    private String expected;
    
    public RDP188EasyTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }
    
    @Before public void
    before_each_test() {
        fixer = new ProductionDateFixer();
        fixer.add(new F1(), new F2(), new F3(), new F4(), new F5(), new F6());
    }
    
    @Parameters
    public static Collection<String[]> dates() {
        return Arrays.asList(tc("2014-11-12"), tc("11/12/14"), tc("11#14#12"), tc("12*11*2014"), tc("(Nov) 12, 14"), tc("(Nov) 12, 2014"));
    }

    @Test public void
    fixes_date() {
        assertEquals(expected, fixer.fix(input));
    }
    
    private static String[] tc(String inp) {
        return new String[]{inp, "2014-11-12"};
    }

}
