public class SocialNetwork implements iSocialNetwork {

    private Vector<UserProfile> users;
    private Vector<CorporateProfile> companies;

    public SocialNetwork() {
        users = new Vector<UserProfile>(100);
        companies = new Vector<CorporateProfile>(100);
    }


    /**
     * Helper method to find a user by username.
     * Returns null if user does not exist.
     */
    public UserProfile findUser(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }


    /**
     * Helper method to find a corporate profile by company name.
     * Returns null if the company is not registered.
     */
    public CorporateProfile findCompany(String companyName) {
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getCompanyName().equals(companyName)) {
                return companies.get(i);
            }
        }
        return null;
    }


    /**
     * Creates a new user profile only if the username is not yet taken.
     */
    @Override
    public void createUserProfile(String username, int age) {
        if (findUser(username) != null) return;

        UserProfile userProfile = new UserProfile(username, age);
        users.addLast(userProfile);
    }


    /**
     * Creates a new corporate profile only if the name is not already registered.
     */
    @Override
    public void createCorporateProfile(String companyName) {
        if (findCompany(companyName) != null) return; // avoid duplicates

        CorporateProfile corporateProfile = new CorporateProfile(companyName);
        companies.addLast(corporateProfile);
    }


    /**
     * Posts a status update to the specified user's profile.
     * Statuses are stored in a sorted structure inside UserProfile.
     */
    @Override
    public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp) {
        UserProfile user = findUser(username);
        if (user == null) return; // invalid user

        user.postStatus(status, privacy, ageLimit, timestamp);
        // ComparableVector handles ordering
    }


    /**
     * Posts an advertisement to the specified company's profile.
     * Ads are also stored in chronological sorted order.
     */
    @Override
    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp) {
        CorporateProfile corp = findCompany(companyName);
        if (corp == null) return; // invalid company

        corp.postAd(ad, ageLimit, paid, timestamp);
    }


    /**
     * Collects ALL ads from ALL companies into a single sorted vector.
     * Combined ad list is used for printing user walls (one ad every 4 posts).
     */
    private ComparableVector<Ad> getAllAds() {
        ComparableVector<Ad> allAds = new ComparableVector<>(1000);

        // Iterate through each company and merge their ads
        for (int i = 0; i < companies.size(); i++) {
            CorporateProfile corp = companies.get(i);
            ComparableVector<Ad> ads = corp.getAds();

            // Add ads while keeping the global list sorted O(n^2)
            for (int j = 0; j < ads.size(); j++) {
                allAds.addSorted(ads.get(j));
            }
        }
        return allAds;
    }


    /**
     * Prints the wall of a user by alternating:
     * - 4 user status updates
     * - 1 advertisement
     * Ads loop in a circular manner if there are fewer ads than needed.
     */
    @Override
    public void printWallOf(String username) {
        UserProfile user = findUser(username);
        if (user == null) return;

        ComparableVector<StatusUpdate> posts = user.getPosts();
        ComparableVector<Ad> ads = getAllAds();

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
}
