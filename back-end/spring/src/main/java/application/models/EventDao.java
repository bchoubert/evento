package application.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class EventDao {
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the contact in the database.
   */
  public void create(Event event) {
    entityManager.persist(event);
    return;
  }
  
  /**
   * Return all the contacts stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<Event> getAll() {
    return entityManager.createQuery("from Event").getResultList();
  }

  /**
   * Return the contact having the passed id.
   */
  public Event getById(long id) {
    return entityManager.find(Event.class, id);
  }

  /**
   * Update the passed contact in the database.
   */
  public void update(Event event) {
    entityManager.merge(event);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
} // class EventDao
