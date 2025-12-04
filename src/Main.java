public class Main {

    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork();

        // ---- Create users ----
        sn.createUserProfile("navid", 23);
        sn.createUserProfile("john", 30);

        // ---- Create companies ----
        sn.createCorporateProfile("Apple");
        sn.createCorporateProfile("Nike");

        // ---- Post statuses (out of order timestamps) ----
        sn.postStatus("navid", "Hello world!", 0, 0, 1);
        sn.postStatus("navid", "Go to Algorithm Class", 0, 0, 2);
        sn.postStatus("navid", "Working on my project", 0, 0, 10);
        sn.postStatus("navid", "Going to the gym later", 0, 0, 7);
        sn.postStatus("navid", "Having lunch", 0, 0, 3);

        // ---- Post ads (out of order timestamps) ----
        sn.postAd("Apple", "New iPhone available!", 0, true, 40);
        sn.postAd("Apple", "Apple Music discount!", 0, false, 15);
        sn.postAd("Nike", "New running shoes!", 0, false, 20);

        // ---- Test wall printing ----
        System.out.println("===== NAVID'S WALL =====");
        sn.printWallOf("navid");

        // ---- Test user with no posts ----
        System.out.println("\n===== JOHN'S WALL =====");
        sn.printWallOf("john");
    }
}
