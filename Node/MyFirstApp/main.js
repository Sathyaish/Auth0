var http = require("http");

var server = http.createServer(serverInitializer);

var port = 8181;

server.listen(port);

function serverInitializer(request, response)
{
	response.writeHead(200, { "Content-Type" : "text/plain"});
	
	response.write("Hello, World!");
	
	response.end();
}

console.log("Server running at http://127.0.0.1:8181");