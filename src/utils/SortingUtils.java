package utils;

import java.util.*;

public class SortingUtils {
    public static <T> void sort(List<T> list, Comparator<T> comparator){
        list.sort(comparator);
    }
}
