ACID stands for: 

Atomicity: 
- takes individual operations and turns them into an all-or-nothing unit of work
- A transaction must always leave the system in a consistent state, no matter how many concurrent transactions are interleaved at any given time


Consistency:
- constraints are enforced for every committed transaction ( Keys, Data types, Checks and Trigger are successful)
- no constraint violation is triggered




Isolation:
- the benefit of hiding uncommitted state changes from the outside world, as failing transactions shouldn’t ever corrupt the state of the system
- using pessimistic or optimistic locking mechanisms




Durability
- A successful transaction must permanently change the state of a system
- If our system is suddenly affected by a system crash or a power outage, then all unfinished committed transactions may be replayed