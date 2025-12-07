package Class;

import java.util.List;

import Dao.UserDao;
import DaoImpl.UserDaoImpl;
import Entity.Users;

public class Main {
	public static void main(String[] args) {
		UserDao um = new UserDaoImpl();
		for(Users user : um.findall()) {
			System.out.println(user);
		}
	}
}
