<b><h3>ACID stands for:</h3></b>

>**Atomicity**
- The ability to **abort** a transaction on error and have all writes from that transaction discarded
- giving an all-or-nothing guarantee

*if a transaction was aborted, the application can be sure that it didn’t change anything, so it can safely be retried*

>**Consistency**
- Database being in a “good state”
- **No constraint violation** is triggered (such as: foreign key, unique,...)

*However, in general, the application defines what data is valid or invalid, the database only stores it.*

>**Isolation**
- Concurrency issues (race conditions) only come into play when one transaction reads/modify data that is concurrently read/modified by another transaction.
- The database ensures that when the transactions have committed, **the result is the same** even though in reality they may have run concurrently
- **serializable isolation** means that the database guarantees that transactions have the same effect as if they ran **serially**. *In practice, serializable isolation is rarely used, because it carries a performance penalty.*

>**Durability**
- A successful transaction must **permanently change the state of a system**
- The promise that once a transaction has committed successfully, any data it has written will not be forgotten, even if there is a hardware fault or the database crashes

*a database must wait until writes or replications are completed before reporting a transaction as successfully committed*

<b><h3>HOWEVER</h3></b>

*perfect durability does not exist, if all your hard disks and all your backups are destroyed at the same time, there’s obviously nothing your database can do to save you*

<b><h3>A Transaction</h3></b>

*A transaction is a way for an application to group several reads and writes together into a logical unit.*

ACID supports for transactions and Almost all relational databases today, and some nonrelational databases, support transactions.

A key feature of a transaction is that it can be aborted and safely retried if an error occurred. ACID databases are based on this philosophy.

<b><h3>Isolation levels</h3></b>
1. <b>Read Committed:</b> 
- to prevent dirty-writes by using row-level locks, A transaction must hold the lock until it is committed or aborted.
- to prevent dirty-reads, the database remembers both the old committed value and the new value set by the transaction that currently holds the write lock. Provide old commited value when reading.
2. <b>Snapshot isolation</b>
- to prevent read skew: each transaction reads from a consistent snapshot of the database, each transaction sees only the old data from a particular point in time.
- 