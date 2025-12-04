public class CorporateProfile {

    private String companyName;
    private ComparableVector<Ad> ads;

    public CorporateProfile(String companyName){
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public ComparableVector<Ad> getAds() {
        return ads;
    }

    public void postAd (String content, int ageLimit, boolean paid, int timestamp){
        Ad ad = new Ad(content, this.companyName, ageLimit, paid, timestamp);
        ads.addLast(ad);
    }

    public void printWall (){
        for (int i = 0; i < ads.size(); i++){
            //automatically prints via Ad.toString()
            System.out.println(ads.get(i));
        }
    }
}
