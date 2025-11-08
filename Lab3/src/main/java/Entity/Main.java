package Entity;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		UserManager um = new UserManager();
//		um.findAll();
//		um.findById("admin01");
//		um.findEmail("%@example.com", false);
//		um.findPage(2,3);
		
		List<Users> list = um.findpage2(2, 3);
		for(Users u : list) {
			String role = u.isAdmin()?"admin":"Users";
			System.out.printf("%-25s| %-25s | %s\n", u.getFullname(),u.getEmail(),role);
		}
	}
}
