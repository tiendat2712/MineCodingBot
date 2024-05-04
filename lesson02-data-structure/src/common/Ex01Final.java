package common;

import datastructure.object.custom.Item;

public class Ex01Final {

	public static void main(String[] args) {
		// Final --> Final ở STACK --> không thể thay đổi giá trị cho biến ở STACK
		// không thể dùng toán tử = cho biến final

		// Primitive type
		// Giá trị lưu trữ ở STACK
		// Biến lưu ở STACK chứa thông tin giá trị
		final int a = 5;
		int b = 7;
		int c = 10;

		// a = b;
		// a = 10;
		b = c;

		// Object type
		// Giá trị lưu trữ ở HEAP
		// Biến lưu ở STACK chứa thông tin ô nhớ ở HEAP
		Item itA = new Item(1, 'A', 11);       // H1
		final Item itB = new Item(2, 'B', 22d);// H2
		Item itC = new Item(3, 'C', 33);       // H3

		itA = itB; // A,B --> H2
		// itB = itC; error
		itC = itA; // C,A --> H2

		itB.name = 'Z';
		itB.price = 87; // --> H2: 2, 'Z', 87

		System.out.println("itA --> " + itA);
		System.out.println("itB --> " + itB);
		System.out.println("itC --> " + itC);

	}

}
