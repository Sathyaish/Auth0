fun main(args: Array<String>)
{
    // TestReadOnlyVariable();

    // TestObjectDeclarationUse();

    // TestCompanionObjectUsage();

    TestKotlinUsesJavaCollections();
}

fun TestKotlinUsesJavaCollections()
{
    val numbers : List<Int> = listOf(1, 2, 3);

    println(numbers.javaClass);
}

fun TestReadOnlyVariable()
{
    val num: Int = 1;

    println("num: $num");
    // CAUSES ERROR AS EXPECTED: Val cannot be reassigned
    // num++;
}

class Person(val name: String, var age: Int)
{
    init
    {
        // CAUSES ERROR AS EXPECTED: Val cannot be reassigned
        // name = "Mr. $name";
    }
}

interface Factory<T>
{
    fun Create(): T;
}

class Foo
{
    object FooFactory
    {
        fun Create(): Foo
        {
            println("Object declaration named Factory called.");

            // implicit return expression.
            // Since a function is a block scope
            // container, the value of the last
            // expression in this block must be its return.
            // Let's test that.

            // Apparently, doesn't work. You have to explicitly return.
            return Foo();
        }
    }

    fun Do(name: String)
    {
        println("$name is doing stuff...");
    }

    companion object Creator : Factory<Foo>
    {
        override fun Create() : Foo
        {
            println("Companion object named Creator called.");

            // Explicit return statement
            return Foo();
        };
    }
}

fun TestObjectDeclarationUse()
{
    var foo = Foo.FooFactory.Create();
    foo.Do("foo");
}

fun TestCompanionObjectUsage()
{
    var foo1 = Foo.Creator.Create();
    foo1.Do("foo1");

    var foo2 = Foo.Create();
    foo2.Do("foo2");
}

open class Tree<out T>
{
    class Node<T>(val left: Tree<T>, val right: Tree<T>) : Tree<T>();
    class Leaf<T>(val value: T) : Tree<T>();
    object Empty : Tree<Nothing>();
}