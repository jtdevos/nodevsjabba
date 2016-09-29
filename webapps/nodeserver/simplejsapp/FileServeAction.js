const fs = require('fs');
const http = require('http');
const path = require('path');
const url = require('url');
const config = require('./config');



const fileServeHandler = (request, response) => {

};


const fileListingHandler = (request, response) => {
        response.setHeader('Content-Type', 'text/plain');
            console.log('readdir %s', config.outfilesDir)
        fs.readdir(config.outfilesDir, (err, paths) => {
            if(err) {
                console.log("something bad happened: %s", err)
                response.statusCode = 400;
                response.end('error');
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