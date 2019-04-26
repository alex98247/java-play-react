import React, {Component} from 'react';
import Menu from "./Menu";
import * as actions from "./actions"
import {connect} from "react-redux";
import {Button} from "reactstrap";

class AdminPanel extends Component {

  state = {
    claims: []
  };

  constructor(props) {
    super(props);
  }

  async componentDidMount() {
    const response = await fetch('/api/claim');
    const body = await response.json();
    this.setState({claims: body});
  }

  async solved(claim){
    await fetch('/api/claim', {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(claim),
      credentials: 'include'
    }).then(async res => {
      if (await res.ok) {
      }
      else {}
    });
  }

  render() {

    const claims = this.state.claims;
    const claimList = claims.map(claim => {
      return (
        <tr>
          <td>{claim.id}</td>
          <td>{claim.theme}</td>
          <td>{claim.comment}</td>
          <td><Button onClick={this.solved(claim)} className="btn btn-success">Solved</Button></td>
        </tr>);
    });

    return (
      <div>
        <Menu {...this.props}/>
        <table style={{marginBottom: 0}} className="table table-dark">
          <thead>
          <tr>
            <th>Id</th>
            <th>Theme</th>
            <th>Comment</th>
            <th></th>
          </tr>
          </thead>
          <tbody>
          {claimList}
          </tbody>
        </table>
        <div align="center" width="100%" className="bg-dark">
        </div>
      </div>
    );
  }
};

function mapStateToProps(state) {
  return {
    credentials: state.credentials
  };
}

export default connect(mapStateToProps, actions)(AdminPanel);
