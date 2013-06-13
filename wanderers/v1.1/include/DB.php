<?php
/**
*
*  @author Tarun Gulati, tarun.gulati1988@gmail.com
*  Project: Wanderers
*  Wrapper for creating a sql database connection object and connecting to a database
*
*/

class DB{
	
	//private connection variables
	private $connection;
	private $table;
	private $host;
	private $username;
	private $password;
	private $dbName;

	/**
	*
	*database class constructor
	*/
	function __construct(){
		$this->host = "localhost";
		$this->username = "root";
		$this->password = "";
		$this->dbName = "testdb";
	}

	/**
	*
	* connect()
	* takes in the host, username, password, db name and table to establish a connection
	*/
	function connect($table){

		 //echo('the host is: ' . $this->host);
		 $this->connection = mysql_connect($this->host, $this->username, $this->password);
		 //check for successful sql connection
		 if (!$this->connection){ 
		 	die('Could not connect to MySQL: ' . mysql_error());
		 }else{
			mysql_select_db($this->dbName, $this->connection);
			$this->table=$table;
			//mysql_close($this->connection) 	
		 }
		
	}

}

?>
