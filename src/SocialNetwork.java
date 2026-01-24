public class SocialNetwork implements iSocialNetwork {

    //add
    private Vector<UserProfile> users;
    private Vector<CorporateProfile> companies;
    private Graph graph;

    public SocialNetwork() {
        users = new Vector<UserProfile>(100);
        companies = new Vector<CorporateProfile>(100);
        graph = new Graph();
    }


    //helper to find users
    //users are listed in a normal vector so we cannot use binary search
    private UserProfile findUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }


    //helper to find a company
    public CorporateProfile findCompany(String companyName) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getCompanyName().equals(companyName)) {
                return companies.get(i);
            }
        }
        return null;
    }



    //Creates a new user profile only if the username is not yet taken.
    @Override
    public void createUserProfile(String username, int age) {
        if (findUser(username) != null) return;

        UserProfile userProfile = new UserProfile(username, age);
        users.addLast(userProfile);
        graph.addNode(username);
    }


    //Creates a new corporate profile only if the name is not already registered.
    @Override
    public void createCorporateProfile(String companyName) {
        if (findCompany(companyName) != null) return; // avoid duplicates

        CorporateProfile corporateProfile = new CorporateProfile(companyName);
        companies.addLast(corporateProfile);
        graph.addNode(companyName);
    }

    //Posts a status update to the specified user's profile.
    //Statuses are stored in a sorted structure inside UserProfile.
    @Override
    public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp) {
        UserProfile user = findUser(username);
        if (user == null) return; // invalid user

        user.postStatus(status, privacy, ageLimit, timestamp);
        // ComparableVector handles ordering by using compareTo method of the statusUpdate
    }



    //Posts an advertisement to the specified company's profile.
    //Ads are also stored in chronological sorted order.
    @Override
    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp) {
        CorporateProfile corp = findCompany(companyName);
        if (corp == null) return; // invalid company

        corp.postAd(ad, ageLimit, paid, timestamp);
    }

    //make edges between 2 nodes
    @Override
    public void connect(String username1, String username2) {
        UserProfile u1 = findUser(username1);
        UserProfile u2 = findUser(username2);

        if (u1 == null || u2 == null) return;

        graph.addEdge(username1, username2, 1);
        graph.addEdge(username2, username1, 1);
    }

    //make edge just from one node to the other one
    @Override
    public void follow(String username, String corporateName) {
        UserProfile user = findUser(username);
        CorporateProfile corp = findCompany(corporateName);

        if (user == null || corp == null) return;

        graph.addEdge(username, corporateName, 1);
    }

    //I added this part so companies can follow users because without this, distanceExcludeCorporate and distance methods
    //will give the same answers
    public void companyFollowUser(String companyName, String username)
    {
        CorporateProfile corp = findCompany(companyName);
        UserProfile user = findUser(username);

        if (corp == null || user == null) return;

        graph.addEdge(companyName, username, 1);
    }

    //because nodes are unweighted we can use BFS
    @Override
    public int distance(String username1, String username2) {
        return graph.distance(username1, username2);
    }

    @Override
    public void printPath(String username1, String username2) {
        graph.printPath(username1, username2);
    }

    @Override
    public void printFriendListOf(String username)
    {
        Vector<String> neighbors = graph.getEdges(username);
        if (neighbors == null) return;

        for (int i = 0; i < neighbors.size(); i++)
        {
            String friendName = neighbors.get(i);

            // Only users (exclude companies)
            if (findUser(friendName) != null)
            {
                System.out.println(friendName);
            }
        }
    }

    @Override
    public void printWallOfAFriend(String username, String friendUsername)
    {
        UserProfile user = findUser(username);
        UserProfile friend = findUser(friendUsername);

        if (user == null || friend == null)
            return;

        // must be connected
        if (graph.distance(username, friendUsername) == -1)
            return;

        SortedVector<StatusUpdate> posts = friend.getPosts();

        for (int i = 0; i < posts.size(); i++)
        {
            StatusUpdate post = posts.get(i);

            // privacy check
            if (post.getPrivacy() == 2) continue;

            // age check
            if (user.getAge() < post.getAgeLimit()) continue;

            System.out.println(post);
        }
    }

    //adding this helper to check if a username is a company name or not to prevent paths with companies
    private boolean isCorporate(String label) {
        return findCompany(label) != null;
    }

    //this method and the printPathExcludeCorporate don't make any sense if companies cannot follow users or connect to them
    //in that case both distance and distanceExcludeCorporate gives the same thing
    @Override
    public int distanceExcludeCorporate(String username1, String username2)
    {
        if (isCorporate(username1) || isCorporate(username2))
            return -1;

        Queue queue = new Queue();
        Vector<String> visited = new Vector<>(100);
        Vector<String> parent = new Vector<>(100);

        queue.push(username1);
        visited.addLast(username1);
        parent.addLast(null);

        while (!queue.empty())
        {
            String current = (String) queue.pop();

            if (current.equals(username2))
            {
                int dist = 0;
                int idx = visited.indexOf(current);

                while (parent.get(idx) != null)
                {
                    dist++;
                    idx = visited.indexOf(parent.get(idx));
                }
                return dist;
            }

            //to prevent accessing to the internal implementation of edges i used neighbors vector
            //it keeps the graph encapsulate
            Vector<String> neighbors = graph.getEdges(current);
            if (neighbors == null) continue;

            for (int i = 0; i < neighbors.size(); i++)
            {
                String next = neighbors.get(i);

                if (isCorporate(next)) continue;
                if (visited.contains(next)) continue;

                //here we assign an index to each node beside checking the visited status of it!
                visited.addLast(next);
                //here we define the parent of each node by its associated index of the visited vector
                parent.addLast(current);
                queue.push(next);
            }
        }
        return -1;
    }

    @Override
    public void printPathExcludeCorporate(String username1, String username2)
    {
        if (isCorporate(username1) || isCorporate(username2))
        {
            System.out.println("No path.");
            return;
        }

        Queue queue = new Queue();
        Vector<String> visited = new Vector<>(100);
        Vector<String> parent = new Vector<>(100);

        queue.push(username1);
        visited.addLast(username1);
        parent.addLast(null);

        while (!queue.empty())
        {
            String current = (String) queue.pop();

            if (current.equals(username2))
            {
                Vector<String> path = new Vector<>(100);
                String step = current;

                while (step != null)
                {
                    //add the current node to the path
                    path.addFirst(step);
                    //get the index of the node
                    int idx = visited.indexOf(step);
                    //set the parent of the node as the current (step) node
                    step = parent.get(idx);
                }

                for (int i = 0; i < path.size(); i++)
                {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1)
                        System.out.print(" -> ");
                }
                System.out.println();
                return;
            }

            Vector<String> neighbors = graph.getEdges(current);
            if (neighbors == null) continue;

            for (int i = 0; i < neighbors.size(); i++)
            {
                String next = neighbors.get(i);

                if (isCorporate(next)) continue;
                if (visited.contains(next)) continue;

                visited.addLast(next);
                parent.addLast(current);
                queue.push(next);
            }
        }

        System.out.println("No path.");
    }

    /**
     * Collects ALL ads from ALL companies into a single sorted vector.
     * Combined ad list is used for printing user walls (one ad every 4 posts).
     */
    private SortedVector<Ad> getAllAds() {
        SortedVector<Ad> allAds = new SortedVector<>(1000);

        // Iterate through each company and merge their ads
        for (int i = 0; i < companies.size(); i++) {
            CorporateProfile corp = companies.get(i);
            SortedVector<Ad> ads = corp.getAds();

            // Add ads while keeping the global list sorted O(n^2)
            for (int j = 0; j < ads.size(); j++) {
                allAds.addSorted(ads.get(j));
            }
        }
        return allAds;
    }



    //Prints the wall of a user by alternating:
    // - 4 user status updates and 1 advertisement
    //the ads will be reused in case of lacking number of them
    @Override
    public void printWallOf(String username) {
        UserProfile user = findUser(username);
        if (user == null) return;

        SortedVector<StatusUpdate> posts = user.getPosts();
        SortedVector<Ad> ads = getAllAds();

        int i = 0; // index for posts
        int j = 0; // index for ads

        // If user has no posts, show a single ad if available
        if (posts.size() == 0) {
            if (!ads.isEmpty()) {
                System.out.println(ads.get(0));
            }
            return;
        }

        // Print posts in blocks of 4, then an ad
        while (i < posts.size()) {

            // Print up to 4 posts
            for (int k = 0; k < 4 && i < posts.size(); k++) {
                System.out.println(posts.get(i));
                i++;
            }

            // After 4 posts, print an ad (if any exist)
            if (!ads.isEmpty()) {
                System.out.println(ads.get(j));
                j++;

                // Wrap around ads list to keep alternating continuously
                if (j == ads.size()) {
                    j = 0;
                }
            }
        }
    }

    @Override
    public String toString() {
        return graph.toString();
    }

}
