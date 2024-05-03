package demomain;

public class Ex01TestMainMethod {
	
	/*
	 Để thực thi 1 chương trình trong JAVA
	 
	 + Tạo ra 1 class --> tạo ra 1 file .java
	   có tên trùng với tên class
	 
	 + Chương trình
	   . Cấu dữ dữ liệu: khai báo và lưu trữ dữ liệu
	   . Hàm/phương thức: nơi xử lý một chức năng trong chương trình
	 
	   . Hàm main: nơi bắt đầu và kết thức 1 chương trình khi thực thi
	   
	   System.out.println
	      --> In nội dung ra xong xuống hàng
	   System.out.print
	      --> In nội dung ra xong giữ nguyên
	   \n --> xuống hàng
	 
	 + Thực thi
	   Ctrl F11 --> Thực thi class hiện tại
	   Ctrl d   --> Xóa nhanh 1 dòng
	   Ctrl shift f --> format(định dạng code về chuẩn của Java mặc định)
	   
	   public class A {
	   		public static void main(String[] args) {
	   			System.out.println("Hello");
	   		}
	   }
	   
	   Cú pháp khi tạo ra 1 hàm
	   
	   access modifier: public, private, protected, mặc định: phạm vi truy cập
	   		 + public: có thể sử dụng mọi nơi
	   		 + private: dùng trong class chứa hàm này
	   		 
	   static: thuộc phạm vi của class
	           lấy class(chứa nó) gọi hàm static
	           giải thích kỹ hơn trong phần OOP
	   
	   return_type: kiểu dữ liệu trả về
	         + void : ko có trả về gì cả
	         + !void: trả về để sử dụng lại kq trả về đó làm việc khác
	   
	   method_name: tên hàm
	         + đại diện cho chức năng của hàm
	   
	   parameters: danh sách tham số
	         + đầu vào của hàm
	   
	   {...} : body
	         + nội dung hàm
	   
	   [access modifier] [static] return_type method_name(parameters) {
	   		// body: implementation
	   }
	 
	 */
	
	public static void main(String[] args) {
		System.out.println("Hàm main 01");
		System.out.println("Welcome To Java21 class");
		
		System.out.println();
		
		// Gọi hàm printStarTriangle
		printStarTriangle();
		printStarTriangle();
		
		System.out.println("\n.... Main Finsihed ....");
	}
	
	// Tạo ra/khai báo 1 chức năng(hàm/phương thức) để in ra tam giác ngôi sao có 5 dòng
	public static void printStarTriangle() {
		System.out.println("*");
		System.out.println("* *");
		System.out.println("* * *");
		System.out.println("* * * *");
		System.out.println("* * * * *");
	}
}