
package simplified_geolocation;


public class Review {
    private String id;
    private String text;
    private int rating;

    public Review(String id, String text, int rating) {
        this.id = id;
        this.text = text;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
        public String toString() {
        return text +"- " + rating+"/5";
    }
}
