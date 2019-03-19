var loginForm = function (credentials) {
  return {
    type: "LOGIN",
    credentials
  }
};
module.exports = {loginForm};
