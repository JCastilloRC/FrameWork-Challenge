import org.testng.Assert;
import org.testng.annotations.Test;
public class TestRunner{
    @Test
    public void firstTest(){
        String myString = "Damn boy";
        Assert.assertNull(myString, "String not null");
    }
}
