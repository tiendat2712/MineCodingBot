package datastructure.primitive;

public class Ex02ModifyValue {

	public static void main(String[] args) {
		// = --> toán tử gán
		// VT = VP --> lấy giá trị của VP gán cho VT
		
		int a = 5;
		int b = 7;
		
		System.out.println("a1 --> " + a);
		System.out.println("b1 --> " + b);
		
		System.out.println();
		
		b = 10;
		a = b; // --> gán giá trị của b cho a
		
		System.out.println("a2 --> " + a);
		System.out.println("b2 --> " + b);
		
	}
	
}
