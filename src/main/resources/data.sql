insert into product( name,description, category, author, price) values( 'Mobile', 'This is Mobile', 'Electronics', null, 15000);
insert into product( name,description, category, author, price) values( 'Book', 'This is Book', 'Books', 'author', 250);
COMMIT;

insert into roles(name) values('USER');
insert into roles(name) values( 'ADMIN');

insert into users( username, firstname, lastname, password, email) values( 'raju', 'fff', 'lll', '$2y$12$i6qxF7bc/exgQW58y5BCdushaiEqxi5QuGJWcZNaC3kMiQjmzX3vK', 'abc@abc.com');