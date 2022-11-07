import React from "react";
import "./Hero.css";
//import Gelato from "./images/firstone.png";
//import Donut from "./images/secondone.png";
//import Family from "./images/thirdone.png";
import { Link } from "react-router-dom";
//import { Link } from "@mui/material";

const Hero = (props) => {
  return (
    <div className="container-card class">
      <div className="card-one">
        <p className="text-card">Ice Cream &amp; Gelato</p>
        <Link to="gelato">
          <button className="btn">EXPLORE OPTION</button>
        </Link>
      </div>
      <div className="card-two">
        <p className="text-card">Delicious Donuts</p>
        <Link to="donuts">
          <button className="btn">EXPLORE OPTION</button>
        </Link>
      </div>
      <div className="card-three">
        <p className="text-card">Family/Corporate</p>
        <Link to="">
          <button className="btn">EXPLORE OPTION</button>
        </Link>
      </div>
    </div>
  );
};
export default Hero;
