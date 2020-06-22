package com.backend.boiler.plate.database;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.backend.boiler.plate.Utils.Constants;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Bitto Kazi
 */
public class DataLoaderTask implements CustomTaskChange {
	private static final Logger logger = LoggerFactory.getLogger(DataLoaderTask.class);

	private ResourceAccessor resourceAccessor;
	private String companyFileName;
	private String userFileName;
	private static final String ROLE_="ROLE_";

	public void setCompanyFileName(String companyFileName) {
		this.companyFileName = companyFileName;
	}

	public void setUserFileName(String userFileName) {
		this.userFileName = userFileName;
	}

	@Override
	public void execute(Database database) throws CustomChangeException {
		JdbcConnection databaseConnection = (JdbcConnection) database.getConnection();
		try {
			Set<InputStream> streams = resourceAccessor.getResourcesAsStream(userFileName);
			if (streams.size() < 1) {
				throw new CustomChangeException("User data file not found");
			}
			Reader in = new InputStreamReader(streams.iterator().next());
			long roleId = 1L;
			for (String role : Arrays.asList(ROLE_ + Constants.getInstance().ROLE_ADMIN,
					ROLE_ + Constants.getInstance().ROLE_USER)) {
				String sql = "INSERT INTO role(id,name) VALUES(?,?)";
				PreparedStatement statement = databaseConnection.prepareStatement(sql);
				statement.setLong(1, roleId);
				statement.setString(2, role);
				statement.executeUpdate();
				statement.close();
				roleId++;
			}

			Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
			long userId = 1L;
			for (CSVRecord record : records) {
				String sql = "INSERT INTO users (id,first_name,last_name,user_name,password,email,enabled,change_password) VALUES(?,?,?,?,?,?,?,?)";

				PreparedStatement statement = databaseConnection.prepareStatement(sql);
				statement.setLong(1, userId);
				statement.setString(2, record.get("firstName"));
				statement.setString(3, record.get("lastName"));
				statement.setString(4, record.get("username"));
				statement.setString(5, BCrypt.hashpw(record.get("password"), BCrypt.gensalt()));
				statement.setString(6, record.get("email"));
				statement.setBoolean(7, true);
				statement.setBoolean(8, true);

				statement.executeUpdate();
				databaseConnection.commit();
				statement.close();

				for (String role : record.get("roles").split(",")) {
					sql = "insert into user_role (user_id, role_id) VALUES ("
							+ "(select id from users where email='" + record.get("email")
							+ "'), (select id from role where name='" + role + "') )";
					logger.info(sql);
					statement = databaseConnection.prepareStatement(sql);
					statement.executeUpdate();
					databaseConnection.commit();
					statement.close();
				}
				userId++;
			}
			in.close();

		} catch (Exception e) {
			throw new CustomChangeException(e);
		}
	}
	
	/**
	 * @Override method
	 */
	@Override
	public String getConfirmationMessage() {
		return null;
	}
	
	/**
	 * @Override method
	 */
	@Override
	public void setUp() throws SetupException {
	//	throw new UnsupportedOperationException();
	}

	/**
	 * @Override method
	 */
	@Override
	public void setFileOpener(ResourceAccessor resourceAccessor) {
		this.resourceAccessor = resourceAccessor;
	}
	
	/**
	 * @Override method
	 */
	@Override
	public ValidationErrors validate(Database database) {
		return null;
	}
}