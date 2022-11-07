import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Home from "./Pages/Home/Home";
import Gelato from "./components/Gelato/Gelato";
import GelatoForSpecialNeeds from "./components/GelatoForSpecialNeeds/GelatoForSpecialNeeds";
import DonutsForSpecialNeeds from "./components/DonutsForSpecialNeeds/DonutsForSpecialNeeds";

import Account from "./components/Account/Account";
import Basket from "./components/Basket/Basket.js";
import "./App.css";
import Navbar from "./Navbar/Navbar";
import Footer from "./components/Footer/Footer";
import Donuts from "./components/Donuts/Donuts";

const App = () => {
  return (
    <Router>
      <Navbar />

      <Routes>
        <Route path="/" element={<Home />} exact />
        {/* <Route path="/gelato" element={<Gelato />} exact /> */}
        <Route path="/gelato" element={<Gelato />} exact />
        <Route
          path="/gelatoforspecialneeds"
          element={<GelatoForSpecialNeeds />}
          exact
        />
        {/* <Route path="/donuts" element={<Donuts />} exact /> */}
        <Route path="/donuts" element={<Donuts />} exact />
        <Route
          path="/donutsforspecialneeds"
          element={<DonutsForSpecialNeeds />}
          exact
        />
        <Route path="/account" element={<Account />} exact />
        <Route path="/basket" element={<Basket />} exact />
      </Routes>

      <Footer />
    </Router>
  );
};

export default App;
