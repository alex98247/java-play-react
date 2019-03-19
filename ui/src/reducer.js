var Map = require("immutable").Map;

var reducer = function(state = Map(), action) {
  switch (action.type) {
    case "SET_STATE":
      return state.merge(action.state);
    case "LOGIN":
      return state.update("credentials", (credentials) => credentials.push(action.phone));
  }
  return state;
}
module.exports = reducer;
