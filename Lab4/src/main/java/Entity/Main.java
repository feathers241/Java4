package Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

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
		UserDao udao = new UserDaoImpl();
		VideoDao vdao = new VideoDaoImpl();
		FavoriteDao fdao = new FavoriteDaoImpl();
		ShareDao sdao = new ShareDaoImpl();
		
		for(Users user : udao.findall()) {
			System.out.println(user);
		}
		
	}
}
