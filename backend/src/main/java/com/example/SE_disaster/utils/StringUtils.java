package com.example.SE_disaster.utils;

import java.util.ArrayList;
import java.util.Comparator;

public class StringUtils {
    public static String[] multiplePositionSplit(String str, ArrayList<Integer> pos) {
        if (pos.size() == 0)
            return new String[] { str };
        pos.sort(Comparator.naturalOrder());
        int last = 0;
        int len = str.length();
        var list = new ArrayList<String>();
        for (var p : pos) {
            var pp = Math.max(last, Math.min(p, len));
            list.add(str.substring(last, pp));
        }
    }
}
