package datastructure.primitive;

public class Ex03FunctionPassing {

	public static void main(String[] args) {
		// phạm vi sử dụng của biến
		// function scope, block scope

		int a = 22;
		int b = 37;

		{
			var x = 5;
			int c = 100;
			System.out.println("The value of c --> " + c);
		}
		// System.out.println(c); ERROR --> block scope

		modify(a); // 999
		modify(b); // 999
		modify(56); // 999
		b = a;

		System.out.println(a); // 22
		System.out.println(b); // 22

		System.out.println();

		swap(11, 55);

	}

	// pass by value --> 100% AT STACK
	// truyền tham số qua hàm --> truyền giá trị<STACK> qua tham số <ô nhớ ko thay
	// đổi>

	// int a --> khai báo tham số cho hàm --> nhận giá trị từ bên ngoài vào
	// --> khi truyền 1 biến kiểu nguyên thủy vào hàm --> biến đó sẽ không bao h cập
	// nhật giá trị được
	private static void modify(int a) {
		a = 999;
		System.out.println(a);
	}

	// swap(5, 7);
	private static void swap(int a, int b) {
		int tmp = a; // tmp = 5
		a = b; // a=7; b=7
		b = tmp; // a=7; b=5 --> done!
		
	}

}
