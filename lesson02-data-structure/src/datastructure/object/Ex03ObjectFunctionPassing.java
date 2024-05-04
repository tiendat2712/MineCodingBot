package datastructure.object;

public class Ex03ObjectFunctionPassing {
	
	/*
	  
	 Primitive Data Type
	    + Dùng KDL có sẵn của java 
	    + int, char, double
	    
	 Object Data Type
	    + Dùng KDL có sẵn của java: Integer, Double, String, LocalDate
	    + Tạo ra KDL đối tượng: Item, Employee
	 ??:
	   1. Khai báo một số nguyên dùng int hay Integer. Vì sao?
	   
	   
	   2. Nếu khai báo một chuỗi --> sử dụng String varName = "....";
	                   hay là    --> String name = new String();
	                   
	 */

	public static void main(String[] args) {

		Item i3 = new Item(3, 'C', 33d); // H3

		System.out.println("i3 1st --> " + i3);

		modify(i3);
		System.out.println("i3 2nd --> " + i3);
	}

	// modify(i3) --> Item item = i3;
	private static void modify(Item item) { // H3 ... i3, item vẫn là hai biến khác nhau, chỉ là đang cùng trỏ đến cùng 1 ô nhớ
		item.price = 99;
		Item i4 = new Item(4, 'D', 44d); // H4
		i4 = item;                       // i4, item --> H3
		i4.price = 88;
		i4 = new Item();                 // i4 --> H5
		item.price = 77;
		item = i4;                       // item --> H5
	}

}
