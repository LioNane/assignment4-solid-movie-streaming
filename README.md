A. SOLID Documentation

SRP: The only reason to change classes in my project is to implement new functions, not applying already existed.
OCP: I can create new subclasses of superclass Content without its extension.
LSP: Subclasses are supported to be substitutable for their superclasses without knowing superclass.
ISP: Interfaces contain minimum methods, realizing specific function.
DIP: High-level code does not depend on low-level code.

B. Advanced OOP Features

Generics used in Interfaces Validatable and Searchable, in SortingUtils and CrudRepository in order to apply them for any type of entities.
Lambdas used in SortingUtils to work with function like data.
Reflection used in ReflectionUtils to show information about classes.
Default method validates and returns value given to the function.
Static method used for validation checking.

C. OOP Documentation

Content is abstract class, Film and Series are subclasses of Content with overriden methods.
Interfaces Validatable implemented in every class with method validate() to check validation and Playable in classes Film, Series, Episode with play() to "play" them.
Episode is another class with composition of Series.
Polymorphism with method displayInfo() returning type of subclass and countDuration(): in Film returns duration in minutes, in Series returns sum of durations of episodes.

D. Database Section

Schema: films(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, rating FLOAT NOT NULL) 
series(series_id INT PK, name VARCHAR UNIQUE, rating FLOAT, NOT NULL) 
episodes(id INT PK, name VARCHAR UNIQUE, duration INT NOT NULL, series_id INT NOT NULL FK)

D. Architecture Explanation

Repository role is to provide access to CRUD-operations with database.
Service role is to make indirect access to database through repositories and apply business rules.
Controller role is to run program and use all its tools.
Request: Sort films by rating
Response: Sorted list

E. Instructions to Compile and Run

java Main + PostgreSQL + JDBC

Reflection Section

I learned how to make lambda-expressions, generics and how to follow SOLID principles.
For this time I did not face any challenges.
SOLID architecture value is convenience and consistency of the program that follows it.