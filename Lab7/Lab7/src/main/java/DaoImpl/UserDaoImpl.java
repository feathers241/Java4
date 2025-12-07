package DaoImpl;

import java.util.List;

import Dao.UserDao;
import Entity.Users;
import javax.persistence.*;

public class UserDaoImpl implements UserDao{
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	
	
	@Override
	public List<Users> findall() {
		String sql = "select u from Users u";
		TypedQuery<Users> query = em.createQuery(sql, Users.class);
		List<Users> list = query.getResultList();
		return list;
	}

	@Override
	public Users findById(String id) {
		return em.find(Users.class, id);
	}

	@Override
	public void create(Users user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Users user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void update(Users user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public Users findidemail(String a) {
		if(a.contains("@")) {
			String sql = "select u from Users u where u.email = :value";
			TypedQuery<Users> query = em.createQuery(sql, Users.class);
			query.setParameter("value", a);
			List<Users> list = query.getResultList();
			return list.get(0);
		}else {
			return em.find(Users.class, a);
		}
		
	}

}
