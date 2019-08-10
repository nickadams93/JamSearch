package auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jamsearch.auth.model.Post;
import com.jamsearch.auth.model.Role;
import com.jamsearch.auth.model.User;
import com.jamsearch.auth.repository.UserRepository;

public class UserRepositoryTest {
	
	@Autowired
	UserRepository userRepository;
	
	private static Stream<Arguments> userData() {
		
		List<User> friendsList = new ArrayList<>();
		User friend = new User();
		friend.setUsername("friend");
		friendsList.add(friend);
		
		return Stream.of(
				Arguments.of("Lucas", "Piano", "245 Plum Street", "77777", friendsList, "password2", "test2@gmail.com"),
				Arguments.of("Bob", "Guitar", "111 Jacktar Street", "90887", friendsList, "password88", "test@yahoo.com"),
				Arguments.of("Nick Adams", "Guitar", "111 Plum Street", "68046", friendsList, "password", "test@gmail.com"));
	}
	
	@ParameterizedTest
	@MethodSource("userData")
	public void testUserRepo(String name, String instruments, String address, String zipcode, List<User> friendsList,
		String password, String emailAddress) {
		User testUser = new User();
		testUser.setUsername(name);
		testUser.setAddress(address);
		testUser.setInstruments(instruments);
		testUser.setEmailAddress(emailAddress);
		testUser.setPassword(password);
		testUser.setFriends(friendsList);
		testUser.setZipCode(zipcode);
		testUser.setPosts(new ArrayList<Post>());
		testUser.setRoles(new TreeSet<Role>());
		
		
		
		Assertions.assertEquals(testUser.getAddress(), address);
		Assertions.assertEquals(testUser.getInstruments(), instruments);
		Assertions.assertEquals(testUser.getUsername(), name);
		Assertions.assertEquals(testUser.getZipCode(), zipcode);
		Assertions.assertEquals(testUser.getFriends().get(0).getUsername(), friendsList.get(0).getUsername());
		Assertions.assertEquals(testUser.getPassword(), password);
		Assertions.assertEquals(testUser.getEmailAddress(), emailAddress);
	}
}
