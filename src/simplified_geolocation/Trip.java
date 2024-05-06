
package simplified_geolocation;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;


public class Trip {
    private LinkedHashSet<Node> path;

    public Trip() {
        this.path = new LinkedHashSet<>();
    }

    public void addNode(Node node) {
        path.add(node);
    }

    public void print() {
        Iterator<Node> iterator = path.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            System.out.printf("<%s, %f, %f>%n", node.getId(), node.getLatitude(), node.getLongitude());
            iterator.remove();
        }
    }


}
