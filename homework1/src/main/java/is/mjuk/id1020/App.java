package is.mjuk.id1020;

// import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.introcs.StdOut;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
		System.out.println(combine(args, 0));
		System.out.println(combine1(args));
	}
	
	public static String combine(String[] arr, int i) {
		if (i == arr.length) {
			return "";
		} else {
			return arr[i] + combine(arr, i+1);
		}
	}

	public static String combine1(String[] arr) {
		String str = "";
		for (String a : arr)
		{
			str += a;
		}
		return str;
	}
}

