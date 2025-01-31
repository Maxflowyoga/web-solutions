const bcrypt = require('bcrypt');


class Router {


    constructor (app, db) {
        this.login(app, db);
        this.logout(app, db);
        this.isLoggedIn(app, db);
    }


    login(app, db) {

        app.post('/login', (req, res) => {


            //make sure password input field is populated


            let username = req.body.username;
            let password = req.body.password;

            console.log("Username is: " + username);

            username = username.toLowerCase();

            if (username.length > 12 || password.length > 12 ) {
                res.json({
                    success: false,
                    msg: 'An error occured with password length, please try again'
                })
                return;
            }

            let cols = [username];
            db.query('Select * FROM user WHERE username = ? LIMIT 1', cols, (err, data, fields) => {

                if (err) {
                    res.json({
                        success: false,
                        msg: 'An error occurred, please try again'
                    })
                    return; 
                }

                if(data && data.length === 1) {

                    bcrypt.compare(password, data[0].password, (bcryptErr, verified) => {

                        if(verified) {

                            req.session.userID = data[0].iduser;

                            res.json({
                                success: true,
                                username: data[0].username
                            });

                            console.log("Successful login for user: " + username);

                            return;
                        }
                        else {
                            res.json({
                                success: false,
                                msg: 'Invalid password'
                            });
                        }
                    });
                 } else {
                    res.json({
                        success: false,
                        msg: 'No User Found with that Username, ' + req.body.username + ' please try again.'
                    });
                 }
            })
            
        })
    }


    logout (app, db) {

        app.post('/logout', (req, res) => {

            if(req.session.userID) {

                req.session.destroy();
                res.json({
                    success: true
                })
                return true;
            }
            else {

                res.json({
                    success: false
                });
                return false;
            }
        });
    }

    isLoggedIn(app, db) {


        app.post('/isLoggedIn', (req, res) => {

            if(req.session.userID) {

                let cols = [req.session.userID];
                db.query('SELECT * FROM user WHERE userid = ? LIMIT 1', cols, (err, data, fields) => {

                    if (data && data.length === 1) {

                        res.json({
                            success: true,
                            username: data[0].username
                        })
                        return true;
                    } else {
                        res.json({
                            success: false
                        })
                    }
                });
            } 
            else {
                res.json({
                    success: false
                })
            }
        });


    }




}

module.exports = Router;