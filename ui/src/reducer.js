const initialState = {
  credentials: {
    username: '',
    password: '',
    token: '',
    status: 200
  }
}

export default function (state = initialState, action) {
  switch (action.type) {
    case "ADD_CREDENTIALS":
      console.log(action.credentials);
      return {
        credentials: {
          username: action.credentials.username,
          password: action.credentials.password,
          token: action.credentials.token,
          status: action.credentials.status
        }
      }
    default:
      return state;
  }
}
