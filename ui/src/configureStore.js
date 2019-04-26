import { persistStore, persistReducer } from 'redux-persist'
import { createStore } from 'redux'
import reducer from "./reducer";
import * as redux from "redux";
import storage from 'redux-persist/lib/storage' // defaults to localStorage for web and AsyncStorage for react-native

var store = redux.createStore(reducer);

const persistConfig = {
  key: 'root',
  storage,
}
const persistedReducer = persistReducer(persistConfig, reducer);

export default () => {
  let store = createStore(persistedReducer)
  let persistor = persistStore(store)
  return { store, persistor }
}
