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

  async solved(claim) {
    var solvedClaim = claim;
    solvedClaim.solved = true;
    await fetch('/api/claim/' + solvedClaim.id, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(solvedClaim),
      credentials: 'include'
    }).then(async res => {

    });
    let indexClaim = [...this.state.claims].indexOf(claim);
    let updatedClaims = [...this.state.claims];
    updatedClaims[indexClaim].solved = 'solved';
    this.setState({claims: updatedClaims});
  }

  render() {

    const claims = this.state.claims;
    const claimList = claims.map(claim => {
      return (
        <tr>
          <td>{claim.id}</td>
          <td>{claim.theme}</td>
          <td>{claim.comment}</td>
          <td>{new Date(claim.created_at).toDateString()}</td>
          <td>{(claim.solved) ? "SOLVED" :
            <Button onClick={() => this.solved(claim)} className="btn btn-success">Solve</Button>}
          </td>
        </tr>
      );
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
            <th>Created date</th>
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
