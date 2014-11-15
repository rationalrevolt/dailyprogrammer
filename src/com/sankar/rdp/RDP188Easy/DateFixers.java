package com.sankar.rdp.RDP188Easy;


public class DateFixers {
    
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
