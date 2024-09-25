# advanceJava

Repository containing hands-on examples and code snippets from my Advanced Java learning journey. Includes one-to-one implementations of key concepts such as Servlets, JSP, JDBC, and more



 Properties properties = new Properties();
properties.load(new FileInputStream("config.properties"));

String dbUrl = properties.getProperty("db.url");
String dbUsername = properties.getProperty("db.username");
String dbPassword = properties.getProperty("db.password");

Class.forName("com.mysql.cj.jdbc.Driver");
con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword); 

Storing sensitive information like the database URL, username, and password directly in the code can lead to security issues. By using a config.properties file, we externalize the configuration, making the application more secure and easier to maintain.
This makes the application adaptable to different environments (development, testing, production) without modifying the source code.
