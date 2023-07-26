## Introduction
**SOLID** is a mnimonic acronym for five design principles to help developer writting code in understandable, flexible, and maintainable way. It mainly applied in OOP paradigm and first introduced by Robert C. Martin (Uncle Bob) in his book - Design Principles and Design Patterns.

The **SOLID** stands for:
- Single Responsibility Principle
- Open-closed principle
- Liskov substitution principle
- Interface segregation principle
- Dependency inversion principle

## Single Responsibility Principle
This principle states that a class should have only one reason to change. In other words, a class should only perform one task. The purpose of this principle is to ensure that a class is readable, easy to change, test, and debug.

### Example
In the (Rust) code below, the implementation of the struct `User` (equivalent to a class in other languages) has multiple responsibilities. It knows how to create a new User, validate and update a user's email, persist user data to a database. As more features are added, this can lead to complextity for the class, making hard to understand its purpose.

```rust
struct User {
    user_name: String,
    email: String,
    is_admin: bool
}

impl User {

    fn new (user_name: String, email: String, is_admin: bool) -> Self {
        return User {
            user_name,
            email,
            is_admin
        };
    }

    fn update_email(&self, email: String) {
        if self.is_valid_email(email) {
            self.email = email;
        }
    }

    fn save_user_to_database(&self) {
        let conn = MysqlConnection::new();
        conn.execute(
            "INSERT INTO users (user_name, email, is_admin) values (?1, ?2, ?3)", 
            self.user_name, 
            self.email,
            if self.is_admin { 1 } else { 0 }    
        );
    }

    fn is_valid_email(&self, email: &str) -> bool {
        // validate email
        return true
    }
}
```

We can improve by applying SRP:

```rust
struct User {
    user_name: String,
    email: String,
    is_admin: bool
}

struct Email {
    value: String
}

struct UserMapper<'a> {
    user: &'a User
}

struct UserDatabase<'a> {
    conn: &'a MysqlConnection
}

impl User {
    fn new (user_name: String, email: Email, is_admin: bool) -> Self {
        return User {
            user_name,
            email,
            is_admin
        };
    }

    fn update_email(&self, email: Email) {
        self.email = email;
    }
}

impl Email {
    fn new (email: String) -> Self {
        if !Email::isValid(email) {
            panic!("Invalid email.")
        }

        return Email { 
            value: email 
        };
    }

    fn is_valid(email: &str) -> bool {
        // validate email
        return true;
    }
}

impl<'a> UserMapper<'a> {
    fn new (user: &'a User) -> Self {
        return UserMapper {
            user
        };
    }

    fn getData(&self) -> (String, String, u8) {
        return (
            self.user.user_name, 
            self.user.email.value, 
            if self.is_admin { 1 } else { 0 }
        );
    }
}

impl<'a> UserDatabase<'a> {
    fn new(conn: &'a MysqlConnection) -> Self {
        return {
            conn
        };
    }

    fn save(&self, user: &User) {
        let user_mapper = UserMapper::new(user);
        let (user_name, email, is_admin) = user_mapper.getData();

        self.conn.execute("INSERT INTO users (user_name, email, is_admin) values (?1, ?2, ?3)", 
            user_name, 
            email,
            is_admin    
        );
    }
}
```

In applying the Single Responsibility Principle, we have transformed the original implementation of the User class that had multiple responsibilities into a set of smaller classes, each with a single responsibility. While the number of classes has increased, this approach leads to a more flexible design that is easier to trace, read, and understand.

It's worth noting that applying the SRP requires developers to have a clear understanding of the functionality of each class, and what its purpose is within the system. Blindly applying the principle without proper analysis can lead to an even more complex design than the original. Therefore, it's important to carefully consider the responsibilities of each class and ensure that they are well-defined and cohesive.

## Open-closed Principle
The open-closed principle (OCP) states that software entities such as functions, classes, modules should be open for extension, but closed for modification.

The benefits of using OCP include making your code extensible and easy to maintain. By introducing new code to add new functionality to your program without risking unintended side effects or breaking existing code.

### Example
Let's condider an e-commerce system that has a module responsible for handling payments. Initially, the payment module supports only PayPal payments.
```rust
struct PaymentProcessor {
    pay_pal_gateway: PayPalGateway,
}

impl PaymentProcessor {
    fn new () -> PaymentProcessor {
        return PaymentProcessor {
            pay_pay_gateway: PayPalGateway,
        }
    }

    fn process(&self, amount f32) -> bool {
        // do something with self.pay_pal_gateway...

        return true;
    }
}
```

As the bussiness grows, you decide to add Stripe payment. Without OCP, you would need to modify the PaymentProcessor struct and its implementation.
```rust
struct PaymentProcessor {
    pay_pal_gateway: PayPalGateway,
    stripe_gateway: StripeGateway
}


impl PaymentProcessor {
    fn new () -> PaymentProcessor {
        return PaymentProcessor {
            pay_pay_gateway: PayPalGateway,
            stripe_gateway: StripeGateway,
        }
    }

    fn process(&self, amount f32, is_stripe: bool) -> bool {
        if is_stripe {
            // do something with self.stripe_gateway...
        } else {
            // do something with self.pay_pal_gateway...
        }
        return true;
    }
}
```
Now try to add once more gateway, this time we add Apple Pay.
```rust
struct PaymentProcessor {
    pay_pal_gateway: PayPalGateway,
    stripe_gateway: StripeGateway,
    apple_pay_gateway: ApplePayGateway,
}

enum PaymentGateway {
    PayPal,
    Stripe,
    ApplePay
}

impl PaymentProcessor {
    fn new () -> PaymentProcessor {
        return PaymentProcessor {
            pay_pay_gateway: PayPalGateway,
            stripe_gateway: StripeGateway,
            apple_pay_gateway: ApplePayGateway,
        }
    }

    fn process(&self, amount f32, gateway: PaymentGateway) -> bool {
        match gateway {
            PaymentGateway::Stripe => // do something with self.stripe_gateway...,
            PaymentGateway::PayPal => // do something with self.pay_pal_gateway...,
            PaymentGateway::ApplePay => // do something with self.pay_pal_apple...,
        }

        return true;
    }
}
```
As you can see, every time a new payment gateway is introduced, you need to modify the existing code, which can risk breaking it.

Now, let's refactor the code by applying OCP. First, we will revert the system to support only PayPal.
```rust
trait PaymentProcessor {
    fn process(amount: f32) -> bool
}

struct PayPalGateway

impl PaymentProcessor for PayPalGateway {
    fn process(amount: f32) -> bool {
        // processing...

        return true;
    }
}
```

Next, we will add Stripe and Apple Pay gateways..
```rust
...

struct StripeGateway

impl PaymentProcessor for StripeGateway {
    fn process(amount: f32) -> bool {
        // processing...

        return true;
    }
}

struct ApplePayGateway

impl PaymentProcessor for AppleGateway {
    fn process(amount: f32) -> bool {
        // processing...

        return true;
    }
}
```
You can see that we've introduced new struct for each payment gateway and implemented `PaymentProcessor` trait for them without modifing the `PaymentProcessor` trait or touching the `PayPalGateway` struct.

## Liskov Substitution Principle
The Liskov Substitution Principle (LSP) is a particular definition of a subtyping relation, that was initially introduced by Barbara Liskov in 1987. LSP states that a class can be replaced by its sub-class without breaking the program.

### Example
We continue with the Payment Gateway example. If we want to use one of gateways we could create 3 functions for `PayPalGateway`, `AppleGateway`, `StripeGateway`.

```rust
fn handle_payment_using_pay_pal(pay_pal: PayPalGateway) {
    pay_pal.process(50);
}

fn handle_payment_using_apple_pay(apple_pay: ApplePayGateway) {
    apple_pay.process(30);
}

fn handle_payment_using_stripe(stripe: StripeGateway) {
    stripe.process(40);
}
```

or create a function with conditional creation of instances.

```rust
fn handle_payment(amount f32, gateway: PaymentGateway) {
    match gateway {
        PaymentGateway::Stripe => {
            let stripe = StripGateway::new();
            stripe.process(amount);
        },
        PaymentGateway::PayPal => {
            let pay_pal = PayPalGateway::new();
            pay_pal.process(amount);
        },
        PaymentGateway::ApplePay => {
            let apple_pay = ApplePayGateway::new();
            apple_pay.process(amount);
        },
    }
}
```

As you can see everytime you add new gateway for the system you need to modify the `handle_payment()` to support that new one. By applying LSP we ensure that the `handle_payment()` can run with new gateway without modifying it.

```rust
fn handle_payment(payment_gateway: Box<dyn PaymentProcessor>, amount: f32) {
    payment_gateway.process(amount); 
}
```

By refactor the code to use a base class (trait in Rust), `handle_payment()` can now run without breaking the program in case we forget to modify the code when new gateway is introduced. Because we you a base class, we can make sure that every sub-class can have the same behaviors of its parent.

### Interface Segregation Principle
The Interface Segregation Principle (ISP) is a fundamental principle of SOLID design that advocated breaking large interfaces into smaller, more specific ones. The principle emphasizes that clients dhould not be forced to depend on methods or functionality that they don't need. Like other principles in SOLID apply ISP will make your code flexible, easy to maintain.

## Example
Let's consider an example what we have a `Person` interface defined using a Rust Trait. The interface has methods to get basic information `get_name()`, `get_age()` with some information about the job such as `get_profession()`, `get_salary()`
```rust
trait Person {
    fn get_name() -> String;
    fn get_age() -> u8;
    fn get_profession() -> String;
    fn get_salary() -> f64;
}
```

But not all persons have professions or salaries (eg. children), which means that clients who use this interface are forced to implement all the methods, even though some of them might be irrelevent. Let's refactor the code by applying ISP.

```rust
trait Person {
    fn get_name() -> String;
    fn get_age() -> u8;
}

trait HasJob {
    fn get_profession() -> String;
    fn get_salary() -> f64;
}
```

We broke the "big" `Person` interface into 2 smallers and speciific ones which are `Person` and `HasJob`. By doing this, clients can choose the interfaces that they need, without being forced to implement unnecessary methods.


### Dependency Inversion Principle
Dependency Inversion Principle (DIP) is as method making the high-level modules and low-level modules loosely coupling by introducing the middle layer (Interfaces). 

High-level modules are components that take care about bussiness domain functionality such as `getOrder`, `updateOrder`,... Low-level modules are components might be the database access layer. By using DIP, both of modules will not have a direct dependency on each other. Instead, they are depend on an abstraction or interface, this make easy to maintain, change as changing each module will not effect others.

## Example
The high-level module of a e-commerce system has a function to show customer's orders. In order to establish the task the high-level need to interact with the low-level which has access to database. By introducing the `DatabaseAccess` trait the interaction becomes independent between two modules. We can change the database to use other system instead of Sqlite as the code below without changing the high-level module.
```rust
// High level module
trait DatabaseAccess {
    fn connect(&self, url: &str) -> Result<(), String>;
    fn query(&self, query: &str) -> Result<Vec<String>, String>;
}

struct Application {
    database: Box<dyn DatabaseAccess>,
}

impl Application {
    fn new(database: Box<dyn DatabaseAccess>) -> Self {
        return Application {
            database
        };
    }

    fn show_orders(&mut self) {
        self.database.connect("sqlite://e-commerce.sqlite").unwrap();

        let orders = self.database.query("SELECT * FROM orders").unwrap();

        println!("{:?}", orders);
    }
}
```

```rust
// Low level module
struct SqliteAccess {
    connection: Option<Connection>,
}

impl DatabaseAccess for SqliteAccess {
    fn connect(&self, url: &str) -> Result<(), String> {
        let connection = Connection::open(url).map_err(|e| e.to_string())?;
        self.connection = Some(connection);

        return Ok(());
    }

    fn query(&self, query: &str) -> Result<Vec<String>, String> {
        let connection = self.connection.as_ref().ok_or("not connected")?;
        let mut stmt = connection.prepare(query).map_err(|e| e.to_string())?;
        let rows = stmt.query_map([], |row| row.get(0)).map_err(|e| e.to_string())?;
        let results: Vec<String> = rows.map(|r| r.unwrap()).collect();

        return (Ok(results));
    }
}
```


