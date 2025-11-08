package Entity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.query.spi.QueryImplementor;
	

public class UserManager {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	
	public void findAll() {
		String sql = "select u FROM Users u";
		TypedQuery<Users> query = em.createQuery(sql,Users.class);
		List<Users> list = query.getResultList();
		list.forEach(user ->{
			String fullname = user.getFullname();
			boolean admin = user.isAdmin();
			String email = user.getEmail();
			System.out.println(fullname+ " | " + admin + " | " + email);
		});
	}
	
	public void findById(String a) {
		Users user = em.find(Users.class,a);
		String fullname = user.getFullname();
		boolean admin = user.isAdmin();
		System.out.println(fullname+ " | " + admin);
	}
	
	public void create() {
		Users user = new Users("001","1234","LeVanTeo","TEo@gmailmcomn",false);
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();;
		}
	}
	public void update() {
		Users user = em.find(Users.class, "admin01");
		user.setFullname("Nguyễn Văn Tẻo");
		user.setEmail("teonotteo@gmail.com");
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteById() {
		Users user = em.find(Users.class, "admin01");
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
