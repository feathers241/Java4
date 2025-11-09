package DaoImpl;

import java.util.List;

import Dao.UserDao;
import Entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

}
