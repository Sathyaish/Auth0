var events = require("events");

var eventEmitter = new events.EventEmitter();

eventEmitter.on("somethingHappened", somethingHappenedHandler);
eventEmitter.on("somethingElseHappened", somethingElseHappenedHandler);

function somethingHappenedHandler(data)
{
	if (data != null)
	{
		console.log(data.toString());
		
		eventEmitter.emit("somethingElseHappened", { "data" : "Something else happened." });
	}
	else
	{
		console.log("Something happened but there was no data.");
	}
}

eventEmitter.emit("somethingHappened", /*data*/ "Look! something happened.");

function somethingElseHappenedHandler(data)
{
	if (data != null)
	{
		console.log(data.data.toString());
	}
	else
	{
		console.log("Something else happened but there was no data.");
	}
}