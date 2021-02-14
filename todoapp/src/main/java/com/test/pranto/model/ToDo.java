package com.test.pranto.model;

public class ToDo {

	public static String REF = "ToDo"; //$NON-NLS-1$
	public static String PROP_ID = "id"; //$NON-NLS-1$
	public static String PROP_CREATE_DATE = "createDate"; //$NON-NLS-1$
	public static String PROP_LAST_UPDATE_TIME = "lastUpdateTime"; //$NON-NLS-1$
	public static String PROP_DELETED = "deleted"; //$NON-NLS-1$
	public static String PROP_TODO_DATE = "toDoDate"; //$NON-NLS-1$
	public static String PROP_ITEM_NAME = "itemName"; //$NON-NLS-1$
	public static String PROP_DESCRIPTION = "description"; //$NON-NLS-1$

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

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(java.util.Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public java.lang.Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(java.lang.Boolean deleted) {
		this.deleted = deleted;
	}

	public java.util.Date getToDoDate() {
		return toDoDate;
	}

	public void setToDoDate(java.util.Date toDoDate) {
		this.toDoDate = toDoDate;
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

	@Override
	public String toString() {
		return itemName;
	}

}
