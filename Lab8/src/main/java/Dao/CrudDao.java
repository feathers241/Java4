package Dao;

import java.util.List;

public interface CrudDao <X,Y> {
	List<X> findall();
	X findById(Y id);
	void create (X user);
	void delete (X user);
	void update (X user);
}
