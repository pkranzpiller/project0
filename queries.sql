select * from users;
select * from accounts;

select users.username, accounts.balance from users left join accounts on users.id=accounts.primaryUserId;--display customer username and balance
select * from users left join accounts on users.id=accounts.primaryUserId;--display full user and account information