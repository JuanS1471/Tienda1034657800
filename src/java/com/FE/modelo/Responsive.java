/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.FE.modelo;


import org.primefaces.util.EscapeUtils;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public class Responsive implements Serializable {

    private static final long serialVersionUID = 1L;

    private String breakpoint;
    private int numVisible;
    private int numScroll;

    public Responsive() {

    }

    public Responsive(String breakpoint, int numVisible) {
        this.breakpoint = breakpoint;
        this.numVisible = numVisible;
    }

    public Responsive(String breakpoint, int numVisible, int numScroll) {
        this(breakpoint, numVisible);
        this.numScroll = numScroll;
    }

    public String getBreakpoint() {
        return breakpoint;
    }

    public void setBreakpoint(String breakpoint) {
        this.breakpoint = breakpoint;
    }

    public int getNumVisible() {
        return numVisible;
    }

    public void setNumVisible(int numVisible) {
        this.numVisible = numVisible;
    }

    public int getNumScroll() {
        return numScroll;
    }

    public void setNumScroll(int numScroll) {
        this.numScroll = numScroll;
    }

    public void encode(Writer writer) throws IOException {
        writer.write("{");
        writer.write("breakpoint:\"" + EscapeUtils.forJavaScript(breakpoint) + "\"");
        writer.write(",numVisible:" + this.numVisible);
        writer.write(",numScroll:" + this.numScroll);
        writer.write("}");
    }
}

