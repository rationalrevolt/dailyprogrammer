package com.sankar.RDP189Intermediate;


public class RomanConverter {
    
    private enum Roman {
        M(1000),
        D(500),
        C(100),
        L(50),
        X(10),
        V(5),
        I(1);
        
        private Roman(int value) {
            this.value = value;
        }
        
        public int value() {
            return value;
        }
        
        public int minimumValueBySubtraction() {
            return value() - romanToSubtract().value();
        }
        
        public Roman romanToSubtract() {
            switch(this) {
                case V:
                case X: return I;
                case L:
                case C: return X;
                case D:
                case M: return C;
                
                default: throw new AssertionError();
            }
        }
        
        public int maxRepeat() {
            return this == M ? 4 : 3;
        }
        
        public static Roman valueOf(char c) {
            return valueOf(Character.toString(c));
        }
        
        private int value;
    }
    
    static int toDecimal(String inp) {
        return new InternalFromRoman(inp).convert();
    }
    
    static String toRoman(int inp) {
        StringBuilder sb = new StringBuilder();
        
        if (inp >= 5000) {
            int rest = inp % 1000;
            int thousands = (inp - rest) / 1000;
            
            int thousandsTenth = thousands % 10;
            
            if (thousandsTenth > 0 && thousandsTenth < 4) {
                rest += (1000 * thousandsTenth);
                thousands -= thousandsTenth;
            }
            
            else if (thousandsTenth > 5 && thousandsTenth < 9) {
                int toShift = (thousandsTenth - 5);
                rest += (1000 * toShift);
                thousands -= toShift;
            }
            
            sb.append('(').append(toRoman(thousands)).append(')').append(toRomanLessThan5000(rest));
        }
        
        else
            sb.append(toRomanLessThan5000(inp));
        
        return sb.toString();
    }
    
    private static String toRomanLessThan5000(int inp) {
        StringBuilder sb = new StringBuilder();
        
        while(inp > 0) {
            for(Roman r : Roman.values()) {
                // Exact value match
                if (inp == r.value()) {
                    sb.append(r);
                    inp -= r.value();
                    break;
                }
                
                // Check if we need to represent using subtraction
                else if (r.value() > 1 && inp >= r.minimumValueBySubtraction() && inp < r.value()) {
                    sb.append(r.romanToSubtract()).append(r);
                    inp -= r.minimumValueBySubtraction();
                    break;
                }
            
                else {
                    int repeat = 0;
                    while(inp > r.value() && (++repeat <= r.maxRepeat())) {
                        sb.append(r);
                        inp -= r.value();
                    }
                    
                    if (repeat > 0) break;
                }
            }
        }
        
        return sb.toString();
    }
    
    private static class InternalFromRoman {
        
        private String inp;
        
        private int currentIndex = 0;
        
        InternalFromRoman(String inp) {
            this.inp = inp;
        }
        
        int convert() {
            return _convert(0);
        }
        
        private int _convert(int nesting) {
            int result = 0;
            int multipliers = 0;
            
            Roman previous = null;
            
            int repeat = 0;
            
            while(currentIndex < inp.length()) {
                
                char c = inp.charAt(currentIndex);
                
                if (c == '(') {
                    if (multipliers > 1) fail();
                    
                    currentIndex++;
                    result += 1000 * _convert(nesting + 1);
                    multipliers++;
                    continue;
                }
                
                else if (c == ')') {
                    if (nesting == 0) fail();
                    if (result == 0) fail();
                    if (previous == Roman.I) fail();
                    
                    currentIndex++;
                    return result;
                }
                
                Roman current = Roman.valueOf(c);
                
                if (previous == null) {
                    result += current.value();
                    previous = current;
                    repeat = 1;
                    currentIndex++;
                    continue;
                }
                
                if (current == previous && repeat == current.maxRepeat()) 
                    fail();
                
                if (current.value() == previous.value()) {
                    result += current.value();
                    repeat++;
                }
                
                else if (current.value() < previous.value()) {
                    result += current.value();
                    repeat = 1;
                }
                
                else if (repeat > 1) 
                    fail();
                
                else {
                    result += current.value();
                    result -= 2 * previous.value();
                    repeat = 1;
                }
                
                previous = current;
                currentIndex++;
            }
            
            return result;
        }
    }
    
    private static void fail() {
        throw new IllegalArgumentException("Invalid roman value");
    }

}
