package io.mross;

import io.mross.phonebook.domain.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mross on 9/14/16.
 */
public class UserTests {

  @Test
  public void testStandardizePhoneNumnber() {
    User user = new User("1","Danny Brown","6163452212","Seattle");
    user.standardizePhoneNumber('-');
    String phoneNumber = user.getPhoneNumber();

    assertEquals("616-345-2212",phoneNumber);

    user.setPhoneNumber("5411120989");
    user.standardizePhoneNumber('-');

    phoneNumber = user.getPhoneNumber();

    assertEquals("541-112-0989", phoneNumber);

  }

  @Test
  public void testDifferentDelimeter() {
    User user = new User("1","Danny Brown","6163452212","Seattle");
    user.standardizePhoneNumber('-');
    String phoneNumber = user.getPhoneNumber();

    assertEquals("616-345-2212",phoneNumber);

    user.standardizePhoneNumber('/');
    phoneNumber = user.getPhoneNumber();
    assertEquals("616/345/2212",phoneNumber);

    user.standardizePhoneNumber('*');
    phoneNumber = user.getPhoneNumber();
    assertEquals("616*345*2212",phoneNumber);
  }

  @Test
  public void testRemoveCountryCode() {
    User user = new User("1","Danny Brown","18597542123","Seattle");
    user.standardizePhoneNumber('-');
    String phoneNumber = user.getPhoneNumber();

    assertEquals("859-754-2123",phoneNumber);

    user.setPhoneNumber("1-859-754-2123");
    user.standardizePhoneNumber('-');
    phoneNumber = user.getPhoneNumber();

    assertEquals("859-754-2123",phoneNumber);

  }

}
