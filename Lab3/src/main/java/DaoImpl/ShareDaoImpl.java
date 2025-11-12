package DaoImpl;

import java.util.List;

import Dao.ShareDao;
import Entity.Favorite;
import Entity.Share;
import javax.persistence.*;
public class ShareDaoImpl implements ShareDao{

	EntityManagerFactory ef = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = ef.createEntityManager();
	
	@Override
	public List<Share> findall() {
		String sql = "select a from Share a";
		TypedQuery<Share> query = em.createQuery(sql,Share.class);
		List<Share> list = query.getResultList();
		return list;
	}


	@Override
	public Share findById(String id) {
		return em.find(Share.class, id);
	}

	@Override
	public void create(Share user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Share user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Share user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}


	@Override
	public List<Object[]> shareinfo() {
		String jpql = """
			    SELECT v.title, COUNT(s.video.id), MIN(s.shareDate), MAX(s.shareDate)
			    FROM Share s 
			    JOIN s.video v 
			    GROUP BY v.title, s.video.id
			""";
		TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}
	
}
