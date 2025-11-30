public interface iSocialNetwork {


    /************************************* PART 1 *******************************************/
    /*
     * Create new user profile with given parameters: username, age.
     *
     * @param username
     *
     * @param age
     *
     */
    public void createUserProfile(String username, int age);

    /*
     * Create new corporate profile with given parameters: companyName.
     *
     * @param companyName
     *
     */
    public void createCorporateProfile(String companyName);

    /*
     * Print the wall of the user.
     *
     * @param username
     *
     */
    public void printWallOf(String username);

    /*
     * Create a new status update of a user.
     *
     * @param username of the author of the status update
     *
     * @param status content
     *
     * @param privacy setting of the status
     *
     * @param ageLimit
     *
     * @param timestamp
     *
     */
    public void postStatus(String username, String status, int privacy, int ageLimit, int timestamp);

    /*
     * Create new ad message.
     *
     * @param companyName of the author of the ad
     *
     * @param ad content
     *
     * @param ageLimit
     *
     * @param paid
     *
     * @param timestamp
     *
     */
    public void postAd(String companyName, String ad, int ageLimit, boolean paid, int timestamp);
}
