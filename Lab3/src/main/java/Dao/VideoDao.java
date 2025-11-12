package Dao;

import java.util.List;

import Entity.Favorite;
import Entity.Video;

public interface VideoDao extends CrudDao<Video,String> {
	List<Video> notlike();
	List<Object[]> sharein2024();
}
