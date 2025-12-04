public class StatusUpdate implements Comparable<StatusUpdate> {

    private String content;
    private String author;
    private int privacy;
    private int ageLimit;
    private int timestamp;

    public StatusUpdate(String content, String author, Integer privacy, Integer ageLimit, Integer timestamp) {
        this.content = content;
        this.author = author;
        this.privacy = privacy;
        this.ageLimit = ageLimit;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrivacy() {
        return privacy;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public int getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Status update: " + timestamp + ", " + author + ", "
                + privacy + ", " + ageLimit + ", " + content;
    }

    // using compareTo to sort posts by their timestamp
    @Override
    public int compareTo(StatusUpdate other) {
        return this.getTimestamp() - other.getTimestamp();
    }
}

