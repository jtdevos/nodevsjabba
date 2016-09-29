const url = require('url');
const querystring = require('querystring');

function fib(i) {
    if(i == 0 ) return 1;
    if(i == 1 ) return 1;
    return fib(i - 1) + fib(i - 2);
}

module.exports = (req, res) => {
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
}