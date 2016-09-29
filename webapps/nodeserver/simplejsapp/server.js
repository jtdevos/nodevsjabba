//built-in modules
const fs = require("fs");
const http = require('http');
const url = require('url');
const querystring = require('querystring')

//our own modules
const Dispatcher = require('./Dispatcher')
const fileactions = require('./FileServeAction');

const hostname = '127.0.0.1';
const port = 8080;
const dispatcher = new Dispatcher();
const MAX_FIBONACCI_INPUT = 42

dispatcher.addRoute('/fib', require('./FibonacciAction'));
dispatcher.addRoute('/listing', fileactions.fileListingHandler);
dispatcher.addRoute('/serve-file', fileactions.fileServeHandler);

const server = http.createServer((req, res) => {
    dispatcher.dispatch(req, res);
});

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});






