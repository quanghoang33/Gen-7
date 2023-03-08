<h3>Transaction</h3>

*A transaction is a way for an application to group several reads and writes
together into a logical unit.*

ACID supports for transactions and Almost all relational databases today, and some nonrelational databases, support transactions.


<h3>ACID stands for:</h3>

**Atomicity**
- The ability to **abort** a transaction on error and have all writes from that transaction discarded
- giving an all-or-nothing guarantee

*if a transaction was aborted, the application can be sure that it didn’t change anything, so it can safely be retried*

**Consistency**
- Database being in a “good state”
- **No constraint violation** is triggered (such as: foreign key, unique,...)

*However, in general, the application defines what data is valid or invalid, the database only stores it.*

**Isolation**
- Concurrently running transactions **shouldn’t interfere with each other**. For example, if one transaction makes several writes, then another transaction should see either all or none of those writes, but not some subset.
- The database ensures that when the transactions have committed, **the result is the same** even though in reality they may have run concurrently

*In practice, serializable isolation is rarely used, because it carries a performance penalty.*

**Durability**
- A successful transaction must **permanently change the state of a system**
- The promise that once a transaction has committed successfully, any data it has written will not be forgotten, even if there is a hardware fault or the database crashes

*a database must wait until writes or replications are completed before reporting a transaction as successfully committed*

*however, perfect durability does not exist, if all your hard disks and all your backups are destroyed at the same time, there’s obviously nothing your database can do to save you*