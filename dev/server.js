let express = require('express');
let path = require('path');
let serveStatic = require('serve-static');

let app = express();
app.use(serveStatic('./dist'));

let port = process.env.PORT || 80;
app.listen(port);

console.log('Server running at ' + port);