package Entity;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;		
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


import org.hibernate.query.spi.QueryImplementor;
	

public class UserManager {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	
	public List<Users> findAll() {
		String sql = "select u FROM Users u";
		TypedQuery<Users> query = em.createQuery(sql,Users.class);
		List<Users> list = query.getResultList();
		return list;
	}
	
	public Users findById(String a) {
		return em.find(Users.class, a);
	}
	
	public void create(Users u) {
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();;
		}
	}
	public void update(Users u) {
		try {
			em.getTransaction().begin();
			em.merge(u);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteById(Users u) {
		Users user = em.find(Users.class, u.getId());
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}
	public void findEmail(String a , boolean b) {
		String sql = "select u FROM Users u where u.email like :search and u.admin = :role";
		TypedQuery<Users> query = em.createQuery(sql,Users.class);
		query.setParameter("search", a);
		query.setParameter("role", b);
		List<Users> list = query.getResultList();
		list.forEach(user ->{
			String fullname = user.getFullname();
			String email = user.getEmail();
			System.out.println(fullname+ " | " + email);
		});
	}
	public void findPage(int a, int b) {
		String sql = "select u from Users u";
		Query query = em.createQuery(sql,Users.class);
		query.setFirstResult(a);
		query.setMaxResults(a*b);
		List<Users> list = query.getResultList();
		list.forEach(user ->{
			String fullname = user.getFullname();
			String email = user.getEmail();
			System.out.println(fullname+ " | " + email);
		});
	}
	public List<Users> findpage2(int a, int b){
		String sql = "select u from Users u";
		TypedQuery<Users> query = em.createQuery(sql,Users.class);
		query.setFirstResult(a);
		query.setMaxResults(a*b);
		return query.getResultList();
	}
}	
