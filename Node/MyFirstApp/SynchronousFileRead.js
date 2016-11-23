var fs = require("fs");

var buffer = fs.readFileSync("C:\\Sathyaish\\temp\\MyFriends.txt");

console.log(buffer.toString());

console.log("Program ended.");