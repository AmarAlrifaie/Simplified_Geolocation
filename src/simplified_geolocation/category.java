
package simplified_geolocation;

import java.util.HashSet;
import java.util.Set;


public class category {
        private static Set<category> categories = new HashSet<>();
        private String id;
        private String name;

    public category(String id, String name) {
        this.id = id;
        this.name = name;
         categories.add(this);
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    

    public static void printAllCategories() {
        for (category category : categories) {
            System.out.println(category.toString());
        }
    }
        public String toString() {
        return id + "-" + name;
    }



}
