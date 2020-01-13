package chapter2.part4;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap {
    public static void main(String[] args) {
        Map hashMap = new HashMap();
        hashMap.put(1, 2);
        hashMap.put(1, 3);
        System.out.println(hashMap.get(1));
    }
}
