public class Main {

    public static void main(String[] args) {

        SocialNetwork sn = new SocialNetwork();

        //Create users
        sn.createUserProfile("Navid", 23);
        sn.createUserProfile("John", 30);
        sn.createUserProfile("Alice", 25);
        sn.createUserProfile("Bob", 30);
        sn.createUserProfile("Charlie", 28);

        //Create companies
        sn.createCorporateProfile("Apple");
        sn.createCorporateProfile("Nike");

        //Post statuses
        sn.postStatus("Navid", "Hello world!", 0, 0, 1);
        sn.postStatus("Navid", "Go to Algorithm Class", 0, 0, 2);
        sn.postStatus("Navid", "Working on my project", 0, 0, 10);
        sn.postStatus("Navid", "Going to the gym later", 0, 0, 7);
        sn.postStatus("Navid", "Having lunch", 0, 0, 3);

        //Post ads without time order to check the sorting
        sn.postAd("Apple", "New iPhone available!", 0, true, 40);
        sn.postAd("Apple", "Apple Music discount!", 0, false, 15);
        sn.postAd("Nike", "New running shoes!", 0, false, 20);

        //Test printWallOf
        System.out.println("NAVID'S WALL:");
        sn.printWallOf("Navid");

        //Test user with no posts
        System.out.println("\nJOHN'S WALL:");
        sn.printWallOf("John");

        //Connect users
        sn.connect("Alice", "Bob");
        sn.connect("Alice", "Navid");
        sn.connect("Bob", "Charlie");

        //Follow companies by users
        sn.follow("Alice", "Nike");
        sn.follow("Bob", "Nike");
        sn.follow("Charlie", "Apple");

        //To follow users by companies
        sn.companyFollowUser("Apple", "Navid");

        System.out.println("=== GRAPH STRUCTURE ===");
        System.out.println(sn);   // relies on Graph.toString()

        System.out.println("=== Distance check ===");
        //Distances between users
        //first case: we don't have companies in between so the shortest path will be shown in both scenarios:
        System.out.println("Distance Alice -> Charlie: "
                + sn.distance("Alice", "Charlie"));

        System.out.println("DistanceExcludeCorporate Alice -> Charlie: "
                + sn.distanceExcludeCorporate("Alice", "Charlie"));

        //second case: the shortest path will go through a company and excluding company gives a path with 1 time longer length
        System.out.println("Distance Charlie -> Navid: "
                + sn.distance("Charlie", "Navid"));  // shortest path through Apple: 2

        System.out.println("DistanceExcludeCorporate Charlie -> Navid: "
                + sn.distanceExcludeCorporate("Charlie", "Navid")); //the path without going through companies: 3

        //Paths
        System.out.println("=== Find Path check ===");
        System.out.print("Path Alice -> Charlie: ");
        sn.printPath("Alice", "Charlie");

        System.out.print("PathExcludeCorporate Alice -> Charlie: ");
        sn.printPathExcludeCorporate("Alice", "Charlie");

        System.out.print("Path Charlie -> Navid: ");
        sn.printPath("Charlie", "Navid");

        System.out.print("PathExcludeCorporate Charlie -> Navid: ");
        sn.printPathExcludeCorporate("Charlie", "Navid");


        System.out.print("PathExcludeCorporate Alice -> Charlie: ");
        sn.printPathExcludeCorporate("Apple", "Navid");

        System.out.print("PathExcludeCorporate Alice -> Charlie: ");
        sn.printPathExcludeCorporate("Charlie", "Apple");

        // Friend list
        System.out.print("=== Friend List check ===");
        System.out.println("\nFriends of Bob:");
        sn.printFriendListOf("Bob");
    }
}
