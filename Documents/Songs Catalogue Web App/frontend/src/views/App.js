import React, { Component } from "react";
import "../css/App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import {Songs} from "./Songs";
import {AddSong} from "./AddSong";
import 'bootstrap/dist/css/bootstrap.min.css';
import {Login} from "./Login";


class App extends Component {
  render() {

    return (
        <BrowserRouter>
          <Routes>
              <Route path="/users/catalogue/songs" exact={true} element={<Songs/>}/>
              <Route path="/users/catalogue/songs/add" exact={true} element={<AddSong/>}/>
              <Route path="/login" element={<Login/>} />
          </Routes>
        </BrowserRouter>
    )
  }

}

export default App;