const fs = require('fs');
const http = require('http');
const path = require('path');
const url = require('url');
const config = require('./config');
const querystring = require('querystring');


function sendError(response, statusCode, message) {
    console.error(message);
    response.statusCode = statusCode;
    response.end(message);
}



const fileServeHandler = (request, response) => {
    var info = url.parse(request.url);
    var query = querystring.parse(info.query);
    var name = query.name;
    if(!name) {
        sendError(response, 404, "file not found / file not specified");
        return;
    }
    var filePath = path.join(config.outfilesDir, name);

    // fixme:  the java version does not do the stat lookup, which is a little unfair.
    var stat = fs.statSync(filePath);
    response.writeHead(200, {
        'Content-Type': 'text/html',
        'Content-Length': stat.size
    });

    var readStream = fs.createReadStream(filePath);
    // We replaced all the event handlers with a simple call to readStream.pipe()
    readStream.pipe(response);
};


const fileListingHandler = (request, response) => {
        response.setHeader('Content-Type', 'text/plain');
            console.log('readdir %s', config.outfilesDir)
        fs.readdir(config.outfilesDir, (err, paths) => {
            if(err) {
                sendError(response, 400, "outfiles dir not found");
            } else {
                response.statusCode = 200;
               var data = paths.reduce((a, b, i ) => {
                   return a + '\n' + b;
               }, '');
               console.log('about to send data');
               response.end(data);
            } 
        })
};

module.exports = {
    'fileServeHandler': fileServeHandler,
    'fileListingHandler': fileListingHandler
}