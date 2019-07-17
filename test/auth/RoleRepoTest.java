package auth;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.jamsearch.auth.model.Post;
import com.jamsearch.auth.model.Role;
import com.jamsearch.auth.model.User;
import com.jamsearch.auth.repository.UserRepository;

public class RoleRepoTest {
	@Autowired
	UserRepository userRepository;
	
	private static Stream<Arguments> userData() {
		
		List<User> friendsList = new ArrayList<>();
		User friend = new User();
		friend.setUsername("friend");
		friendsList.add(friend);
		
		return Stream.of(
				Arguments.of("Lucas", "Piano", "245 Plum Street", "77777", friendsList, "password2", "test2@gmail.com", "Admin"),
				Arguments.of("Bob", "Guitar", "111 Jacktar Street", "90887", friendsList, "password88", "test@yahoo.com", "User"),
				Arguments.of("Nick Adams", "Guitar", "111 Plum Street", "68046", friendsList, "password", "test@gmail.com", "Trial"));
	}
	
	@ParameterizedTest
	@MethodSource("userData")
	public void testUserRepo(String name, String instruments, String address, String zipcode, List<User> friendsList,
		String password, String emailAddress, String roleName) {
		User testUser = new User();
		testUser.setUsername(name);
		testUser.setAddress(address);
		testUser.setInstruments(instruments);
		testUser.setEmailAddress(emailAddress);
		testUser.setPassword(password);
		testUser.setFriends(friendsList);
		testUser.setZipCode(zipcode);
		
		Role testRole = new Role();
		Set<User> users = new HashSet<>();
		users.add(testUser);
		testRole.setUsers(users);
		testRole.setName(roleName);
		
		
		Assertions.assertTrue(testRole.getUsers().contains(testUser));
		Assertions.assertEquals(testRole.getName(), roleName);
	}
}
