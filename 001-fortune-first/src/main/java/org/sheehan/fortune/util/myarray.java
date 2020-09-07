package org.sheehan.fortune.util;

public class myarray {
    public static String[] reverse(String[] a) {
        String[] b = a;
        for(int start=0,end=b.length-1;start<end;start++,end--) {
            String temp = b[start];
            b[start] = b[end];
            b[end] = temp;
        }
        return b;
    }
}
