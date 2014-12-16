package is.mjuk.id1020.utils;

import java.util.List;

public class Swap {
    public static <T> void swap(List<T> li, int a, int b)
    {
        T tmp = li.get(a);
        li.set(a, li.get(b));
        li.set(b, tmp);
    }
}
