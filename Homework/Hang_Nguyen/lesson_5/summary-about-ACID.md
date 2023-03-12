<b><h3>A Transaction</h3></b>

*A transaction is a way for an application to group several reads and writes together into a logical unit.*

ACID supports for transactions and Almost all relational databases today, and some nonrelational databases, support transactions.

A key feature of a transaction is that it can be aborted and safely retried if an error occurred. ACID databases are based on this philosophy.

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

<h3><b>Isolation levels</b> built based on race conditions as below:</h3>

*Dirty Reads*: One client reads another client’s writes before they have been committed

*Dirty Writes*: One client overwrites data that another client has written, but not yet committed

*Read skew (nonrepeatable reads)*: A client sees different parts of the database at different points in time.

*Lost updates*: Two clients concurrently perform a read-modify-write cycle. One overwrites the other’s write without incorporating its changes, so data is lost.

*Write skew*: A transaction reads something, makes a decision based on the value it saw, and writes the decision to the database. However, by the time the write is made, the premise of the decision is no longer true.

*Phantom reads*: A transaction reads objects that match some search condition. Another client makes a write that affects the results of that search.

***Algorithm to prevent those isolation levels:***
1. <b>Read Committed:</b>
- use case: dirty-reads, dirty-writes
- default setting: Oracle 11g, PostgreSQL, SQL Server 2012, MemSQL, and many other databases
- *how to implement*:

++ to prevent dirty-writes by using row-level locks, A transaction must hold the lock until it is committed or aborted.

++ to prevent dirty-reads, the database remembers both the old committed value and the new value set by the transaction that currently holds the write lock. Provide old commited value when reading.

2. <b>Snapshot isolation (repeatable read)</b>
- use case: read skew, lost-updates, phantom reads
- the transaction sees all the data that was committed in the database at the start of the transaction.
- *how to implement*:

++ readers never block writers, and writers never block readers.

++ implement by using created_by and deleted_by to capture snapshot

3. <b>Serializable Snapshot Isolation</b>
- use case: write skew
- *how to implement:*
- When the transaction wants to commit, the database checks whether any of the ignored writes have now been committed. If so, the transaction must be aborted.
- When a transaction writes to the database, it must look in the indexes for any other transactions that have recently read the affected data: it simply notifies the transactions that the data they read may no longer be up to date.