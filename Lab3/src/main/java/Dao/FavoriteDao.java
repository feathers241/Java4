package Dao;

import java.util.List;

import Entity.Favorite;
import Entity.Users;

public interface FavoriteDao extends CrudDao<Favorite,String> {
	List<Favorite> findByUserId(String userId);
	List<Object[]> tenmostliked();
	
}
