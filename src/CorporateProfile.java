public class CorporateProfile {

    private String companyName;
    private ComparableVector<Ad> ads;

    public CorporateProfile(String companyName){
        this.companyName = companyName;
        this.ads = new ComparableVector<>(100);
    }

    public String getCompanyName() {
        return companyName;
    }

    public ComparableVector<Ad> getAds() {
        return ads;
    }

    // Add sorted ads
    public void postAd (String content, int ageLimit, boolean paid, int timestamp){
        Ad ad = new Ad(content, this.companyName, ageLimit, paid, timestamp);
        ads.addSorted(ad);
    }

    public void printWall (){
        for (int i = 0; i < ads.size(); i++){
            System.out.println(ads.get(i));
        }
    }

    @Override
    public String toString() {
        return "Corporate Profile: " + companyName;
    }
}
