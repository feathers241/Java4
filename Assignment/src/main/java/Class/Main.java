package Class;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Dao.FavoriteDao;
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Favorite;
import Entity.Share;
import Entity.Users;
import Entity.Video;

public class Main {
	public static void main(String[] args) {
		VideoDao vdao = new VideoDaoImpl();
		List<Video> ListVideo = vdao.findall();
		FavoriteDao fdao = new FavoriteDaoImpl();
		ShareDao sdao = new ShareDaoImpl();
		
		List<List<Object>> SHARE = new ArrayList<>();
		for(Share s : sdao.findall()) {
			List<Object> share = new ArrayList<>();
			share.add(s.getUser().getFullname());
			share.add(s.getUser().getEmail());
			share.add(s.getEmails());
			share.add(s.getShareDate());
			SHARE.add(share);
		}
		
	}
}
