/*****************************************************************************************************************
 *
 *	 File			 : SequenceStoreGenerator.java
 *
  *****************************************************************************************************************/


package com.opt.common.persistence;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.Table;

import com.opt.common.annotation.PrimaryKey;


/*
 * This class is  SequenceStoreGenerator class   which is used for
 *  Primary key generation  into  db
 */
public abstract class SequenceStoreGenerator {

    private static final String PRIMARY_KEY = "SELECT STRINGKEY FROM DBO.SEQUENCESTORE WHERE TABLENAME = :TABLENAME";
    private static final String PRIMARY_KEY_INSERT = "INSERT INTO DBO.SEQUENCESTORE (TABLENAME, STRINGKEY) VALUES (:TABLENAME, :STRINGKEY)";
    private static final String PRIMARY_KEY_UPDATE = "UPDATE DBO.SEQUENCESTORE SET STRINGKEY = :STRINGKEY WHERE TABLENAME = :TABLENAME";

    protected abstract EntityManager getEntityManager();

    public <T> String getPrimaryKey(final Class<T> entityClass) {
        final String tableName = getTableName(entityClass);
        return generatePrimaryKey(entityClass, tableName);
    }

    public <T> String getPrimaryKey(final Class<T> entityClass, final String offset) {
        String tableName = getTableName(entityClass);
        final StringBuffer table = new StringBuffer();
        table.append(tableName);
        table.append("_");
        table.append(offset);
        tableName = new String(table);
        return generatePrimaryKey(entityClass, tableName);
    }

    private <T> String generatePrimaryKey(final Class<T> entityClass, final String tableName) {
        final Integer length = getLength(entityClass);
        String primaryKey = getNextPrimaryKey(tableName, length);
        primaryKey = format(length, primaryKey);
        editPrimaryKey(tableName, primaryKey);
        return primaryKey;
    }

    private String getNextPrimaryKey(final String tableName, final Integer length) {
        String primaryKey;

        try {
            final Query query = getEntityManager().createNativeQuery(PRIMARY_KEY);
            query.setParameter("TABLENAME", tableName);
            primaryKey = (String) query.getSingleResult();
        } catch (final NoResultException ex) {
            primaryKey = format(length, "0");
            addPrimaryKey(tableName, primaryKey);
        }

        final Integer nextPrimaryKey = Integer.parseInt(primaryKey) + 1;
        return String.valueOf(nextPrimaryKey);
    }

    private void addPrimaryKey(final String tableName, final String primaryKey) {
        final Query query = getEntityManager().createNativeQuery(PRIMARY_KEY_INSERT);
        query.setParameter("STRINGKEY", primaryKey);
        query.setParameter("TABLENAME", tableName);
        query.executeUpdate();
        getEntityManager().flush();
    }

    private void editPrimaryKey(final String tableName, final String primaryKey) {
        final Query query = getEntityManager().createNativeQuery(PRIMARY_KEY_UPDATE);
        query.setParameter("STRINGKEY", primaryKey);
        query.setParameter("TABLENAME", tableName);
        query.executeUpdate();
    }

    private <T> String getTableName(final Class<T> entityClass) {
        final Table table = entityClass.getAnnotation(Table.class);
        return table.name();
    }

    private <T> Integer getLength(final Class<T> entityClass) {
        final PrimaryKey primaryKey = entityClass.getAnnotation(PrimaryKey.class);
        return primaryKey.length();
    }

    private String format(final Integer length, String primaryKey) {
        final String format = new String(new StringBuffer().append("%0").append(length).append("d"));
        final int key = Integer.parseInt(primaryKey);
        primaryKey = String.format(format, key);
        return primaryKey;
    }

}
