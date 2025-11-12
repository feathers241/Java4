package DaoImpl;

import java.util.ArrayList;
import java.util.List;

import Dao.FavoriteDao;
import Entity.Favorite;
import Entity.Users;
import javax.persistence.*;

public class FavoriteDaoImpl implements FavoriteDao{
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	
	@Override
	public List<Favorite> findall() {
		String sql = "select f from Favorite f";
		TypedQuery<Favorite> query = em.createQuery(sql,Favorite.class);
		List<Favorite> list = query.getResultList();
		return list;
	}

	@Override
	public Favorite findById(String id) {
		return em.find(Favorite.class, id);
	}
	
	@Override
	public List<Favorite> findByUserId(String userId) {
		String sql = "select f from Favorite f where f.user.id = :id";
		TypedQuery<Favorite> query = em.createQuery(sql,Favorite.class);
		query.setParameter("id",userId);
		List<Favorite> list = query.getResultList();
		return list;
	}

	@Override
	public void create(Favorite user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Favorite user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Favorite user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public List<Object[]> tenmostliked() {
		String sql = "select f.video.id, count(f.video) from Favorite f group by f.video.id order by count(f.video) desc";
		TypedQuery<Object[]> query = em.createQuery(sql,Object[].class);
		query.setMaxResults(10);
		List<Object[]> list = query.getResultList();
		return list;
	}
	
}
