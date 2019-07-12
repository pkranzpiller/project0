select * from account;
select * from customer;

select account.fname, account.lname, customer.balance from account join customer on customer.id=account.id;