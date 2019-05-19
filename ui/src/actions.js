export function addCredentials (credentials) {
  return {
    type: "ADD_CREDENTIALS",
    credentials
  }
};

export function setGames (pageGames) {
  return {
    type: "SET_GAMES",
    pageGames
  }
};
