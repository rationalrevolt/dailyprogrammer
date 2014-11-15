package com.sankar.rdp.RDP188Easy;

enum Month {
    Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec;
    
    public String asMMString() {
        return String.format("%2d", this.ordinal() + 1);
    }
}