package u01_collections_introduction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionVsMap {

    public static void main(String[] args) {
        // Collection: a group of single elements
        List<String> names = new ArrayList<>();
        names.add("Iddu");
        names.add("Grace");
        names.add("Jenifer");
        System.out.println("Collection: " + names + " first=" + names.get(0));

        // Map: key -> value associations
        // Note: HashMap does not guarantee the order in which entries are printed.
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Iddu", 80);
        marks.put("Grace", 91);
        marks.put("Jenifer", 78);
        marks.put("Iddu", 95); // same key: the old value (80) is replaced
        System.out.println("Map: " + marks + " Iddu=" + marks.get("Iddu"));

        // Three Collection views of a Map
        System.out.println("keySet  : " + marks.keySet());   // Set<String>
        System.out.println("values  : " + marks.values());   // Collection<Integer>
        for (Map.Entry<String, Integer> e : marks.entrySet()) {
            System.out.println("entry   : " + e.getKey() + " = " + e.getValue());
        }

        // Views are backed by the map: removing a key from the view removes it from the map
        marks.keySet().remove("Jenifer");
        System.out.println("After removing key via view: " + marks);

        // Safe lookup with default
        System.out.println("Missing key -> " + marks.getOrDefault("Zoya", 0));
    }
}
