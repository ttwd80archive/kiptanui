package bcrypt;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptTest {

	@Test
	public void test() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = "$2a$10$giVIMkXQ3KikUtGBYFCVsuoyfVhNMaqKKV4CF/rt4gHKhASz17iDi";
		assertTrue(encoder.matches("rh23i5ojuq58", encodedPassword));
	}

}
