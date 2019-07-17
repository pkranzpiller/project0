select * from users;
select * from accounts;

--display customer username and balance
select users.username, accounts.balance from users left join accounts on users.id=accounts.primaryUserId;
--display full user and account information
select * from users left join accounts on users.id=accounts.primaryUserId;

select permission from users where id=1;
update accounts set status = 'open' where accounts.primaryUserId = 4;
update accounts set balance = 100 where accounts.primaryUserId = 1;
