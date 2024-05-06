
package simplified_geolocation;

import java.util.*;


public class Place {

    private String id;
    private String name;
    private Set<category> categories;
     private LinkedHashSet<Review> reviews;

    public Place(String id, String name) {
        this.id = id;
        this.name = name;
        this.categories = new HashSet<>();
        this.reviews = new LinkedHashSet<>();
    }

public void addCategory(category cat) {
categories.add(cat);
}


    public void addReview(Review review) {
        reviews.add(review);
    }


 public boolean hasCategory(String categoryid) {
    for (category cat : categories) {
        if (cat.getId().equals(categoryid)) {
            return true;
        }
    }
    return false;
}

 
    public void printReviews(boolean inReverse) {
        if (inReverse) {
            Review[] reviewsArray = reviews.toArray(new Review[0]);
            for (int i = reviewsArray.length - 1; i >= 0; i--) {
                System.out.println(reviewsArray[i].toString());
            }
        } else {
            for (Review review : reviews) {
                System.out.println(review.toString());
            }
        }
    }

    @Override
    public String toString() {
        
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }
    
}
