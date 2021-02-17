package com.test.pranto.model.base;

import java.lang.Comparable;
import java.io.Serializable;


/**
 * This is an object that contains data related to the TODO table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="TODO"
 */

public abstract class BaseToDo  implements Comparable, Serializable {

	public static String REF = "ToDo";
	public static String PROP_LAST_UPDATE_TIME = "lastUpdateTime";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_TO_DO_DATE = "toDoDate";
	public static String PROP_DELETED = "deleted";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_DATE = "createDate";
	public static String PROP_ITEM_NAME = "itemName";


	// constructors
	public BaseToDo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseToDo (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	 long version;

	// fields
	private java.util.Date createDate;
	private java.util.Date lastUpdateTime;
	private java.lang.Boolean deleted;
	private java.util.Date toDoDate;
	private java.lang.String itemName;
	private java.lang.String description;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  column="ID"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}



	/**
	 * Return the value associated with the column: VERSION_NO
	 */
	public long getVersion () {
		return version;
	}

	/**
	 * Set the value related to the column: VERSION_NO
	 * @param version the VERSION_NO value
	 */
	public void setVersion (long version) {
		this.version = version;
	}




	/**
	 * Return the value associated with the column: CREATE_DATE
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}

	/**
	 * Set the value related to the column: CREATE_DATE
	 * @param createDate the CREATE_DATE value
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}



	/**
	 * Return the value associated with the column: LAST_UPDATE_TIME
	 */
	public java.util.Date getLastUpdateTime () {
		return lastUpdateTime;
	}

	/**
	 * Set the value related to the column: LAST_UPDATE_TIME
	 * @param lastUpdateTime the LAST_UPDATE_TIME value
	 */
	public void setLastUpdateTime (java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}



	/**
	 * Return the value associated with the column: DELETED
	 */
	public java.lang.Boolean isDeleted () {
		return deleted;
	}

	/**
	 * Set the value related to the column: DELETED
	 * @param deleted the DELETED value
	 */
	public void setDeleted (java.lang.Boolean deleted) {
		this.deleted = deleted;
	}


	/**
	 * Custom property
	 */
	public static String getDeletedDefaultValue () {
		return "false";
	}


	/**
	 * Return the value associated with the column: TODO_DATE
	 */
	public java.util.Date getToDoDate () {
		return toDoDate;
	}

	/**
	 * Set the value related to the column: TODO_DATE
	 * @param toDoDate the TODO_DATE value
	 */
	public void setToDoDate (java.util.Date toDoDate) {
		this.toDoDate = toDoDate;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.test.pranto.model.ToDo)) return false;
		else {
			com.test.pranto.model.ToDo toDo = (com.test.pranto.model.ToDo) obj;
			if (null == this.getId() || null == toDo.getId()) return false;
			else return (this.getId().equals(toDo.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}

	public int compareTo (Object obj) {
		if (obj.hashCode() > hashCode()) return 1;
		else if (obj.hashCode() < hashCode()) return -1;
		else return 0;
	}

	public String toString () {
		return super.toString();
	}

	public java.lang.String getItemName() {
		return itemName;
	}

	public void setItemName(java.lang.String itemName) {
		this.itemName = itemName;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}


}