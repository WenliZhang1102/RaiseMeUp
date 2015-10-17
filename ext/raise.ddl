CREATE TABLE Pet (
	petid integer primary key AUTOINCREMENT not null,
	type text not null,
	variant text not null, /* pl szurke cat, sarga cat */
	name text not null,
	hunger integer not null,
	energy integer not null,
	fun integer not null,
	hygiene integer not null,
	age integer not null,
	image text,
	userid integer not null,
	FOREIGN KEY (userid) REFERENCES User (userid)
);

CREATE TABLE Food (
	foodid integer primary key AUTOINCREMENT not null,
	name text not null,
	price integer not null,
	valuedog integer not null,
	valuecat integer not null,
	valuefish integer not null,
	valuepenguin integer not null,
	image text
);

CREATE TABLE FoodOwned (
	petid integer not null,
	foodid integer not null,
	FOREIGN KEY (petid) REFERENCES Pet (petid),
	FOREIGN KEY (foodid) REFERENCES Food (foodid)
);

CREATE TABLE Upgrade (
	upgradeid integer primary key AUTOINCREMENT not null,
	name text not null,
	price integer not null,
	property text not null,	/* energy */
	species text not null,	/* pl dog */
	value integer not null,	/* mennyire jo */
	image text
);

CREATE TABLE UpgradeOwned (
	petid integer not null,
	upgradeid integer not null,
	FOREIGN KEY (petid) REFERENCES Pet (petid),
	FOREIGN KEY (upgradeid) REFERENCES Upgrade (upgradeid)
);

CREATE TABLE User (
	userid integer primary key AUTOINCREMENT not null,
	email text not null,
	username text not null,
	password text not null /* tettem egy ilyet is hozzá pluszba */
);

CREATE TABLE Job (
	id integer primary key AUTOINCREMENT not null,
	impactenergy integer not null,
	impacthunger integer not null,
	impacthygiene integer not null,
	impactfun integer not null,
	title text not null,
	image text,
	client text not null, /* mondjuk ez érdekes h mi legyen a típusa */
	message text not null,
	petid integer not null,
	FOREIGN KEY (petid) REFERENCES Pet (petid)
);