package Dao;

import java.util.List;

import Entity.Users;

public interface UserDao extends CrudDao<Users,String>{

	List<Users> findall();
	Users findidemail(String a);
	
}
