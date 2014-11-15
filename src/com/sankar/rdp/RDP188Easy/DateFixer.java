package com.sankar.rdp.RDP188Easy;


abstract class DateFixer {
    
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