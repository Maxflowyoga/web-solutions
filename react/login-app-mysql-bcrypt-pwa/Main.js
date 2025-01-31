const express           = require('express');
const app               = express();
const path              = require('path');
const mysql             = require('mysql2');
const session           = require('express-session');
const MySQLStore        = require('express-mysql-session');
const Router            = require('./Router');



app.use(express.static(path.join(__dirname, 'build')));
app.use(express.json());

console.log("Testing Server");


// Database


const db = mysql.createConnection({
    host: 'localhost',
    user: '',
    password: '',
    database: ''
});


db.connect(function(err) {

    if (err){
        console.log("DB error: " + err);
        throw err;
        return false;
    }

});

/*
const sessionStore = new MySQLStore({
    expiration: (1825 * 86400 * 1000),
    endConnectionOnClose: false
}, db);
*/

app.use(session({
    key: '',
    secret: '',
   // store: sessionStore,
    resave: false,
    saveUninitialized: false,
    cookie: {
        maxAge: (1825 * 86400 * 1000),
        httpOnly: false
    }
}));


new Router(app, db);

app.get('/', function (req, res) {
    res.sendFile(path.join(__dirname, 'build', 'index.html'))
});

app.listen(3000);

/*


CREATE TABLE `user` (
  `iduser` int NOT NULL AUTO_INCREMENT,
  `username` varchar(240) DEFAULT NULL,
  `password` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `iduser_UNIQUE` (`iduser`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


*/