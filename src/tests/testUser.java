package tests;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testUser {

    @Test
    public void testUsers(){
        User user = new User("1","2","3");
        String resultName = user.getName();
        String resultEmail =  user.getEmail();
        String resultPassword = user.getPassword();

        assertEquals("1",resultName);
        assertEquals("2",resultEmail);
        assertEquals("3",resultPassword);

    }
}
