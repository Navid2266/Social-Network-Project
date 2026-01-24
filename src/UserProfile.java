public class UserProfile {

    private String username;
    private int age;
    private SortedVector<StatusUpdate> posts;

    public UserProfile (String username, int age)
    {
        this.posts = new SortedVector<>(100);
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

    public SortedVector<StatusUpdate> getPosts()
    {
        return posts;
    }

    // Add sorted posts
    public void postStatus (String content, int privacy, int ageLimit, int timestamp){
        StatusUpdate status = new StatusUpdate(content, this.username, privacy, ageLimit, timestamp);
        posts.addSorted(status);
    }

    public void printWall (){
        for (int i = 0; i<posts.size(); i++){
            System.out.println(posts.get(i));
        }
    }

    @Override
    public String toString()
    {
        return "User Profile: " + username + ", age:" + age;
    }
}
