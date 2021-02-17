package com.test.pranto.model.base;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import com.test.pranto.model.dao.ToDoDAO;
import org.hibernate.criterion.Order;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseToDoDAO extends com.test.pranto.model.dao._RootDAO {

	// query name references


	public static ToDoDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static ToDoDAO getInstance () {
		if (null == instance) instance = new ToDoDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.test.pranto.model.ToDo.class;
	}

    public Order getDefaultOrder () {
		return null;
    }

	/**
	 * Cast the object as a com.test.pranto.model.ToDo
	 */
	public com.test.pranto.model.ToDo cast (Object object) {
		return (com.test.pranto.model.ToDo) object;
	}

	public com.test.pranto.model.ToDo get(java.lang.String key)
	{
		return (com.test.pranto.model.ToDo) get(getReferenceClass(), key);
	}

	public com.test.pranto.model.ToDo get(java.lang.String key, Session s)
	{
		return (com.test.pranto.model.ToDo) get(getReferenceClass(), key, s);
	}

	public com.test.pranto.model.ToDo load(java.lang.String key)
	{
		return (com.test.pranto.model.ToDo) load(getReferenceClass(), key);
	}

	public com.test.pranto.model.ToDo load(java.lang.String key, Session s)
	{
		return (com.test.pranto.model.ToDo) load(getReferenceClass(), key, s);
	}

	public com.test.pranto.model.ToDo loadInitialize(java.lang.String key, Session s) 
	{ 
		com.test.pranto.model.ToDo obj = load(key, s); 
		if (!Hibernate.isInitialized(obj)) {
			Hibernate.initialize(obj);
		} 
		return obj; 
	}

/* Generic methods */

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.test.pranto.model.ToDo> findAll () {
		return super.findAll();
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 */
	public java.util.List<com.test.pranto.model.ToDo> findAll (Order defaultOrder) {
		return super.findAll(defaultOrder);
	}

	/**
	 * Return all objects related to the implementation of this DAO with no filter.
	 * Use the session given.
	 * @param s the Session
	 */
	public java.util.List<com.test.pranto.model.ToDo> findAll (Session s, Order defaultOrder) {
		return super.findAll(s, defaultOrder);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param toDo a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.String save(com.test.pranto.model.ToDo toDo)
	{
		return (java.lang.String) super.save(toDo);
	}

	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * Use the Session given.
	 * @param toDo a transient instance of a persistent class
	 * @param s the Session
	 * @return the class identifier
	 */
	public java.lang.String save(com.test.pranto.model.ToDo toDo, Session s)
	{
		return (java.lang.String) save((Object) toDo, s);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param toDo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.test.pranto.model.ToDo toDo)
	{
		saveOrUpdate((Object) toDo);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default the
	 * instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the identifier
	 * property mapping. 
	 * Use the Session given.
	 * @param toDo a transient instance containing new or updated state.
	 * @param s the Session.
	 */
	public void saveOrUpdate(com.test.pranto.model.ToDo toDo, Session s)
	{
		saveOrUpdate((Object) toDo, s);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param toDo a transient instance containing updated state
	 */
	public void update(com.test.pranto.model.ToDo toDo) 
	{
		update((Object) toDo);
	}

	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * Use the Session given.
	 * @param toDo a transient instance containing updated state
	 * @param the Session
	 */
	public void update(com.test.pranto.model.ToDo toDo, Session s)
	{
		update((Object) toDo, s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param id the instance ID to be removed
	 */
	public void delete(java.lang.String id)
	{
		delete((Object) load(id));
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param id the instance ID to be removed
	 * @param s the Session
	 */
	public void delete(java.lang.String id, Session s)
	{
		delete((Object) load(id, s), s);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * @param toDo the instance to be removed
	 */
	public void delete(com.test.pranto.model.ToDo toDo)
	{
		delete((Object) toDo);
	}

	/**
	 * Remove a persistent instance from the datastore. The argument may be an instance associated with the receiving
	 * Session or a transient instance with an identifier associated with existing persistent state. 
	 * Use the Session given.
	 * @param toDo the instance to be removed
	 * @param s the Session
	 */
	public void delete(com.test.pranto.model.ToDo toDo, Session s)
	{
		delete((Object) toDo, s);
	}
	
	/**
	 * Re-read the state of the given instance from the underlying database. It is inadvisable to use this to implement
	 * long-running sessions that span many business tasks. This method is, however, useful in certain special circumstances.
	 * For example 
	 * <ul> 
	 * <li>where a database trigger alters the object state upon insert or update</li>
	 * <li>after executing direct SQL (eg. a mass update) in the same session</li>
	 * <li>after inserting a Blob or Clob</li>
	 * </ul>
	 */
	public void refresh (com.test.pranto.model.ToDo toDo, Session s)
	{
		refresh((Object) toDo, s);
	}


}