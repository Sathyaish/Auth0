title: "How to create an application in Kotlin and secure it using JSON Web Tokens (JWTs)"

description: "In this article, we’ll learn how to create a simple application using Kotlin, a statically typed programming language that targets the Java Virtual Machine (JVM). We’ll secure all communication with our application using JSON Web Tokens (JWTs)."

**TL;DR** In this article, we’ll learn how to create a simple application using Kotlin, a statically typed programming language that targets the Java Virtual Machine (JVM). We’ll secure all communication with our application using JSON Web Tokens (JWTs).

---



In this article, we’ll learn how to create a simple application using [Kotlin](https://kotlinlang.org/), a statically typed programming language that targets the Java Virtual Machine (JVM). We’ll secure all communication with our application using [JSON Web Tokens (JWTs)](https://jwt.io/).



Don’t worry if you don’t know what a JSON Web Token (JWT) is. I’ll cover that in a bit.

And you don’t need to know any Kotlin, either. If you’ve got some decent programming experience with any programming language, you’ll be able to follow through without any difficulty. If you’re a Java programmer, though, you’ll feel right at home because Kotlin uses the Java API to do everything. It’s got a very sparse syntax with a lightweight standard library.

In fact, let us cover all of the features of Kotlin used in the [code that goes with this article](https://github.com/Sathyaish/Bookyard).


## A Crash Course in Kotlin


To declare a variable that can be read from *and written to*, use the `var` keyword:



```kotlin

var name : String = “Joe Bloggs”;



var age = 20; // Type inferred by the compiler



age = 21; // valid statement since the variable is writable also

```



Semi-colons as statement terminators are optional. But it’s a good practice to have them, anyway. All throughout our code, we’ll use semi-colons to terminate statements.



To declare a read-only variable that can only be initialized once, use the `val` keyword:



```kotlin

val name : String = “Joe Bloggs”;



val age = 20;



age = 21; // Illegal statement. Compiler error. The variable is read-only.

```



To create a class:



```kotlin

class Student() {

  // This is a class that has one default parameterless constructor

  // The parenthesis after the class name is actually the constructor declaration for this class.

}



class Student {

  // If the class has just one default parameterless constructor, 

  // the parenthesis area optional

}



class Student; // if the class is empty, the curlies are optional.

```



To create an object of the Student class:

```kotlin

// Kotlin does not have the new keyword. 

// This creates a read-only/assign-once 

// variable of type Student.

val student : Student = Student();



val student = Student(); // type inferred



// read-write variable

var student : Student = Student();

```



Nullable types:



Kotlin distinguishes between nullable and non-nullable types. Each type, whether a primitive or user defined, has both a nullable version and a non-nullable version. You create a nullable version by appending a ? symbol after the type name.



```kotlin

var age : Int? = 2; // nullable integer



age = null; // valid



val joe : Student? = null; // valid



var lisa : Student = null; // illegal. Compiler error. The variable is not nullable.

```



To declare a class Student with a read-only, non-nullable property called name and a read-write, nullable property called age:



```kotlin

class Student(val name : String, var age : Int?);

```



In the above code, `name` and `age` are properties. Kotlin creates a getter for the `name` property and a getter and setter pair for the `age` property. Also, the `Student` class in the listing above gets a parameterized constructor.



To create an object of the Student class and use it:

```kotlin

// creates a read-only, nullable variable of type Student

val student : Student? = Student(“Lisa”, null); 

```



To create a class with optional parameters in its constructor:

```kotlin

class Student(val name : String, 

              var age : Int? = null, 

              var gender : String = “Male”);

	      

// optional argument omitted. Nullable student.

val joe : Student? = Student(“Joe Bloggs”, 20);



// Provided an explicit value for all 

// arguments including the optional argument. 

// Non-nullable student.

val lisa : Student = Student(“Lisa Hendricks”, 18, “Female”);

```



Functions in Kotlin can exist independently outside of any class. To create a function that returns void (`Unit` is a type that means void):

```kotlin

fun Print(name : String?) : Unit {

  if (name != null) {

    println(name);

  }

}

```



The Unit keyword is optional. The same function could be re-written as the following:

```kotlin

fun Print(name : String?) {

  if (name != null) {

    println(name);

  }

}

```



To create a class with instance methods:

```kotlin

class Student(val name : String) {



 	fun displayName() {

		println(this.name);

	}

}

```



To use it:

```kotlin

val joe : Student = Student(“Joe Bloggs”);



joe.displayName();

```



Kotlin does not have static classes. Because functions can exist independent of classes, you just write your functions that you would have wanted to write in a static class in a separate file.

```kotlin

// file: JustMyFunctions.kt



fun play() {

}



fun stop() {

}



fun sing(song : String) {

}



fun fastForward(frames : Int) : Boolean {

}

```



Sometimes, you want to create a class with no methods just to hold data so you can serialize/deserialize it or just hold some data in it so as to pass that data across the boundaries of your application. Such classes are referred to by various names, such as Data Transfer Objects, data objects, beans, Plain Old Java Objects (POJOs), or Plain Old CLR Objects (POCOs) in the case of C# and .NET. 


To create a class of that kind, you’ll simply add the keyword `data` before the class keyword like so:

```kotlin

data class Student(val name : String) {

  // A class with one read-only, 

  // non-nullable property that has 

  // only a getter for its name property

}

```



When you create a data class, in addition to the getters and setters for properties, which Kotlin creates even for classes that are not marked as `data` classes, Kotlin generates the following methods for the data class behind the scenes:

```kotlin

hashCode();



toString();



equals();



copy();

```





Annotations:

You can annotate a method, constructor, or class like in any other language:

```kotlin

@annotationForClass class 

      @annotationForConstructor Student(@propertyAnnotation val name : String) {

      

	@methodAnnotation fun display() {

		//

	}

}

```



All classes and methods in Kotlin are non-inheritable and non-overridable, respectively, unless otherwise explicitly stated by declaring them with the `open` keyword.

```kotlin

class ThisClassCannotBeInherited;



open class ThisClassCan;



open class ThisClassCanAlsoBeInherited {

	

  fun thisMethodCannotBeOverriden() {

  }



  open fun thisMethodCan() {

  }

}



class ChildClass : ThisClassCanAlsoBeInherited {



  override fun thisMethodCan() {

  }

}

```



Interfaces and class inheritance:

```kotlin

interface IPerson {

}



open class Person : IPerson {

}



interface ILoggable {

}



open class Student : Person, IPerson, ILoggable {

}

```





In Kotlin, packages and [Visibility Modifiers](https://kotlinlang.org/docs/reference/visibility-modifiers.html) work exactly like they do in Java.



To create a Singleton object:

```kotlin

object IAmASingletonObject {

  

  val firstProperty : String;

}

```



The above construct is called an *object declaration*. An object declaration is an object instance that does not belong to a class. And since that’s the only instance you can have of it, it is effectively a singleton object. Therefore, you use an object declaration when you need to create a singleton instance.



We use the object declaration like so:

```kotlin

IAmASingletonObject.firstProperty = “Hello, World!”;

```



You can put it inside a class, as well. In this case, the object will be able to access the internals of its containing class.



If you mark an object declaration with the `companion` keyword, the members of the companion object can be referenced directly as members of the containing class like so:

```kotlin

class User(val userName : String,  val password : String?) {



  companion object Validator {

  

    public fun isValid() : Boolean {

      

      // access containing object’s members

      If (userName.equals(“Joe”) {

	//

      }

    }

  }

}



// Usage



val user : User = getUser();



if (user.isValid()) {

}

```



That’s pretty much all you need to know to get started and be productive with Kotlin.





## What We’re Going to Develop



We’ll create a client/server application that gets from a web API a list of book recommendations for a logged-in user based on the user’s interests or likes. We’ll store the user’s likes in a database.


We’ll write both the client and the web API in Kotlin. The client will be a desktop application written using the Swing/AWT libraries. The server is an HTTP Servlet that returns data objects declared in a library named **Contracts** as JSON strings. We’ll call our application -- in fact, we’ll call this whole system -- by the name Bookyard.



Here’s what the high-level component architecture for Bookyard would look like:



![Bookyard Component Architecture](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/Bookyard%20Component%20Architecture.png)



## Workflow



Assuming the servlet application is running, when the user launches the client application, a login dialog will appear.



![Bookyard Login Dialog](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/BookyardLogin.png)

 

A successful login will dismiss the login dialog and display a window listing the recommended books for the logged-in user.



![Book Recommendations Window](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/RecommendationsWindow.png)

 

Please ignore the aesthetical anomalies of the graphical user interface.





## OAuth 2.0 and Token /Claims Based Authorization



In order to understand how we’ll ensure secured communication between the client and the server of Bookyard, I’d like to provide a to-a-four-year-old explanation of some of the highfalutin terms popularly used in elite architect circles.



Consider a traditional web application that resides on a single server. That’s how it used to be done in the old days when the Web was a new thing—you had all the source code on a single server.



You had two parties:



1. A web server that had some server-side code that ran on the remote server and also some client-side code that ran on the browser. 



   Since both the client code and the server code were part of a single application usually written by a single developer or company, the server-side code and the client-side code could be considered a single entity or a single application.



2. The user using the application in a Web browser.



In those cases, a simple user name and password-based authentication was sufficient to validate the identity of the user.

When the user logged in, the server would issue a session ID and an authentication cookie to the user’s browser. The browser would carry these two with every subsequent request to the server.



This all worked fine until the number of users outgrew the server’s capacity to handle requests.





### Scenario 1: A Clustered Environment



When you had two servers running the same application code, you had a problem. If the login request came to **server A**, which issued a session cookie and an authentication cookie to the user, **server B** didn’t know anything about those cookies. Therefore, any subsequent requests coming in to **server B**, even after the user had innocently validated his identity earlier with **server A**, would fail with **server B**.



One obvious solution to this problem is to make **server A** and **server B** share their session IDs. This could be done by having an external state server that held the session state for the entire Web application in an external data source, such as a database or an in-memory state server.



A similar but simpler and more secure solution, however, is to have a separate authentication server. Each request that comes to either of the servers—**A** or **B**—is validated for the presence of a special value in the request header—a value that could only have been obtained from the authentication server. If the value is present, the servers **A** or **B** service the request. If, however, the special value is missing, the client gets redirected to the authentication server, which, after logging the user in, issues this special value that represents a successful login and an active session. Let’s call this value returned by the authentication server an *authentication token*.



Below is a diagrammatic representation of this simple sequence of three interactions.



![Simple Authentication Server Workflow -- Step 1](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/Simple-1.png)



Under this regime, when the user sends in a request to either of the servers (**A** or **B**), each of them checks to see whether the user has an authentication token. If he doesn’t, they redirect his request to the authorization server, whose duty is to ask the user for his user name and password, authenticate his identity, and issue him an authentication token upon successful login.



![Simple Authentication Server Workflow -- Step 2](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/Simple-2.png)

 

The user’s request is then redirected automatically back to the original URL he intended to get the data from—i.e., **server A** or **server B**. This time, his request carries with it the token, so either of the servers fulfills his request.



![Simple Authentication Server Workflow -- Step 3](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/Simple-3.png)



This scheme of authentication and authorization is known as *token-based authentication* or *token-based authorization*. A series of steps performed in a sequence, as indicated above, may also be called a workflow. Let us name this particular workflow the *Simple Authentication Server Workflow*.


I’d like to confess that the names *authentication token* and *Simple Authentication Server Workflow* are names I have made up. You will not find them in security literature. But in deliberately flying by the seat of my pants on good accord, I am trying to avoid trespassing names that already occur in security literature with specific connotations. If I named this token an *access token*, for example, or if I named the series of steps described above as [*Authorization Workflow*](https://www.youtube.com/watch?v=1vovk4yt2GI), I’d be trespassing a commonly accepted nomenclature that we’ll make a nodding acquaintance with later in this article.



The above series of steps, though potent as a basic building block for more specialized variants, is rather simplistic in that it does not describe the contents of the token and ways of securing it against theft. In practice, how we name such a token is predicated on such puritanical considerations.



### Token Uses and Composition



In our simple example, the client application is a Web application that serves a list of book recommendations for a user based on the user’s likes. The authentication server is a separate endpoint that could be a part of the same application or a different one. The simplicity, however, is born of an assumption that both the authentication server and the resource servers are developed by the same vendor.



Because both the authentication server and the resource servers are assumed to either be a part of the same Web application or—at worst—be URLs of two Web applications developed by the same vendor, the use of such a token was both to authenticate a user and consequently to authorize him for access to the data held in the resource servers.



The evolution of the Web in recent times has opened up a slew of interesting possibilities that call for variations on the workflow described above.



#### Authentication



Big players, such as Google, Yahoo!, and Facebook (to name a few) command large user bases of the total Internet population. This has encouraged users and Web application developers to trust these big players to authenticate users for their identity, consequently freeing up Web application developers to concentrate on developing just business logic, delegating the authentication of their users to these giants.



Imagine building a job search portal. You need to validate that the user is above 18 years of age and has a valid social security number. You don’t really care about any other information about the user. In this case, you could use the US government website to validate the user against these two parameters and receive a token containing identification information about the user. This specific need for authenticating a user’s identity dictates what the contents of the token will be.



#### Authorization



[Another use](https://www.youtube.com/watch?v=hRjwPnulKqg) that has come to light is the sharing of data from one Web application to another. Imagine yourself developing a photo editing software for your users. Instead of having users upload pictures to your Web server, you could pull out their pictures from their [Flickr](https://www.flickr.com/) accounts, edit them in your application, and save them to the user’s [dropbox](https://www.dropbox.com/) or back to their Flickr accounts. In this case, you don’t care about the user’s identity as much as you care about their permission to use their Flickr photographs and their dropbox account.


Both the above uses—namely, the authentication and authorization of users—dictate the separation of the server granting the token, the role of such a token, and consequently its contents.



Though OAuth 2.0 access tokens are opaque strings, the authorization server may, upon request, attach additional information about a user, such as his full name, email address, organization, designation, and what have you into the token container. Such a workflow is illustrated by a variation named [Open ID Connect](http://openid.net/connect/), which builds on top of the OAuth 2.0 framework. This token would then be called an *ID token*. This would obviate the necessity for a database look-up. If such information were to be required by either of the servers (**A** or **B**), they could simply read it from the ID token itself without making a trip to the database server. Each such optional datum attached to an access token is known as a claim, as it establishes a claim upon the identity of the user. For this reason, token-based authentication is also referred to as *claims-based authentication*.



The client or server may communicate using tokens even when their dialog does not pertain to authentication or authorization. With each request, the client may package the information it needs to send to the server in the form of a token, although it wouldn’t be called an access token in that case. You’ll observe later that the login dialog of Bookyard Client sends the user’s username and password in such a token when making a login request to the Bookyard server. That is an example usage of a token of this type but not for the purposes of behaving like an access token.





## Scenario 2: The Distributed Web and OAuth 2.0



This mechanism of *claims-based authorization* described in the above paragraphs has opened up the Web to new possibilities. [Consider a scenario](https://www.youtube.com/watch?v=60j9RfRvHJQ) where you needed to import your [**Gmail**](http://gmail.com/) contacts into [**Linked In**](http://linkedin.com/) so you can invite them all to join your **Linked In** network.



Until 2007, you couldn’t have done that without having your arm twisted. The only way to do that would have been for **Linked In** to present you with a screen wherein you typed your **Gmail** username and password into a **Linked In** user interface, effectively giving **Linked In** your **Gmail** username and password. What a shoddy life our younger selves lived!



Thankfully, a bunch of guys at Twitter got together and said, “That must change.”



They started by identifying that in a transaction of the kind described above, there are three parties involved:



1. A resource server: A server where the user’s data is kept. In this case, that would be Gmail because your contacts would be kept there.

2. A user who owned the resources at Gmail; and

3. A client: A third-party application that needed access to your data from the resource server. In other words, Linked In (the third party) that needed your Gmail (resource server) contacts.



They wrote out a bunch of rules that both the resource server (**Gmail** in this example) and the third-party application (Linked In in this example) would have to incorporate into their code in order to perform claims-based authorization so that you wouldn’t have to give your Gmail username and password to Linked In.



They called this grand scheme of interaction [OAuth](https://oauth.net/). It has since spread like wildfire.



OAuth has, since its advent, been revised twice as [v1](https://tools.ietf.org/html/rfc5849), [v1a](https://oauth.net/core/1.0a/) and [v2.0](https://oauth.net/2/). Version 2.0 is the most recent and popular one, and the versions are not backward compatible. Any reference to OAuth in this article without an explicit version suffix must be understood to mean [OAuth 2.0](https://tools.ietf.org/html/rfc6749).



Today, virtually every website—from [**Github**](https://github.com/) to [**Gmail**](http://gmail.com/), [**Picassa**](https://picasa.google.co.in/) to [**Flickr**](https://www.flickr.com/), and perhaps even your own company’s—has a resource server that exposes data in an OAuth way. The [OAuth 2.0 specification](https://tools.ietf.org/html/rfc6749) also calls resource servers by the name *OAuth servers*, and the third-party clients by the name *OAuth clients*.



Today, virtually every user, knowingly or not, uses OAuth. Wherever on the Web you see [buttons of the kind below](https://www.youtube.com/watch?v=Q-Q03qzC7zA), that is [OAuth 2.0 in action](https://www.youtube.com/watch?v=CiwtxlitF7Y).



![Login with Facebook](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/LoginWithFacebook.png)

![Login with Github](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/LoginWithGithub.png)

![Import Linked In profile](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/ImportLinkedInProfile.png)



The evolution of the Web has enabled a scenario where the traditional web application could be written by an OAuth provider. The client application, as was the case with **Linked In** in our example above, could be written by someone else, and the user could be someone else.

OAuth 2.0 access tokens are opaque and can be any string—even the string “Hello, World!” But such a value offers no security. In practice, an access token is a bit more useful than “Hello, World,” as it carries an expiry timestamp and may even be encrypted using symmetric or asymmetric encryption.


## What Is a JSON Web Token (JWT)?



The access token is essentially a string sent in the header of the HTTP response by the authorization server to the client. 



With every subsequent request, the client sends this string back to the server in one of three ways:

1. As a part of the URL in a GET request, 

2. As the part of the body in a POST request, or

3. (The preferred way) sent as part of the Authorization HTTP header in the following form:



`Authorization: Bearer <accessToken>`



The OAuth 2.0 specification pussyfoots its way out of mandating a method, deferring the choice to the authorization server. In other words, whether a JWT is used for an access token, which of the above three methods a client must adopt is dictated by the authorization server documentation. The OAuth 2.0 extensions specifications relate to the choices of the access token structure. Clients are not free to choose any of the three at their disposition.



Note the word bearer and also the moniker bearer token used to represent an access token. The moniker bearer token is righty applied, as the access token is a bearer instrument. Just like a tender bill in your pocket or [a movie ticket you buy](https://www.youtube.com/watch?v=iA6VIx5lyyE), the access token doesn’t have a way to attach the user with it. Once you lose it, anyone who has it may misuse it to represent themselves as you, thereby stealing your identity.



Therefore, the best practice is to obscure the access token. For additional security, you may encrypt it.



If you were to write a *client* that had to first decide how to compose an access token string, then program that same logic in the *OAuth server*—which you didn’t write, by the way—and then encrypt the access token. Oh, but wait! You’ve got to decide the encryption algorithm and then a secret key with which to encrypt it. And it doesn’t stop here. You’ve got to tell all of this to the server so it can write the back logic for all of this decryption using the same technique. And then it has to again create a new access token to send you after a successful login—oh, oh, oh my! That would all add up to a gigantic nuisance.



Thankfully, another bunch of people who were interested in and following the development of OAuth saw far into the future and were able to anticipate this pain. They defined a bunch of formats that all OAuth servers and clients could be free to choose from to create access tokens. One such format is named JSON Web Tokens (JWTs).


The format lets you compose the access token as a JSON string.



It has three parts:



1. A header that lets you specify that the string is a JWT and the signing algorithm chosen to sign the token, if any.

2. A body containing the user claims. This is also referred to as the payload.

3. A signature. The signature is derived by first converting the header into a base64 URL, then converting the payload into base64 URL, concatenating the two base-64 encoded values with a period as a separator between the encoded values and using a secret key to sign the resultant string.



The snippet below illustrates the composition of a JSON Web Token:



```javascript

Header

{

  "alg": "HS256",

  "typ": "JWT"

}



Payload

{

  "iss": “Issuer: OAuth Server Name”,

  "sub": "This is the subject of communication.",

  "name": "Joe Bloggs"

}



Signature

HMACSHA256(

  base64UrlEncode(header) + "." +

  base64UrlEncode(payload),

  secret);

  

```



When sending the JWT, you send in the header and payload parts encoded as base64 URL. Then you add another period at the end of these two parts and append the signature derived from the algorithm above to the end of this string. Therefore, an example JWT might look like this (newlines added for readability):



```javascript

eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9

.

eyJpc3MiOiJPQXV0aCBTZXJ2ZXIgTmFtZSIsInN1YiI6IlRoaXMgaXMgdGhl

IHN1YmplY3Qgb2YgY29tbXVuaWNhdGlvbiIsIm5hbWUiOiJKb2huIERvZSJ9

.

odvw2LUXNBannNwpstpQsnYxngoOuN1h0penPRvz2fI

```



The benefits of using a JWT with claims-based authentication, as is obvious from the commentary above, are the following:



1. It works in a clustered environment as well as a single-server deployment.

2. It works when the client and the authorization server are independent parties that are not necessarily provided by the same vendor.

3. It can be used to centralize and jettison out the authentication and authorization of a large system.

4. It can be used even when each of the OAuth servers, resource servers, or authorization servers are written using different technologies. For example, one of your resource servers could be written using ASP.NET, one could be written using PHP, and the authorization server could be written using Python.

5. There is no affinity between the client and the server. Any server will fulfill a request as long as the request has the access token.

6. Unless your session data is large, there is no need to maintain each session separately. The expiry on the access token represents the session. The request doesn’t need to have come to the same server before in order to preserve the session information. No session history need be created with each individual resource server.

7. Since the access token can be encrypted or signed, it can be protected from man-in-the-middle attacks.



It is mandated that we perform token-based authorization on a secure channel, such as SSL/TLS/HTTPS. 





## Securing Bookyard with JSON Web Tokens (JWTs)



When the user clicks the **Login** button on the **Login** dialog, the client application composes a JSON Web Token containing the following claims:



| Claim Name |	Claim Meaning |	Claim Value |

|------------|----------------|-------------|

|`iss`	| Issuer of the JSON Web Token (JWT). Since the client is sending this new JWT, it writes its own application ID as the value of this claim. Though we’re using a JWT to send this information, we could have sent it as the body of a normal POST request. However, sending this information encrypted within a JWT makes it more secure. Also, this is a use of a JWT that is not used as an access token. An access token is granted by an authorization server to the client. This is an example of using a JWT as a means to communicate generic information securely between two parties. | The application ID of the client application. |

|`sub`	| The subject of the claim. | This can be any mutually agreed-upon value between the client and the OAuth server.In our example, the server expects the value “LoginRequest” for a login request coming from a client.|

| `username` |	The username of the user attempting to log in. | There’s presently no way to create a new user, and there exists just one user in the application at present. The username of that user is Sathyaish. |

| `password` |	The password of the user attempting to log in. |	The password of the only user of this application is **FooBar**. |



The code to send this information is in a class named `APIAuthenticationManager`, which resides in the **Client** project in the package `bookyard.client`, as shown by the code listing below.



### Client: `APIAuthenticationManager` class



CODE





The client application uses the open-source library [jjwt/jwtk](https://github.com/jwtk/jjwt) to make the JSON Web Token (JWT). The JWT is then signed with the **application secret**.



When an OAuth client registers with an OAuth server, it is granted an **application ID** and an **application secret**. The database that the Web API references has these values stored for each client.



So that the Web API can know which client sent this request and fetch its **application secret** from the database and then use that secret to decrypt the JWT, the client sends in the body of the POST request its own **application ID**, in addition to the JWT.



The server returns a JSON string that is the serialized form of a class named `OperationResult<String>`. The `OperationResult<T>` class is declared in the **Contracts** module as follows:



### Contracts: `OperationResult<T>` class



CODE



Assuming that the server’s root is at [https://localhost:8443](https://localhost:8443), the client sends this request to the following URL:



`HTTP POST https://localhost:8443/login`



A servlet named `LoginServlet` is configured to accept the HTTPS requests from this route.





### Server : `LoginServlet`



CODE



The servlet invalidates GET requests on its endpoint by returning a **405, bad request/method not allowed** HTTP status code. This is a security measure to ensure that the JWT and the `appId` are not sent as a part of the URL. Although there is nothing wrong with sending this information in the URL from a security viewpoint, the specification defining URLs allows a permissible length of 4096 bytes, so it is prudent that the server mandates that this information be sent only as an HTTP POST request.



### Server : `LoginServlet`



CODE



The servlet overrides the `doPost` method and delegates it to an internal implementation method named `doPostInternal`.

In the event that the parameters received in the request are invalid, an `OperationResult<String>` denoting a failure and containing an appropriate error message is sent to the client.



### Server: `LoginServlet`



CODE



The server then uses the **jjwt/jwtk** library to decrypt and parse the JWT received. It validates that the request is, indeed, a login request by checking that the subject (`sub`) claim of the JWT has the value **“LoginRequest”**.





### Server: `LoginServlet`



CODE



The login servlet then reads the user claims from JWT and makes a database look-up to authenticate the user, ensuring that the user also belongs to the said application with the specified `appId` received in the request.



### Server: `LoginServlet`



CODE



Finally, if all adds up, the servlet constructs an access token, putting in the user information and an expiry timestamp of one hour from the time the token was generated. Then, it sends the access token as a serialized `OperationResult<String>`.



### Server: `LoginServlet`



CODE



The `APIAuthenticationManager` class at the client deserializes the JSON string response and gives it to its caller within the client.



The caller is the **Login** dialog, which checks to see if the response received is successful, meaning that if the user is a valid user, it unpacks the access token from the `data` property of the `OperationResult<T>` object and creates a new window to display the book recommendations. To the book recommendations window’s constructor, it passes the access token. The book recommendation screen needs this access token to make subsequent requests to retrieve the list of book recommendations from the server. It will need to send this access token with every request that it makes.





### Client: `LoginPane.btnLogin::actionListener`



CODE



The book recommendations window makes an HTTP post request, sending the access token in the HTTP Authorization header and the `appId` in the request body. It sends this new request to the recommendations URL of the Web API. The recommendations URL is at [https://localhost:8443/recommend](https://localhost:8443/recommend) and is attended to by a servlet named `RecommendServlet`, which we will list later in this document.



### Client: `BookRecommendationsFrame`



CODE



From this point onwards, at the server, an authorization filter filters every request before it reaches any servlet or endpoint other than the `/login` endpoint.



![Bookyard Request Response Control Flow](https://raw.githubusercontent.com/Sathyaish/Auth0/master/Article/images/Bookyard%20Request%20Response%20Control%20Flow.png)

 

The authorization filter checks for the presence of an access token in the **Authorization** HTTP header, parses it, and validates the token.



If the token is valid, the request is passed to the next filter in the chain of filters and subsequently to its ultimate destination servlet. If not, the filter returns an appropriate error response as an `OperationResult<T>`.



### Server: `AuthorizationFilter`



CODE



The recommendations servlet embodied in the class `RecommendServlet` does not need to validate the request for the presence of an access token. It simply does what it is meant to do—return the list of recommendations based on a user’s likes. It does this by looking up the database.



### Server: `RecommendServlet`



CODE



## Database Schema

It would make sense to look at the database scheme now. Most of the column names are descriptive, so you’ll get what they mean. I’ll provide an explanation only where it is necessary.



### Table: User



| Column Name |	Meaning |

|-------------|---------|

| Id	| Primary key |

| UserName | |

| PasswordHash	| A hash of the user’s password. |

| FullName | |

| Email	| |





### Table: Application



| Column Name |	Meaning |

|-------------|---------|

| Id |	Integer | Primary key |

| Name | A user-friendly name for the OAuth client |

| ApplicationId | A string representing the application ID that is displayed to the client application administrator. This string is used as the `appId` during all communication between any OAuth clients and this server. |

| ApplicationSecret  |	JWTs are signed with this symmetric key. |



### Table: Membership (A relationship table that stores what user belongs to which application/third party/OAuth client)





| Column Name |	Meaning |

|-------------|---------|

| Id | Primary key |

| UserId | Foreign key for `[User].[Id]` |

| UserName | |	

| ApplicationTableId | Foreign key for `[Application].[Id]` |

| ApplicationId	| |



### Table: AccessToken (When a login request succeeds, the server generates a new access token for that request and creates a new entry in this table if one doesn’t already exist for the application and user making the request. If an entry already exists, the server updates the entry in this table to reflect the new access token and the new expiry time. The update is necessary; otherwise, we will have stale/expired access tokens in this table and requests made from valid OAuth clients after the expiry will fail.)



| Column Name |	Meaning |

|-------------|---------|

| Id | 	Primary key |

| UserId | Foreign key for `[User].[Id]` |

| UserName | |	

| ApplicationTableId | Foreign key for `[Application].[Id]` |

| ApplicationId	 | |

| AccessToken  |	JWT string |

| ExpiryDate |	Datetime2. Stored as absolute time but sent to the client in Unix Time—i.e., the number of milliseconds since 1st January 1970. |



### Table: Likeable (represents things that can be liked—e.g., “Programming,” “Java,” “Kotlin”)



| Column Name |	Meaning |

|-------------|---------|

| Id |  Primary key |

| Name | |





### Table: UserLike (Each entry represents a relationship between a user and the thing he likes)



| Column Name |	Meaning |

|-------------|---------|

| Id  | Primary key |

| UserId | Foreign key for `[User].[Id]` |

| UserName | |	

| LikeableId | Foreign key for `[Likeable].[Id]` |





### Table: Book



| Column Name |	Meaning |

|-------------|---------|

| Id | Primary key |

| Name  | Title of the book |	

| Author  | Name of the author |	

| Description | |	

| AmazonUrl | |	





## Source Code



You can download the whole source code for this application from [this github repository](https://github.com/Sathyaish/Bookyard). As a C# developer aiming to localize complexity, after learning some basic Kotlin syntax and practicing it, I wrote the application first in Java and then translated each line to Kotlin. You’ll find both the Java and the C# versions in [the Bookyard repository](https://github.com/Sathyaish/Bookyard).



To know more about the toolset, the modules in the project, known issues, and how to launch the application, read [the ReadMe.md file in the Bookyard repository](https://github.com/Sathyaish/Bookyard/blob/master/README.md).



## Further Reading



Links to documentation



## Summary



In this article, we learned how to use the Kotlin programming language, which is a statically typed programming language that targets the Java Virtual Machine. We described the function of the Bookyard application.

Then, we meandered about ways we could use token-based authentication and authorization to secure an application. We learned what OAuth 2.0 is, what JSON Web Tokens (JWTs) are, and how we used JWTs to secure the Bookyard application.

