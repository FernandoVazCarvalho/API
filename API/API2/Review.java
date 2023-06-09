package mainActivity.API.API2;

public class Review {
    private String author_name;
    private int rating;
    private String text;
    private long time;

    public Review(String authorName, int rating, String text, long time) {
        this.author_name = authorName;
        this.rating = rating;
        this.text = text;
        this.time = time;
    }
    public String getAuthorName() {
        return author_name;
    }
    public void setAuthorName(String authorName) {
        this.author_name = authorName;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {
        this.time = time;
    }

}
