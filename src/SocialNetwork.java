public class SocialNetwork implements iSocialNetwork {

    private Vector<UserProfile> users;
    private Vector<CorporateProfile> companies;

    public SocialNetwork() {
        users = new Vector<UserProfile>(100);
        companies = new Vector<CorporateProfile>(100);
    }

    public void sortPost (Vector<StatusUpdate> posts){

        for (int i = 0; i < posts.size(); i++) {
            for (int j = i+1; j < posts.size(); j++) {
                StatusUpdate post1 = posts.get(i);
                StatusUpdate post2 = posts.get(j);

                if (post1.getTimestamp() > post2.getTimestamp()) {
                    posts.set(i, post2);
                    posts.set(j, post1);
                }
            }
        }
    }

    public void sortAd (Vector<Ad> ads){

        for (int i = 0; i < ads.size(); i++) {
            for (int j = i+1; j < ads.size(); j++) {
                Ad ad1 = ads.get(i);
                Ad ad2 = ads.get(j);

                if (ad1.getTimestamp() > ad2.getTimestamp()) {
                    ads.set(i, ad2);
                    ads.set(j, ad1);
                }
            }
        }
    }

    public UserProfile findUser (String username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return users.get(i);
            }
        }
        return null;
    }

    public CorporateProfile findCompany (String companyName){
        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getCompanyName().equals(companyName)) {
                return companies.get(i);
            }
        }
        return null;
    }

    @Override
    public void createUserProfile(String username, int age){
        if (findUser(username) != null) return;

        UserProfile userProfile = new UserProfile(username, age);
        users.addLast(userProfile);
    }

    @Override
    public void createCorporateProfile(String companyName){
        if  (findCompany(companyName) != null) return;

        CorporateProfile corporateProfile = new CorporateProfile(companyName);
        companies.addLast(corporateProfile);
    }

    @Override
    public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp){
        UserProfile user =  findUser(username);
        if  (user == null) return;

        user.postStatus(status, privacy, ageLimit, timestamp);
        sortPost(user.getPosts());
    }

    @Override
    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp){
        CorporateProfile corp = findCompany(companyName);
        if  (corp == null) return;

        corp.postAd(ad, ageLimit, paid, timestamp);
        sortAd(corp.getAds());
    }

    private Vector<Ad> getAllAds() {
        Vector<Ad> allAds = new Vector<>(1000);

        for (int i = 0; i < companies.size(); i++) {
            CorporateProfile corp = companies.get(i);
            Vector<Ad> ads = corp.getAds();

            for (int j = 0; j < ads.size(); j++) {
                allAds.addLast(ads.get(j));
            }
        }
        sortAd(allAds);
        return allAds;
    }

    @Override
    public void printWallOf(String username){
        UserProfile user = findUser(username);
        if  (user == null) return;

        Vector<StatusUpdate> posts = user.getPosts();
        Vector<Ad> ads = getAllAds();

        int i = 0; //posts index
        int j = 0; //ads index

        while (i < posts.size()) {

            for (int k = 0; k < 4 && i < posts.size(); k++) {
                System.out.println(posts.get(i));
                i++;
            }

            // ALWAYS PRINT AN AD (repeat them when necessary)
            if (!ads.isEmpty()) {
                System.out.println(ads.get(j));
                j++;

                // wrap around (circular loop)
                if (j == ads.size()) {
                    j = 0;   // restart from first ad
                }
            }
        }
    }
}
