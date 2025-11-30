public class Ad implements Comparable<Ad>{

    private String content;
    private String author;
    private int ageLimit;
    private boolean paid;
    private int timestamp;

    public Ad(String content, String author, int ageLimit, boolean paid, int timestamp) {
        this.content = content;
        this.author = author;
        this.ageLimit = ageLimit;
        this.paid = false;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public int getTimestamp() {
        return timestamp;
    }


    @Override
    public int compareTo(Ad o) {
        return this.timestamp -  o.timestamp;
    }

    @Override
    public String toString() {
        return "Ad: " + timestamp + ", " + author + ", "
                + ageLimit + ", " + ", " + content;
    }
}
