public class SocialNetwork implements iSocialNetwork {

    private Vector<UserProfile> users;
    private Vector<CorporateProfile> company;

    public void createUserProfile(String username, int age){

        UserProfile userProfile = new UserProfile(username, age);
    }


    public void createCorporateProfile(String companyName){

        CorporateProfile corporateProfile = new CorporateProfile(companyName);
    }


    public void printWallOf(String username){
        Vector posts = new Vector(100);
        Vector ads  = new Vector(100);

        for (int i = 0; i<wall.size(); i++){
            Object item = wall.get(i);

            if (item instanceof StatusUpdate){
                posts.addLast((StatusUpdate)item);
            }else if (item instanceof Ad){
                ads.addLast((Ad)item);
            }
        }

        for (int i = 0; i<posts.size(); i++){
            for (int j = i+1; j<posts.size(); j++){

                StatusUpdate status1 = (StatusUpdate)posts.get(i);
                StatusUpdate status2 = (StatusUpdate)posts.get(j);

                if (status1.getTimestamp() > status2.getTimestamp()){
                    Object temp = posts.get(i);
                    posts.set(i, posts.get(j));
                    posts.set(j, temp);
                }
            }
        }

        for (int i = 0; i<ads.size(); i++){
            for (int j = i+1; j<ads.size(); j++){
                StatusUpdate status1 = (StatusUpdate)ads.get(i);
                StatusUpdate status2 = (StatusUpdate)ads.get(j);

                if (status1.getTimestamp() > status2.getTimestamp()){
                    Object temp = ads.get(i);
                    ads.set(i, ads.get(j));
                    ads.set(j, temp);
                }
            }
        }

    }


    public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp){

    };


    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp){

    };

    }
