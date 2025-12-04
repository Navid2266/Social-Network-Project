public class UserProfile {

    private String username;
    private int age;
    private ComparableVector<StatusUpdate> posts;

    public UserProfile (String username, int age)
    {
        this.posts = new ComparableVector<>(100);
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

    public ComparableVector<StatusUpdate> getPosts()
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
