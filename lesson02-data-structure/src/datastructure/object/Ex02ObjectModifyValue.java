package datastructure.object;

public class Ex02ObjectModifyValue {

	public static void main(String[] args) {

		// Toán tử gán = --> 100% gán ở STACK
		
		Item i1 = new Item(1, 'A', 11); // H1
		Item i2 = new Item(2, 'B', 22d);// H2
		
		System.out.println("i1 address --> "+ System.identityHashCode(i1) + " --> H1");
		System.out.println("i2 address --> "+ System.identityHashCode(i2) + " --> H2");

		// gán địa chỉ ô nhớ ở STACK
		i1 = i2; // i1,i2 --> H2
		i1.name = 'Z';
		
		System.out.println();
		System.out.println("i1 address --> "+ System.identityHashCode(i1) + " --> H2");
		System.out.println("i2 address --> "+ System.identityHashCode(i2) + " --> H2");
		
		System.out.println("i1 --> "+ i1); // id = 2; name = Z; price = 22d
		System.out.println("i2 --> "+ i2); // id = 2; name = Z; price = 22d
	}

}
