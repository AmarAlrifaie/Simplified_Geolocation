/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplified_geolocation;

import java.util.LinkedHashSet;



public class Node {

    private String id;
    private double latitude;
    private double longitude;
    private LinkedHashSet<Place> places;

    public Node(String id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.places = new LinkedHashSet<>();
    }
    


    public void addPlace(Place place) {
        places.add(place);
    }

 
    public void printPlaces(String category) {
        for (Place place : places) {
            if (place.hasCategory(category)) {
                
                System.out.println(id + " has " + place + " (placeId=" + place.getId() + ")");
            }
        } 
    }

  
    public void printReviews(String placeId) {
         String[] parts = placeId.split(",");
         
        for (Place place : places) {
            if (place.getId().equalsIgnoreCase(parts[0])) {
                if (parts[1].equalsIgnoreCase("n")) {
                                    place.printReviews(true);
                                                    break;
                }else if(parts[1].equalsIgnoreCase("y")){
                place.printReviews(true);
                break;
                }else  System.out.println("Error: Invalid option. Please try again."); break;
            }
        }
    }

 
    public double calculateDistance(Node other) {
        final int R = 6371;
        double latDistance = Math.toRadians(other.latitude - this.latitude);
        double lonDistance = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;
        return Math.sqrt(distance);
    }
        public static double calcDistance(Node start, Node end) {
        final int R = 6371; 
        double latDistance = Math.toRadians(end.latitude - start.latitude);
        double lonDistance = Math.toRadians(end.longitude - start.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(start.latitude)) * Math.cos(Math.toRadians(end.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; 
        return Math.sqrt(distance);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Node{" +
               "id=" + id +
               ", latitude=" + latitude +
               ", longitude=" + longitude +
               ", places=" + places +
               '}';
    }

        
}
