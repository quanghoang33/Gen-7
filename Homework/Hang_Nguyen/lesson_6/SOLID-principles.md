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
| shipping: Shipping   |    getCost()
| getShippingCost()  //return shipping.getCost() |