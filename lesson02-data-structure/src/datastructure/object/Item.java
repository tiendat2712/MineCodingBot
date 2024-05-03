package datastructure.object;

/**
 * Tạo ra KDL đối tượng KDL tên là: Item
 * 
 * Bao gồm 3 thông tin: Item(id, name, price)
 * 
 */
public class Item {

	// attributes: thuộc tính
	// Khi khởi tạo/gán giá trị cho 1 biến kiểu Item
	// Thì ô nhớ mà biến đó trỏ đến phải luôn có 3 giá trị của thuộc tính
	public int id;
	public char name;
	public double price;

	// constructor
	// constructor name = class name
	// no return type --> default return current class type --> (KDL Item)
	public Item() {
	}
	
	public Item(int id, char name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	// Toán tử = --> gán giá trị ở STACK
	
	// định nghĩa lại hàm toString() từ class Object
	// định nghĩa lại toString từ class Object
	// Item i1, i2 --> KDL item
	// i1.toString() // H1 --> this = i1
	// i2.toString() // H2 --> this = i2

	// trong class sẽ có biến this --> để biết biến/đối tượng nào đang gọi hàm của class đó
	
	@Override
	public String toString() {
		return "[" + this.id + ", " + this.name + ", " + this.price + "]";
	}

}
