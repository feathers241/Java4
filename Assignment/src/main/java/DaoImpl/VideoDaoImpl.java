package DaoImpl;

import java.util.List;

import Dao.VideoDao;
import Entity.Share;
import Entity.Video;
import javax.persistence.*;

public class VideoDaoImpl implements VideoDao{
	
	EntityManagerFactory ef = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = ef.createEntityManager();
	
	@Override
	public List<Video> findall() {
		String sql = "select a from Video a";
		TypedQuery<Video> query = em.createQuery(sql,Video.class);
		List<Video> list = query.getResultList();
		return list;
	}


	@Override
	public Video findById(String id) {
		return em.find(Video.class, id);
	}
	
	

	@Override
	public void create(Video user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Video user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Video user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}


	@Override
	public List<Video> notlike() {
		String sql = "select v from Video v where v.id not in (select f.video.id from Favorite f )";
		TypedQuery<Video> query = em.createQuery(sql,Video.class);
		List<Video> list = query.getResultList();
		return list;
	}


	@Override
	public List<Object[]> sharein2024() {
		String sql = "select s.video.id , s.shareDate from Share s where year(s.shareDate) = 2024 order by s.shareDate asc";
		TypedQuery<Object[]> query = em.createQuery(sql,Object[].class);
		List<Object[]> list = query.getResultList();
		return list;
	}

	public List<Video> findVideosByPage(int page, int pageSize){
	    String jpql = "SELECT v FROM Video v ORDER BY v.id";
	    TypedQuery<Video> query = em.createQuery(jpql, Video.class);

	    int offset = (page - 1) * pageSize;

	    query.setFirstResult(offset);
	    query.setMaxResults(pageSize);

	    return query.getResultList();
	}

	
}
