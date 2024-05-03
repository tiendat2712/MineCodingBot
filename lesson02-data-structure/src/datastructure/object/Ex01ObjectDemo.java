package datastructure.object;

public class Ex01ObjectDemo {
	
	// running
	// debugging: break point

	public static void main(String[] args) {
		// khai báo và khởi tạo giá trị cho biến thuộc KDL nguyên thủy
		int a = 12;
		char b = '@';

		/*
		 * khai báo và khởi tạo giá trị cho biến thuộc KDL đối tượng +) new Item(); -->
		 * gọi hàm khởi tạo mặc định của class(KDL) Item --> mặc định 1 class sẽ có hàm
		 * khởi tạo mặc định --> default constructor + tạo ra 1 ô nhớ trên vùng nhớ heap
		 * + có đầy đủ thông tin tất cả thuộc tính của class chứa nó
		 */

		Item i1 = new Item(); // H1
		i1.id = 1;
		i1.name = 'S';
		i1.price = 11d;

		System.out.println("i1 address 1st --> " + System.identityHashCode(i1));

		// tạo ô nhớ mới và gán địa chỉ của ô nhớ mới cho i1
		i1 = new Item(); // H2 --> i1 trỏ đến H2
		i1.name = 'K';
		i1.price = 14.5;

		System.out.println("i1 address 2nd --> " + System.identityHashCode(i1));

		Item i2 = new Item();
		i2.id = 2;
		i2.name = 'C';
		i2.price = 22d;
		
		// Khởi tạo ô nhớ và gán giá trị trực tiếp cho ô nhớ đó
		Item i3 = new Item(3, '*', 33d);

		// Java có class gọi là cha của tất cả các KDL đối tượng(class) --> Object
		// Object class sẽ có 1 số hàm:
		// toString, hashcode, equals

		// Mặc định Item là con của Object --> sử dụng các hàm (được phép) bên class
		// Object

		// in biến KDL đối tượng
		// mặc định gọi hàm toString() có sẵn trong KDL của biến đó
		// in i1 --> i1.toString()
		// --> getClass().getName() + "@" + Integer.toHexString(hashCode());
		// khi in ra KDL đối tượng --> in ra giá trị tất cả thuộc tính của ô nhớ mà biến
		// đó đang trỏ đến

		System.out.println("i1 1st --> " + i1);
		// System.out.println("i1 with toString --> " + i1.toString());

		System.out.println("i2 --> " + i2);
		System.out.println("i3 --> " + i3);

	}

}
