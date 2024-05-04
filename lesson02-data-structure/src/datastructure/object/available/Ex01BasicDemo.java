package datastructure.object.available;

import datastructure.object.custom.Item;

public class Ex01BasicDemo {

	public static void main(String[] args) {
		
		// primitive type
		int a = 5;
		System.out.println("a --> "+ a);
		
		// object type: custom
		Item item = new Item();
		Item item1 = new Item(1, 'A', 92);
		
		// object type: variable
		// Integer: int value
		// Character: char value
		
		Integer o1 = new Integer(11);
		Integer o2 = new Integer(28);
		Character c1 = new Character('$');
		Character c2 = '&';
		
		System.out.println();
		System.out.println("o1 --> "+ o1);
		System.out.println("o2 --> "+ o2);
		System.out.println();
		System.out.println("c1 --> "+ c1);
		System.out.println("c2 --> "+ c2);
		
		Integer o3 = 77;
		Integer o4 = 88;
		
		System.out.println();
		System.out.println("o3 --> "+ o3);
		System.out.println("o4 --> "+ o4);
		
		// Để khởi tạo giá trị cho KDL đối tượng 
		// Hầu hết sửu dụng từ khóa 'new' --> dùng cho KDL có sẵn của Java hoặc Custom
		
		// Đặc biệt: Với những KDL có sẵn của Java như: Integer, Long, Double, String,........
		// --> có thể khởi tạo giá trị ko cần từ khóa 'new'
		
	}
	
}
