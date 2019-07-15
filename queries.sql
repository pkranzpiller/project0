select * from account;
select * from customer;

select account.fname, account.lname, customer.balance from account join customer on customer.id=account.id;

delete from customer where id=5;
delete from account where id=8;