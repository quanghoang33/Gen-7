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
You added an `PaymentGateway` enum and modifed `PaymentProcessor.process()` again.

The key point here is that every times new payment gateway is introduced you need to modify the exisiting code risking break it.

Lets refactor the code by applying OCP. First, we will rollback the system to support only PayPal.
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

Now we will add Stripe and Apple.
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
You can see that we just introduced new struct for each payment gateway by implement `PaymentProcessor` trait for them. We neither modify the `PaymentProcessor` trait nor touch the `PayPalGateway`.