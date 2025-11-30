public class UserProfile {

    private String username;
    private Integer age;
    private Vector<StatusUpdate> posts;

    public UserProfile (String username, int age)
    {
        this.posts = new Vector<>(100);
        this.username = username;
        this.age = age;
    }

    public  String getUsername()
    {
        return username;
    }

    public Integer getAge()
    {
        return age;
    }

    public Vector<StatusUpdate> getPosts()
    {
        return posts;
    }


    public void postStatus (String content, int privacy, int ageLimit, int timestamp){
        StatusUpdate status = new StatusUpdate(content, this.username, privacy, ageLimit, timestamp);
        posts.addSorted(status);
    }

}
