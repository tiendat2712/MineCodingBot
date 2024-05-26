package utils;

import java.util.Collection;

public class CollectionUtils {

	public CollectionUtils() {
	}
	
	public static <Element> void generate(String prefix, Object object) {
		System.out.println(prefix + " --> {");
		
		System.out.println("   " + object);
		
		System.out.println("}\n");
	}
	
	public static <Element> void generate(String prefix, Collection<Element> elements) {
		System.out.println(prefix + " --> {");
		
		elements.forEach(e -> System.out.println("   " + e));
		
		System.out.println("}\n");
	}
	
}
