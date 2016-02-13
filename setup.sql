DROP TABLE 'Users' IF EXISTS;
CREATE TABLE 'Users' (
    'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
    'username' varchar(30) NOT NULL DEFAULT '',
    'default_view' varchar(40) DEFAULT 'employee',
    PRIMARY KEY ('id')
);

DROP TABLE 'Views' IF EXISTS;
CREATE TABLE 'Views' (
    'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
    'username' varchar(30) NOT NULL DEFAULT '',
    'name' varchar (30) NOT NULL,
    'column_one' varchar(40),
    'column_two' varchar(40),
    'column_three' varchar(40)
    PRIMARY KEY ('id')
);

DROP TABLE 'Modules' IF EXISTS;
CREATE TABLE 'Modules' (
    'id' int(11) unsigned NOT NULL AUTO_INCREMENT,
    'title' varchar (30) NOT NULL,
    'api_endpoint' varchar(30) NOT NULL
)