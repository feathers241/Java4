package Dao;

import java.util.List;

import Entity.History;
	
public interface HistoryDao extends CrudDao<History,String>{
	History findIntegerId(int id);
	List<History> findVideosByPage(int a, int b);
}
