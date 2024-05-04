package common;

public class Ex02Swap {

	public static void main(String[] args) {
		// boxing, unboxing, autoboxing

		int x1 = 2;
		int x2 = 8;
		
		swapInt(x1, x2);
		System.out.println("x1 --> " + x1);
		System.out.println("x2 --> " + x2);
		
		System.out.println("---------------------------------");
		
		Integer xo1 = 17;
		Integer xo2 = 99;
		
		swapInteger(xo1, xo2);
		System.out.println("xo1 --> " + xo1);
		System.out.println("xo2 --> " + xo2);

	}

	private static void swapInt(int a, int b) {
		int tmp = a;
		a = b;
		b = tmp;
	}

	private static void swapInteger(Integer a, Integer b) {
		Integer tmp = a;
		a = b;
		b = tmp;
	}

}
