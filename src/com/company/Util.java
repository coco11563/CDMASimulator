package com.company;

public class Util {

    public static Integer[] toIntArr(String s) {
        String[] sl = s.split(",");
        Integer[] ret = new Integer[sl.length];
        int index = 0;
        for (String str : sl) {
            ret[index] = Integer.valueOf(str);
            index ++;
        }
        return ret;
    }
}
