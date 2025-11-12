package Class;

import java.util.List;

import Entity.Users;

public class Main {
	public static void main(String[] args) {
		UserManager um = new UserManager();
		for(Users user : um.findAll()) {
			System.out.println(user);
		}
	}
}
