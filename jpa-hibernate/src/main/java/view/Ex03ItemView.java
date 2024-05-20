package view;

import service.HibernateItemService;
import service.ItemService;
import java.util.Collection;
import java.util.stream.Collectors;

import net.bytebuddy.asm.Advice.Return;

import static utils.CollectionUtils.*;

import persistence.entities.Item;
import persistence.entities.Size;

public class Ex03ItemView {

	private static ItemService itemService;
	
	static {
		itemService = new HibernateItemService();
	}
	
	public static void main(String[] args) {
		System.out.println("--------------------------------------------------");
		
		selfGenerate(
				"2ab. Display all items", itemService.getAll()
				);
		
		generate(
				"2c. Display item by ID = '3'", itemService.get(3)
				);

	}
	
	public static void selfGenerate(String prefix, Collection<Item> elements) {
		System.out.println(prefix + " --> {");
		
		elements.forEach(e -> {
			System.out.println("   " + e);
			System.out.println("   --> " + e.getGroup() +" \n");
			
			String sizes = e.getItemDetails().stream()
			        .map(itd -> {
			        	Size size = itd.getSize();
			        	return size.getSize() + ", " + (size.getGender() == true ? "Nam" : "Nữ");
			        })
			        .collect(Collectors.joining(" - "));
			
			System.out.println("   itemDetails --> " + sizes +" \n");
			System.out.println("   ----------------------------------------------------------------------- ");
		});
		
		System.out.println("}\n");
	}
	
}






















