const initialState = {
  credentials: {
    id: '',
    username: '',
    password: '',
    token: '',
    status: 200,
    role: ''
  }
}

export default function (state = initialState, action) {
  switch (action.type) {
    case "ADD_CREDENTIALS":
      return {
        credentials: action.credentials
      }
    default:
      return state;
  }
}
