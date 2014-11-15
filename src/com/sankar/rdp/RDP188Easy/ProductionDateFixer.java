package com.sankar.rdp.RDP188Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ProductionDateFixer {
    
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