package Dao;

import java.util.List;

import Entity.Share;

public interface ShareDao extends CrudDao<Share,String>{
	List<Object[]> shareinfo();
}
