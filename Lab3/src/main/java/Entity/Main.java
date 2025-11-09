package Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Dao.FavoriteDao;
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;

public class Main {
	public static void main(String[] args) {
		FavoriteDao dao = new FavoriteDaoImpl();
		UserDao udao = new UserDaoImpl();
			
		List list = new ArrayList<>();
		for(Favorite fv : dao.findall()) {
			List temp = new ArrayList<>();
			temp.add(fv.getVideo().getTitle());
			temp.add(fv.getUser().getFullname());
			temp.add(fv.getLikeDate());
			list.add(temp);
		}
		System.out.println(list);
		
	}
}
