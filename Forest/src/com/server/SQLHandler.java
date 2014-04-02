package com.server;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.common.net.PacketCommunicator;
import com.server.player.Account;

public class SQLHandler {

	public static String SQLSERVER = "localhost";
	public static String SQLDATABASE = "forestserver";
	public static SQLHandler INSTANCE;
	public static int SQLSERVER_PORT = 3306;

	public static Connection sqlConnection;

	public SQLHandler() {
		getConnection();
		createTableFromClass("accounts", Account.class);
		Account acc = new Account("Test", "test1");
		saveAccount(acc);
	}

	public static Account loadAccount(String username) {
		String command = "SELECT username, id, password, players FROM accounts";
		Statement statement = null;
		try {
			statement = sqlConnection.createStatement();
			ResultSet rs = statement.executeQuery(command);
			while (rs.next()) {
				String user = rs.getString("username");
				if (user.equals(username)) {
					String pass = rs.getString("password");
					pass = PacketCommunicator.decrypt(Account.encryptionKey, pass);
					Account acc = new Account(user, pass);
					String players = rs.getString("players");
					for (String player : players.split(",")) {
						acc.players.add(player);
					}
					return acc;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveAccount(Account acc) {
		try {
			PreparedStatement ps = sqlConnection
					.prepareStatement(
							"INSERT INTO accounts (username, password, players) VALUES (?, ?, ?)",
							Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, acc.username);
			ps.setString(2, PacketCommunicator.encrypt("abc123", acc.password));
			ps.setString(3, acc.playersToString());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createTableFromClass(String tableName, Class<?> c) {
		String command = "CREATE TABLE " + tableName
				+ " (id int auto_increment primary key, ";
		Field[] fields = c.getFields();
		for (int x = 0; x < fields.length; x++) {
			Field field = fields[x];
			int mod = field.getModifiers();
			if (!Modifier.isTransient(mod)) {
				System.out.println("YES: " + field.getName());
				Class<?> fieldClass = field.getType();
				command += field.getName() + " ";
				if (fieldClass.equals(String.class)) {
					command += "varchar(1050)";
				} else if (fieldClass.equals(int.class)) {
					command += "int";
				} else if (fieldClass.equals(ArrayList.class)) {
					command += "varchar(1050)";
				} else {
					System.out.println("Unknown field: " + fieldClass.getName());
					return;
				}
				if (x + 2 != fields.length) {
					command += ", ";
				}
			}
		}
		command += ");";
		// command =
		// "CREATE TABLE supportContacts	(id int auto_increment primary key, type varchar(20), details varchar(30));";
		System.out.println(command);

		Statement statement = null;
		try {
			statement = sqlConnection.createStatement();
			statement.execute("DROP TABLE IF EXISTS " + tableName);
			statement.executeUpdate(command);
			statement.close();
		} catch (Exception e) {
			System.out.println(command);
			e.printStackTrace();
		}
	}

	public static void createTable(Connection con) throws SQLException {
		String dbName = SQLDATABASE;

		String createString = "create table " + dbName + ".ACCOUNTS "
				+ "(ACC_ID integer NOT NULL, "
				+ "ACC_USERNAME varchar(40) NOT NULL, "
				+ "ACC_PASSWORD varchar(40) NOT NULL, " + "PRIMARY KEY (ACC_ID))";

		Statement stmt = null;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(createString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public static void getConnection() {

		try {
			Properties connectionProps = new Properties();
			connectionProps.put("user", "ForestServer");
			connectionProps.put("password", "test1");
			System.out.println(connectionProps);
			sqlConnection = DriverManager.getConnection("jdbc:mysql://" + SQLSERVER
					+ ":" + SQLSERVER_PORT + "/" + SQLDATABASE, connectionProps);
			System.out.println("Connected to SQL server.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to connect to SQL server.");
		}
	}
}
