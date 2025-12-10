package DaoImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Dao.CategoryDao;
import Entity.Category;

public class CategoryDaoImpl implements CategoryDao{
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
	EntityManager em = factory.createEntityManager();
	@Override
	public List<Category> findall() {
		String sql = "select c from Category c";
		TypedQuery<Category> query = em.createQuery(sql,Category.class);
		return query.getResultList();
	}

	@Override
	public Category findById(String id) {
		return em.find(Category.class, id);
	}

	@Override
	public void create(Category user) {
		try {
			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

	@Override
	public void delete(Category user) {
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(Category user) {
		try {
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
		}catch(Exception e) {
			em.getTransaction().rollback();
		}
	}

}
