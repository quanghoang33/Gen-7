<h1>SOLID stand for:</h1>

<b><h2>Single Responsibility Principle</h2></b>

**A class should have just one reason to change.**

*The main goal of this principle is reducing complexity.*

For example:

> BEFORE

In *Employee* class, this class should only manage employee data and behavior of printing timestamp seems not be related and might be changed overtime.

| Employee      | 
| ----------- |
| name: String   |  
| getName()    | 
| printTimeStampReport()   | 

> AFTER

Solve the problem by moving the behavior related to printing timesheet reports into a separate class. This change lets you move other report-related stuff to the new class.

| Employee      | TimeSheetReport      | 
| ----------- | ----------- |
| name: String   |  printTimeStampReport()   | 
| getName()    | |

<b><h2>Open/Closed Principle</h2></b>

**Classes should be open for extension but closed for modification.**

*The main idea of this principle is to keep existing code from breaking when you implement new features.*

- ***Open*:** A class is open if you can extend it, produce a subclass and do whatever you want with it (add fields, new methods, override base behaviors, etc...)
- ***Close:*** A class is closed if it’s 100% ready to be used by other classes, its interface is clearly defined and won’t be changed in the future.

For example:

> BEFORE

you have to change the *Order* class whenever you add a new shipping type to the app.

| Order      | 
| ----------- |
| shipping: String //shipping can be ["air", "ground",...]   |
| getShippingCost()   | 

> AFTER

Solve the problem by applying the *Shipping* interface. And those shipping type such Air or Ground, ... or new type in future can be implemented by this interface.

| Order      | interface Shipping      | 
| ----------- | ----------- |
| shipping: Shipping   |    getCost()   |
| getShippingCost()  //return shipping.getCost() |

<b><h2>Liskov Substitution Principle</h2></b>

*Objects of a superclass should be replaceable with objects of its subclasses without breaking the application*

The substitution principle is a set of checks that help predict whether a subclass remains compatible with the code that was able to work with objects of the superclass.

For example:

Imagine you had *SetWidth* and *SetHeight* methods on your *Rectangle* base class; this seems perfectly logical. However if your *Rectangle* reference pointed to a *Square*, then *SetWidth* and *SetHeight* doesn't make sense because setting one would change the other to match it. 
In this case *Square* fails the Liskov Substitution. Test with *Rectangle* and the abstraction of having *Square* inherit from *Rectangle* is a bad one.

| Rectangle      | Square extends Rectangle      | 
| ----------- | ----------- |
| setWidth()  |    setWidth() // width should be equal to heigh  |
| setHeigh() |  setHeigh() // heigh should be equal to width     |

<b><h2>Interface Segregation Principle</h2></b>

*Clients shouldn’t be forced to depend on methods they do not use.*

Try to make your interfaces narrow enough that client classes don’t have to implement behaviors they don’t need.

For example:

> BEFORE

You design an interface coverd the full set of cloud services and features of AWS. When it came to implementing support for another provider, some methods describe features that other cloud providers just don’t have.

| interface CloudProvider      | Amazone implements CloudProvider      | DropBox implements CloudProvider      | 
| ----------- | ----------- | ----------- |
| storeFile(name)       |    storeFile(name)        | storeFile(name)   |
| getFile(name)         |  getFile(name)            | getFile(name)      |
| listServers(region)   |  listServers(region)      | listServers(region) //not supported     |

> AFTER

The better approach is to break down the interface into parts.

| interface CloudHostingProvider      | interface CDNProvider      | interface CloudStorageProvider      | 
| ----------- | ----------- | ----------- |
| createServer(region)  |    getCDNAddress()   | storeFile(name)   |
| listServers(region)   |                      | getFile(name)      |
|                       |                      |                    |

| Amazon implements CloudHostingProvider, CDNProvider, CloudStorageProvider      | DropBox implements CloudStorageProvider      | 
| ----------- | ----------- |
|  storeFile(name)              | storeFile(name)   |
|  getFile(name)                | getFile(name)      |
|  createServer(region)         |                   |
|  listServers(region)          |                   |
|  getCDNAddress()              |                   |

*Note: Remember that the more interfaces you create, the more complex your code becomes. Keep the balance.*

<b><h2>Dependency Inversion Principle</h2></b>

*High-level classes **shouldn’t** depend on low-level classes. Both should depend on abstractions.*

*Abstractions **shouldn’t** depend on details. Details should depend on abstractions.*

**Low-level classes:** implement basic operations such as working with a disk, transferring data over a network, connecting to a database, etc.

**High-level classes:** contain complex business logic that directs low-level classes to do something.

For example:

> BEFORE

A high-level BudgetReport depends on low-level MySQLDatabase

| BudgetReport //high-level      | MySQLDatabase //low-level      | 
| ----------- | ----------- |
| - database:  MySQLDatabase    |                |
| open(date)                    |    insert() |
| save()                        |    update()     |
|                               |    delete()     |

> AFTER

You can fix this problem by creating a high-level interface that describes read/write operations and making the report class use that interface instead of the low-level class.

| interface Database      | BudgetReport      | 
| ----------- | ----------- |
| delete()          |    database: Database     |
| update()          |    insert()       |
| delete()          |    update()       |
|                   |    delete()       |

| MySQL implements Database      | MongoDB implements Database      | 
| ----------- | ----------- |
| insert()          |    insert()       |
| update()          |    update()       |
| delete()          |    delete()       |