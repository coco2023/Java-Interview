# MySQL(*New)
https://www.testgorilla.com/blog/mysql-interview-questions/

JDBC(*New)
https://www.softwaretestinghelp.com/jdbc-interview-questions/

https://www.interviewbit.com/jdbc-interview-questions/


# MySQL
MySQL data directory： `/var/lib/mysql`

MySQL_connect and MySQL_pconnect
- MySQL_connect opens a page any time someone loads it
- MySQL_pconnect will open a persistent connection to a database. MySQL_pconnect is ideal for websites with high traffic because it mitigates the requirement to consistently open and close a connection each time someone loads a page

table’s content in MySQL

primary keys: to identify the individual rows of a table

Candidate key: is a single key or a group of multiple keys that uniquely identify rows in a table. It is used to ensure that there are no duplicate or ambiguous records in a table

DDL(data definition language): 
- DDL commands can be used to handle database schemas. DDL commands also define the way the data should exist in a database
- Unlike data manipulation language (DML) commands that are used for data modification purposes, DDL commands are used for altering the database structure such as creating new tables or objects along with all their attributes (data type, table name, etc.).
  -  **database schema** defines how data is organized within a relational database; A schema in an SQL server is a collection of unlimited database objects linking to a database system. It includes tables, functions, views, and indexes. 

DML(data manipulative language): another example of the main categories of SQL commands
- helps engineers handle operations or complete data manipulations
```
SELECT * FROM employees;

INSERT INTO employees
  VALUES
  ('Lysandra','Reeves','1-212-759-3751','New York',10018),
  ('Michael','Arnett','1-650-230-8467','San Francisco',94116);
```

DCL(data control languages)
- DCL helps engineers handle grant and permissions operations and give access authorization to different parts of the database
```
GRANT <privileges> ON <object name>
TO <user/roles>

//Gives access to SELECT and INSERT in the database to Icona
GRANT SELECT, INSERT
ON product_details
TO Icona;        

//Gives all privilege access to anybody working with the database
GRANT ALL
ON product_stock
TO PUBLIC;       

//Retains access from Icona to SELECT and INSERT
REVOKE SELECT, INSERT
ON product_details
FROM Icona;      

refer: [<a></a>](https://www.scaler.com/topics/dcl/)
```

federated table
- federated table 
  - points to other tables that are on the database
  - point to tables on other servers
  - federated tables are made of remote servers, database tables, and associated tables
```
CREATE TABLE fed_orders (
...,
shipname  TYPE(LEN),
freight  TYPE(LEN),
...,
)
ENGINE=FEDERATED
DEFAULT CHARSET=latin1
CONNECTION='fedMySQL/orders';

https://www.cdata.com/kb/tech/mysql-odbc-mysql-federated-tables.rst
```

InnoDB:
- InnoDB tables arrange your data on disk to optimize queries based on primary keys

How would you back up databases in MySQL?
- phpMyAdmin 
- Choose the database they want to back up by selecting the database name; hlight all the tables required and select the **export option**; Select the required export option and save the output accordingly

join two tables:
- Cross join
- Inner join
- Right join
- Left join

Create a New User and Grant Permissions in MySQL
```
CREATE USER 'sammy'@'localhost' IDENTIFIED BY 'password';

GRANT PRIVILEGE ON database.table TO 'username'@'host';

GRANT CREATE, ALTER, DROP, INSERT, UPDATE, DELETE, SELECT, REFERENCES, RELOAD on *.* TO 'sammy'@'localhost' WITH GRANT OPTION;
```

# JDBC
JDBC: perform DB operations in Database from Java application. 

JDBC driver: is used to make the Java application to interact with the Database

JDBC-ODBC bridge: an interface between the client and the DB server. The client should put the JDBC-ODBC driver in it. The database ought to support the ODBC driver.

Components of JDBC:
- JDBC API
- JDBC Driver Manager
- JDBC Test Suite
- JDBC – ODBC Bridge

steps to connect with JDBC: **Import** package, **Load** driver, Establish **connection**, **Creation and execution** of the statement, Retrieve **results**, **Close** connection

DriverManager in JDBC: 
- an in-built class that is present in the `java.sql` package
- a mediator between the Java Application and DB
- register or load the driver with DriverManager

execute(), executeQuery(), executeUpdate()
- SELECT query:
  - only SELECT Query: executeQuery()
  - only a non-SELECT query: executeUpdate()
  - both SELECT and non-SELECT queries: execute()
- returns:
  - resultSet object: executeQuery()
  - integer value which represents the no. of affected rows: executeUpdate()
  - It returns a Boolean value: TRUE – returns a resultSet object; FALSE – returns an int value or nothing

Stored Procedures in JDBC: A group of SQL queries that are executed as a single unit to perform a particular task is known as a Stored Procedure. execute the SQL Stored procedures through the `CallableStatement` interface
```
prepareCall() - CallableStatement 
```

batch processing: Batch processing is the process of executing several SQL statements in one transaction. It can reduce communication time and increase performance.

ResultSet interface: ResultSet interface is used to store the output data after the SQL query execution. The object of ResultSet maintains the **cursor point** at the result data. the cursor points before the first row of the result data.
```
Statement stmnt1 = conn.createStatement();
ResultSet resultset = stmnt1.executeQuery(“Select * from EMPLOYEE”);

PreparedStatement pstmnt1 = conn.prepareStatement(insert_query);
ResultSet resultset = pstmnt1.executeQuery(“Select * from EMPLOYEE”);
```

Concurrency modes in ResultSet
- ResultSet.CONCUR_READ_ONLY: A read-only option is available. Updation is not possible.
- ResultSet.CONCUR_UPDATABLE: Updation is possible.

check whether the database supports the concurrency mode - `supportsResultSetConcurrency() `

get the data of the particular row from the resultset - `relative() ` It will move the cursor to the given row either in a forward or in a backward direction from the current row.

getter and setter methods in ResultSet

**ResultSetMetaData** interface - gives information about the Database we are using. Each ResultSet object has been associated with one ResultSetMetaData object. This object will have the details of the properties of the columns like datatype of the column, column name, the number of columns in that table, table name, schema name, etc
```
PreparedStatement pstmntobj = conn.prepareStatement(insert_query);
ResultSet resultsetobj = pstmntobj.executeQuery(“Select * from EMPLOYEE”);
ResultSetMetaData rsmd obj= resultsetobj.getMetaData();                     // getMetaData()
```

ACID: atomicity, consistency, isolation, and durability

change the auto-commit mode value - By default, the value of AutoCommit is TRUE
- setAutoCommit()

Commit and Rollback
- Commit in SQL is a **transaction control language**; used to permanently save the changes done in the transaction in tables/databases; The database cannot regain its previous state after its execution of commit. 

https://www.javatpoint.com/commit-vs-rollback-in-sql

savepoint: create checkpoints in a transaction, and it allows us to perform a rollback to the specific savepoint. Once the transaction is committed or rolled backed, the savepoint that has been created for a transaction will be automatically destroyed and becomes invalid.
- setSavepoint(): create Savepoint; can use `rollback()` method to undo
- releaseSavepoint(): remove the given savepoint

SQLException: SQLNonTransientException, SQLTransientException, SQLRecoverableException



