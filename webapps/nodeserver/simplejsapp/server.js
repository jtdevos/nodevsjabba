// hi!

const fs = require("fs");
const http = require('http');
const url = require('url');
const Dispatcher = require('./Dispatcher')
const querystring = require('querystring')

const hostname = '127.0.0.1';
const port = 8080;
const dispatcher = new Dispatcher();
const MAX_FIBONACCI_INPUT = 42

const server = http.createServer((req, res) => {
    dispatcher.dispatch(req, res);
});

server.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}/`);
});

dispatcher.addRoute('/fib', (req, res) => {
    var info = url.parse(req.url);
    var query = querystring.parse(info.query);
    var num = parseInt(query.i);
    if(!num) {
        res.statusCode = 400
        res.end('error');
    } else if(num > MAX_FIBONACCI_INPUT) {
        res.statusCode = 400
        res.end('error');
    }   else {
        res.statusCode = 200;
        res.setHeader('Content-Type', 'text/html');
        res.end(fib(num).toString());
    }
});

function fib(i) {
    if(i == 0 ) return 1;
    if(i == 1 ) return 1;
    return fib(i - 1) + fib(i - 2);
}




