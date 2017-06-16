package edu.byui.maddldsdj;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class validUserTest {

    private String _email = "hello@gmail.com";
    private String _uid = "4DE789FT542WD";
    private boolean _admin = true;

    private User firstUserConstructor = new User(_email, _uid);
    //private User secondUserConstructor = new User(_email, _uid, _admin);


    @Test
    public void checkUserEmailValid() {
        assertEquals("Email not set;", firstUserConstructor.getUserEmail(), "hello@gmail.com");
        assertEquals("User id not set;", firstUserConstructor.getUserID(), "4DE789FT542WD");
        assertEquals("User isAdmin not set;", firstUserConstructor.isAdmin(), false);
    }

    /*
    @Test
    public void checkConstructor() {
        assertEquals(secondUserConstructor, secondUserConstructor);
        assertEquals("Email not set;", secondUserConstructor.getUserEmail(), "hello@gmail.com");
        assertEquals("User id not set;", secondUserConstructor.getUserID(), "4DE789FT542WD");
        assertEquals("User isAdmin not set;", secondUserConstructor.isAdmin(), true);
    }
*/
}
