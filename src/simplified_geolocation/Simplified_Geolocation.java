
package simplified_geolocation;


import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Simplified_Geolocation {

    private static Graph graph;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        String filePath = "input.txt";
        graph = new Graph(filePath);

        while (true) {
            displayMenu();
            try {
                System.out.print("Enter choice number: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        category.printAllCategories();
                        break;
                    case 2:
                        findPlaces();
                        break;
                    case 3:
                        findNodeOfplace();
                        break;
                    case 4:
                        calcTrip();
                        break;
                    case 0:
                        System.out.println("Exiting application.");
                        scanner.close(); 
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Error: Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Available choices:");
        System.out.println("\t(1) Display all categories");
        System.out.println("\t(2) Search the graph for places based on thier categories");
        System.out.println("\t(3) Display the reviews of a place");
        System.out.println("\t(4) Calculate the path between two nodes");
        System.out.println("\t(0) Exit");
    }


    private static void findPlaces() {
        try {
        System.out.print("Enter category id: ");
        String categoryId = scanner.nextLine();
        graph.printPlaces(categoryId);
    }catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
    }
    }

    private static void findNodeOfplace() {
        System.out.print("Enter place id: ");
        String placeId = scanner.nextLine();
        System.out.print("Do you want to display the reviews backwards (from newest to oldest) [y/n]: ");
        String s = scanner.nextLine();
        s = placeId + "," + s;
        graph.printReviews(s);
    }

    private static void calcTrip() {
        System.out.print("Enter starting node id: ");
        String startId = scanner.nextLine();
        System.out.print("Enter destination node id: ");
        String endId = scanner.nextLine();
        Trip T = graph.calcTrip(startId, endId);
        if (T != null) {
             T.print();
        }
    }
}
