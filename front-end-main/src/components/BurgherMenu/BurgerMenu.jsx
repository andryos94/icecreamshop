import React, { useState } from "react";
import "./BurgerMenu.style.css";
//import ReorderIcon from "@material-ui/icons/Reorder";
import { HamburgerMenuIcon } from "../shared/icons/HamburgerMenuIcon";
import { ClosedIcon } from "../shared/icons";

const BurgerMenu = () => {
  const [showLinks, setShowLinks] = useState(false);
  return (
    <div className="Navbar">
      <div className="leftSide">
        <div className="links" id={showLinks ? "hidden" : ""}>
          <div className="title-menu" onClick={() => setShowLinks(!showLinks)}>
            MENU
            <ClosedIcon className="close-menu" />
          </div>
          <div className="list-menu">
            <a href="home">HOME</a>
            <a href="gelato">GELATO</a>
            <a href="gelatoforspecial">GELATO FOR SPECIAL NEEDS</a>
            <a href="donuts">DONUTS</a>
            <a href="donutsforspecial">DONUTS FOR SPECIAL NEEDS</a>
          </div>
        </div>
        <button onClick={() => setShowLinks(!showLinks)} className="btn-burger">
          <HamburgerMenuIcon />
          <div>Menu</div>
        </button>
      </div>
    </div>
  );
};

export default BurgerMenu;
