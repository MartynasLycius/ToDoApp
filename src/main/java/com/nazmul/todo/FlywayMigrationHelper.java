/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nazmul.todo;

import static org.apache.maven.wagon.PathUtils.password;
import org.flywaydb.core.Flyway;

/**
 *
 * @author nazmul
 */
public class FlywayMigrationHelper {
    
    public static boolean runMigration(String dbURl, String username, String password)
    {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(dbURl, username, password);
        flyway.setBaselineOnMigrate(true);
        flyway.setBaselineDescription("Base migration");
        flyway.setBaselineVersionAsString("0");
        flyway.setLocations("db.migration");
        flyway.setValidateOnMigrate(false);
        int migrate = flyway.migrate();
        System.out.println("Migrated: " + migrate);
        return true;
    }
}
