package Class;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Dao.CategoryDao;
import Dao.FavoriteDao;
import Dao.HistoryDao;
import Dao.ShareDao;
import Dao.UserDao;
import Dao.VideoDao;
import DaoImpl.CategoryDaoImpl;
import DaoImpl.FavoriteDaoImpl;
import DaoImpl.HistoryDaoImpl;
import DaoImpl.ShareDaoImpl;
import DaoImpl.UserDaoImpl;
import DaoImpl.VideoDaoImpl;
import Entity.Category;
import Entity.Favorite;
import Entity.History;
import Entity.Share;
import Entity.Users;
import Entity.Video;

public class Main {
    public static void main(String[] args) {
        String s = "askfdhasdf";
        for(int i = 0 ; i < s.length(); i++) {
        	for(int j = 0; j < 3; j++) {
        		System.out.println(s.charAt(j));
        	}
        }

    }
}

