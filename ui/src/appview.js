var React = require("react");
var connect = require("react-redux").connect;
var actions = require("./actions.js");

class AppView extends React.Component {

  render() {
    return <div>
      Hello!
    </div>
  }
};

function mapStateToProps(state) {
  return {
    credentials: state.get("credentials")
  };
}

export default connect(mapStateToProps, actions)(AppView);
