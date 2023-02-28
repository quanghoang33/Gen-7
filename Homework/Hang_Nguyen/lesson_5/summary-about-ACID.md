<h3>ACID stands for:</h3>

**Atomicity**
- The ability to abort a transaction on error and have all writes from that transaction discarded

*if a transaction was aborted, the application can be sure that it didn’t change anything, so it can safely be retried*

**Consistency**
- Database being in a “good state”
- No constraint violation is triggered (such as: foreign key, unique,...)

*However, in general, the application defines what data is valid or invalid, the database only stores it.*

**Isolation**
- Concurrently executing transactions are isolated from each other: they cannot step on each other’s toes
- The database ensures that when the transactions have committed, the result is the same even though in reality they may have run concurrently

*In practice, serializable isolation is rarely used, because it carries a performance penalty.*

**Durability**
- A successful transaction must permanently change the state of a system
- The promise that once a transaction has committed successfully, any data it has written will not be forgotten, even if there is a hardware fault or the database crashes

*a database must wait until writes or replications are completed before reporting a transaction as successfully committed*

*however, perfect durability does not exist, if all your hard disks and all your backups are destroyed at the same time, there’s obviously nothing your database can do to save you*