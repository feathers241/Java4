package DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Dao.HistoryDao;
import Entity.History;
import Entity.Video;

public class HistoryDaoImpl implements HistoryDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	
	@Override
	public List<History> findall() {
		String sql = "select h from History h";
		TypedQuery<History> query = em.createQuery(sql,History.class);
		return query.getResultList();
	}

	@Override
	public History findById(String id) {
		return em.find(History.class, id);
	}

	@Override
	public void create(History user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(History user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(History user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public History findIntegerId(int id) {
		String sql = "select h from History h where id = :id";
		TypedQuery<History> query = em.createQuery(sql,History.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

	@Override
	public List<History> findVideosByPage(int page, int pageSize) {
		String jpql = "SELECT v FROM History v ORDER BY v.id";
	    TypedQuery<History> query = em.createQuery(jpql, History.class);

	    int offset = (page - 1) * pageSize;

	    query.setFirstResult(offset);
	    query.setMaxResults(pageSize);

	    return query.getResultList();
	}

}
