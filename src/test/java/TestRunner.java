import classes.*;
import helper.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.Random;
public class TestRunner {
    private static final Logger testLogger = LogManager.getLogger(TestRunner.class);
    public static final ArrayList<User> USERS = Helper.jsonUsersToArrayList("TestData\\userCredentials.json");
    public static final ArrayList<Network> NETWORKS = Helper.jsonNetworkToArrayList("TestData\\networks.json");
    public static final ArrayList<Account> ACCOUNTS = Helper.jsonAccountToArrayList("TestData\\ACCOUNTSDetails.json");
    @ParameterizedTest
    @MethodSource("getUsersList")
    @DisplayName("User should get a success response when using a valid api key")
    public void FCT_01(User currentUser){
        testLogger.info("- - - - - - START TEST CASE FCT_01-" +(USERS.lastIndexOf(currentUser)+1)+" - - - - - -");
        boolean successResponse = Session.getInstance().requestToken_Sad(currentUser, testLogger);
        Assertions.assertFalse(successResponse, "Response was not successful");
        testLogger.info("- - - - - - - - - TEST CASE END - - - - - - - - \n");
    }
    @ParameterizedTest
    @MethodSource("getUsersList")
    @DisplayName("User should get an session_id and a success response when a request token is validated")
    public void FCT_02(User currentUser){
        testLogger.info("- - - - - - START TEST CASE FCT_02-" +(USERS.lastIndexOf(currentUser)+1)+" - - - - - -");
        Session.getInstance().requestToken_Happy(currentUser, testLogger);
        Session.getInstance().validateRequestToken(currentUser, testLogger);
        boolean successResponse = Session.getInstance().createSessionID(currentUser, testLogger);
        Assertions.assertAll(
                () -> Assertions.assertTrue(successResponse, "Response was not successful"),
                () -> Assertions.assertNotNull(Session.getInstance().getSession_id(), "session id is null")
        );
        testLogger.info("- - - - - - - - - TEST CASE END - - - - - - - - \n");
    }
    @ParameterizedTest
    @MethodSource("getUsersList")
    @DisplayName("User should get their username from their account details when session is successfully created")
    public void FCT_03(User currentUser){
        testLogger.info("- - - - - - START TEST CASE FCT_03-" +(USERS.lastIndexOf(currentUser)+1)+" - - - - - -");
        Session.getInstance().requestToken_Happy(currentUser, testLogger);
        Session.getInstance().validateRequestToken(currentUser, testLogger);
        Session.getInstance().createSessionID(currentUser, testLogger);
        int accountIdx = Account.requestAccountDetails(Session.getInstance(), currentUser, ACCOUNTS, testLogger);
        testLogger.trace("\"username\": \"" + ACCOUNTS.get(accountIdx).getUsername()+"\"");
        Assertions.assertEquals(currentUser.getUsername(), ACCOUNTS.get(accountIdx).getUsername(), "usernames don't match");
        testLogger.info("- - - - - - - - - TEST CASE END - - - - - - - - \n");
    }

    @ParameterizedTest
    @MethodSource("getUsersList")
    @DisplayName("Users with valid accounts should be able to mark movies as \"Favorite\"")
    public void FCT_04(User currentUser){
        testLogger.info("- - - - - - START TEST CASE FCT_04-" +(USERS.lastIndexOf(currentUser)+1)+" - - - - - -");
        Session.getInstance().requestToken_Happy(currentUser, testLogger);
        Session.getInstance().validateRequestToken(currentUser, testLogger);
        Session.getInstance().createSessionID(currentUser, testLogger);
        int accountIdx = Account.requestAccountDetails(Session.getInstance(), currentUser, ACCOUNTS, testLogger);
        boolean successResponse = Movie.markMovieAsFavorite("requestBodyForFavorite.json",
                                                            currentUser.getApi_key(),
                                                            Session.getInstance().getSession_id(),
                                                            ACCOUNTS.get(accountIdx).getId(),
                                                            testLogger) ;
        Assertions.assertTrue(successResponse, "Unsuccessful response");
        testLogger.info("- - - - - - - - - TEST CASE END - - - - - - - - \n");
    }
    @ParameterizedTest
    @MethodSource("getNetworksList")
    @DisplayName("User should get the correct name of a network when searching by its id")
    public void FCT_05(Network currentNetwork){
        testLogger.info("- - - - - - START TEST CASE FCT_05-" + (NETWORKS.lastIndexOf(currentNetwork)+1) +" - - - - - -");
        int idx = new Random().nextInt(USERS.size());
        User someUser = USERS.get(idx);
        String api_key = someUser.getApi_key();
        String networkNameObtained = Network.searchNetworkById(api_key, currentNetwork, testLogger);
        Assertions.assertEquals(currentNetwork.getName(),networkNameObtained);
        testLogger.info("- - - - - - - - - TEST CASE END - - - - - - - - \n");
    }
    private static ArrayList<User> getUsersList(){
        return USERS;
    }
    private static ArrayList<Network> getNetworksList(){
        return NETWORKS;
    }
}