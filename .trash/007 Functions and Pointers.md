## What is a Function?

A function is a self-contained block of statements that perform a coherent task of some kind. Following would be an apt example:

``` C
message();
main()
{
	message();
	printf("\nCry, and you stop the monotomy!");
}
message()
{
	printf("\nSmile, and the world smiles with you...");
}
```

Its output would be as follows: *Smile, and the world smiles with you... Cry , and you stop the monotony!*

When the **main function** calls the **message function** it hands the control over to the **message function** and goes to sleep while it runs and then comes back to life when it has completed executing, you can 
