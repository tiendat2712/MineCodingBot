package pattern.builder;

import java.time.LocalDateTime;

public class Ex01RootCase {

	public static void main(String[] args) {
		
		/*
		 
		 Design Pattern: là các mẫu thiết kế, được sinh/tạo ra từ kinh nghiệm của các người đi trước
		             --> giúp mình giải quyết các vấn đề cụ thể
		 
		 Builder Pattern:
		 + Đặt vấn đề:
		   Được dùng để tạo ra đối tượng cho các complex class/object
		   Complex class/object là các class có nhiều(>= 6) thuộc tính và có rất nhiều mixing constructor từ class này
		   A: a1, a2, a3, a4, a5, a6
		    --> constructor()
		    --> constructor(a1, a2, a3, a4, a5, a6)
		    --> constructor(a1, a5, a6)
		   
		   --> code rườm rà
		   --> Vì constructor luôn trùng tên với class:
		       Ví dụ: cần constructor 2 tham số a1,a2 và cần constructor 2 tham số a5,a6
		       --> nếu như a1 trùng KDL với a5
		                   a2 trùng KDL với a6 --> không thể
		                   
		       public A(int a1, String a2) ..
		       public A(int a5, String a6) ..
		       
		       Xử lí: --> Dùng duy nhất hàm khởi tạo rỗng, sau đó dùng setter để truyền tham số <OKE nhưng ko hay>
		       
		    ** ==> ** Dùng Builder Pattern:
		     Ví dụ: LocalDateTime: date, month, year, hour, minute, seconds, miliseconds, timezone,.......
		   + Cách thực thi
		     Thay vì dùng setter --> 1 lần gọi xong trả về void, lấy đối tượng đó đi gọi lại --> code dài
		     Dùng builder pattern style --> 1 lần gọi hàm set giá trị xong thì trả về chính đối tượng hiện tại 
		                                --> tiếp tục đi gọi các hàm khác
		 */
		
		System.out.println("now: " +LocalDateTime.now().withHour(20).withMinute(20).withSecond(20));
		
	}
	
}
