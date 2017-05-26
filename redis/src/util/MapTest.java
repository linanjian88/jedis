package util;

import java.util.*;

public class MapTest {

	public static void main(String[] args) {

		List<String> list = new ArrayList<String>();
		list.add("lnj");
		list.add("cxf");
		list.add("lnj");
		list.add("sl");
		Set temp = new HashSet(list);
		Iterator<String> item = temp.iterator();
		while (item.hasNext()) {
			System.out.println(item.next());
		}
	}
}
