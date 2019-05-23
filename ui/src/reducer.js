export const initialState = {
  credentials: {
    id: '',
    username: '',
    password: '',
    token: '',
    status: 200,
    roles: []
  },

  pageGames: {
    pageNumber: 1,
    gameList: []
  }
}

export default function (state = initialState, action) {
  switch (action.type) {
    case "ADD_CREDENTIALS":
      return {
        credentials: action.credentials,
        pageGames: state.pageGames

      }
    case "SET_GAMES":
      return {
        credentials: state.credentials,
        pageGames: action.pageGames
      }
    default:
      return state;
  }
}
