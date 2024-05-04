package common;

public class SwapNumbers {
    public static void main(String[] args) {
        MyInteger a = new MyInteger(10);
        MyInteger b = new MyInteger(20);
        
        System.out.println("Trước khi hoán đổi:");
        System.out.println("a = " + a.value);
        System.out.println("b = " + b.value);
        
        swap(a, b);
        
        System.out.println("Sau khi hoán đổi:");
        System.out.println("a = " + a.value);
        System.out.println("b = " + b.value);
    }
    
    public static void swap(MyInteger x, MyInteger y) {
        int temp = x.value;
        x.value = y.value;
        y.value = temp;
    }
}

class MyInteger {
    public int value;

    public MyInteger(int value) {
        this.value = value;
    }
}

