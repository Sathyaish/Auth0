var fs = require("fs");

fs.readFile("C:\\Sathyaish\\temp\\MyFriends.txt", finishedReadingFileCallback);

function finishedReadingFileCallback(error, data)
{
	if (error) return console.log(error);
	
	var buffer = data;
	
	var s = buffer.toString();
	
	console.log(s);
}

console.log("\nProgram ended.\n");