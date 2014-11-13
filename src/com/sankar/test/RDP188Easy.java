package com.sankar.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RDP188Easy {
    
    enum Month {
        Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
        
        public String asMMString() {
            return String.format("%2d", this.ordinal() + 1);
        }
    }
    
    static abstract class DateFixer {
        
        public abstract void fix(String inp, ResultHolder<String> result, DateFixerChain ch);
        
        protected String isoDate(String year, String month, String day) {
            return String.format("%s-%s-%s", year, month, day);
        }
        
        protected String formatYear(String year) {
            if (year.length() == 4)
                return year;
            else
                return (Integer.valueOf(year) < 50 ? "20" : "19") + year;
        }
        
    }
    
    static interface DateFixerChain {
        public void pass(String inp, ResultHolder<String> result);
    }
    
    static class ResultHolder<T> {
        private T value;
        void set(T value) {this.value = value;}
        T get() {return value;}
    }
    
    static class ProductionDateFixer {
        
        private List<DateFixer> list = new ArrayList<>();
        
        public void add(DateFixer... fixers) {
            list.addAll(Arrays.asList(fixers));
        }
        
        public String fix(String inp) {
            ResultHolder<String> dateHolder = new ResultHolder<>();
            
            DateFixerChain fc = new DateFixerChain() {
                int indx;
                
                @Override
                public void pass(String inp, ResultHolder<String> result) {
                    list.get(indx++).fix(inp, result, this);
                }
            };
            
            fc.pass(inp, dateHolder);
            
            return dateHolder.get();
        }
        
    }
    
    static class F1 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
            if (inp.indexOf('-') < 0)
                dfc.pass(inp, result);
            else
                result.set(inp);
        }
        
    }
    
    static class F2 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
            if (inp.indexOf('/') < 0)
                dfc.pass(inp, result);
            else
                result.set(isoDate(formatYear(inp.substring(6)), inp.substring(0,2), inp.substring(3,5)));
        }
        
    }
    
    static class F3 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
            if (inp.indexOf('#') < 0)
                dfc.pass(inp, result);
            else
                result.set(isoDate(formatYear(inp.substring(3,5)), inp.substring(0,2), inp.substring(6)));
        }
        
    }
    
    static class F4 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
            if (inp.indexOf('*') < 0)
                dfc.pass(inp, result);
            else
                result.set(isoDate(formatYear(inp.substring(6)), inp.substring(3,5), inp.substring(0,2)));
        }
        
    }
    
    static class F5 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
            // (Jan) 01, 19
            if (inp.indexOf(',') < 0 || inp.length() != 12)
                dfc.pass(inp, result);
            else
                result.set(isoDate(formatYear(inp.substring(10)), Month.valueOf(inp.substring(1,4)).asMMString(), inp.substring(6,8)));
        }
        
    }
    
    static class F6 extends DateFixer {

        @Override
        public void fix(String inp, ResultHolder<String> result, DateFixerChain dfc) {
         // (Jan) 01, 2012
            if (inp.indexOf(',') < 0 || inp.length() != 14)
                dfc.pass(inp, result);
            else
                result.set(isoDate(formatYear(inp.substring(10)), Month.valueOf(inp.substring(1,4)).asMMString(), inp.substring(6,8)));
        }
        
    }

}
