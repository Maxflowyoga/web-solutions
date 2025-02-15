const LocalStrategy = require('passport-local').Strategy;
const bcrypt = require('bcrypt');

function initialize( passport, getUserByEmail, getUserById ) {

    const authenticateUser = async (email, password, done) => {

        const user = getUserByEmail(email);

        if (user == null) {
            return done(null, false, { message: 'No user with that email'});
        }

        try {
          //  console.log("Comparing password: " + password);
          //  console.log("...with user.password: " + user.password);

            if (await bcrypt.compare(password, user.password)) {

                return done(null,user);

            } else {
                
                return done(null, false, { message: 'Password incorrect'});

            }
        } catch (e) {
            console.log("error in authenticate user: " + e);
            return done(e);
        }
    }

    passport.use(new LocalStrategy({ usernameField: 'email' }, authenticateUser));

    console.log("User successfully authenticated...");

    passport.serializeUser((user, done) => done(null, user.id));

    passport.deserializeUser((id, done) => {  

      return  done(null, getUserById(id));

    });


}

module.exports = initialize;