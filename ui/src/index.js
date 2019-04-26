import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {Provider} from "react-redux";
import { PersistGate } from 'redux-persist/integration/react';
import configureStore from "./configureStore";

var store = configureStore();

ReactDOM.render(
  <Provider store={store.store}>
    <PersistGate loading={null} persistor={store.persistor}>
      <App/>
    </PersistGate>
  </Provider>,
  document.getElementById('root'));
serviceWorker.register();
