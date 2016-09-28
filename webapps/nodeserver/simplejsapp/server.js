// hi!

const fs = require("fs");
const http = require('http');
const url = require('url');
const Dispatcher = require('./Dispatcher')

const hostname = '127.0.0.1';
const port = 8080;
const dispatcher = new Dispatcher();

const server = http.createServer((req, res) => {
  dispatcher.dispatch(req, res);
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});





