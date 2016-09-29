//simple dispatcher to route requests to appropriate handler
const url = require("url");

class Dispatcher {
    constructor(defaultHandler) {
        this.routes = {};
        this.defaultHandler = defaultHandler;
        if(!this.defaultHandler) {
            this.defaultHandler = (req, res) => {
                const info = url.parse(req.url);
                res.statusCode = 200;
                res.setHeader('Content-Type', 'text/plain');
                res.end('Hello from default');
            }
        }
    }

    addRoute(route, handler) {
        this.routes[route] = handler;
    }

    dispatch(req, res) {
        var info = url.parse(req.url);
        console.log("okay the pathname is: %s", info.pathname);
        var handler = this.routes[info.pathname];
        if(!handler) {
            handler = this.defaultHandler;
        }
        handler(req, res);
    }

}
module.exports = Dispatcher;
