package simplified_geolocation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedSet;

public class Graph {

    private Map<String, Node> nodes;
    private Map<Node, SortedSet<Node>> adjacentNodes;

    public Graph(String filePath) throws FileNotFoundException {
        nodes = new HashMap<>();
        adjacentNodes = new HashMap<>();
        LinkedHashSet<Place> places = new LinkedHashSet<>();
        Set<category> cate = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            String command = line;
            Place place = null;
            Node node = null;
            while ((line = reader.readLine()) != null) {

                if (command.matches("//Category Definitions")) {

                    while (true) {
                        if (line == null || line.trim().startsWith("//")) {
                            command = line;
                            break;
                        }
                        String[] parts = line.split(",");
                        cate.add(new category(parts[0], parts[1]));
                        line = reader.readLine();
                    }
                } else if (command.matches("//Node Definitions")) {
                    while (true) {
                        if (line == null || line.trim().startsWith("//")) {
                            command = line;
                            break;
                        }
                        String[] parts = line.split(",");
                        node = new Node(parts[0], Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                        addNode(node);
                        line = reader.readLine();
                    }

                } else if (command.matches("//Adjacent Nodes")) {
                    while (true) {
                        if (line == null || line.trim().startsWith("//")) {
                            command = line;
                            break;
                        }
                        String[] parts = line.split("->");
                        if (parts.length == 2) {
                            String nodeId1 = parts[0].trim();
                            String nodeId2 = parts[1].trim();

                            Node node1 = nodes.get(nodeId1);
                            Node node2 = nodes.get(nodeId2);

                            if (node1 != null && node2 != null) {
                                adjacentNodes.get(node1).add(node2);
                                adjacentNodes.get(node2).add(node1);
                            } else {
                                System.out.println("Error: Invalid node IDs in adjacency: " + line);
                            }
                        } else {
                            System.out.println("Error: Invalid adjacency definition: " + line);
                        }
                        line = reader.readLine();
                    }
                } else if (command.matches("//Places at nodes")) {
                    while (true) {
                        if (line == null || line.trim().startsWith("//")) {
                            command = line;
                            break;
                        }
                        String[] parts = line.split("->");

                        String nodeId = parts[0];
                        String data = parts[1];

                        String[] dataItems = data.split(",");
                        String data1 = dataItems[0];
                        String data2 = dataItems[1];
                        place = new Place(data1, data2);
                        places.add(place);
                        Node node1 = nodes.get(nodeId);
                        node1.addPlace(place);
                        for (int i = 2; i < dataItems.length; i++) {
                            for (category C : cate) {
                                if (C.getId().equals(dataItems[i])) {
                                    place.addCategory(C);
                                }
                            }

                        }
                        line = reader.readLine();
                    }

                } else if (command.matches("//Place Reviews")) {
                    while (true) {
                        if (line == null || line.trim().startsWith("//")) {
                            command = line;
                            break;
                        }
                        String[] parts = line.split("->");

                        String nodeId = parts[0];
                        String data = parts[1];
                        String[] dataItems = data.split(",");
                        Review rev = new Review(dataItems[0], dataItems[1], Integer.parseInt(dataItems[2]));
                        for (Place p : places) {
                            if ((nodeId).equals(p.getId())) {
                                p.addReview(rev);
                            }
                        }
                        line = reader.readLine();
                    }

                } else {
                    break;
                }

            }
        } catch (IOException e) {
            System.out.println("Error: Invalid option. Please try again.");
            e.printStackTrace();
        }
    }

    private void addNode(Node node) {
        nodes.put(node.getId(), node);
        adjacentNodes.putIfAbsent(node, new TreeSet<>((node1, node2) -> {
            double distance1 = Node.calcDistance(node, node1);
            double distance2 = Node.calcDistance(node, node2);
            return Double.compare(distance1, distance2);
        }));
    }

    public Trip calcTrip(String startId, String endId) {
        Node start = nodes.get(startId);
        Node end = nodes.get(endId);

        if (start == null || end == null) {
            System.out.println("Start or end node not found in the graph.");
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        Map<Node, Node> predecessors = new HashMap<>();
        Set<Node> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            if (current.equals(end)) {
                return reconstructTrip(predecessors, start, end);
            }

            for (Node neighbor : adjacentNodes.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        System.out.println("No path found from start to end.");
        return null;
    }

    private Trip reconstructTrip(Map<Node, Node> predecessors, Node start, Node end) {
        LinkedList<Node> path = new LinkedList<>();
        Node current = end;

        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }

        if (path.getFirst().equals(start)) {
            Trip trip = new Trip();
            for (Node node : path) {
                trip.addNode(node);
            }
            return trip;
        } else {
            System.out.println("An error occurred while reconstructing the path.");
            return null;
        }
    }

    public void printPlaces(String category) {
        for (Node node : nodes.values()) {
            node.printPlaces(category);
        }
    }

    public void printReviews(String placeId) {
        for (Node node : nodes.values()) {
            node.printReviews(placeId);
        }
    }
}
