import React from "react";
import { Link, NavLink } from "react-router-dom";
import BurgerMenu from "../components/BurgherMenu/BurgerMenu";
//import BurgerMenu from "../components/BurgerMenu/BurgerMenu";
//import BurgerMenu from "../components/BurgerMenu/BurgerMenu";
import Logo from "../components/resources2/gelato&donuts.svg";
import "./Navbar.css";
//import logo from "../components/resources2/account.svg";
import AccountIcon from "./NavbarIcons/AccountIcon";
import BasketIcon from "./NavbarIcons/BasketIcon";
//import basket from "../components/resources2/basket.svg";

const Navbar = () => {
  return (
    <nav className="header">
      <BurgerMenu className="btn-menu" />

      {/*left side -  Navbar */}
      <div className="left-nav">
        <Link to="/">
          <img className="img-logo" src={Logo} about alt="gelato-donuts" />
        </Link>

        <NavLink to="/gelato" className="mobile-style" activeClassName="active">
          Gelato
        </NavLink>
        <NavLink
          to="/gelatoforspecialneeds"
          className="gelatoforspecialneeds mobile-style"
          activeClassName="active"
        >
          Gelato for special needs
        </NavLink>
        <NavLink to="/donuts" className="mobile-style" activeClassName="active">
          Donuts
        </NavLink>
        <NavLink
          to="/donutsforspecialneeds"
          className="mobile-style"
          activeClassName="active"
        >
          Donuts for special needs
        </NavLink>
      </div>

      {/*right side -  Account and Basket */}
      <div className="right-nav">
        {/* Add-Hero-component */}

        <NavLink to="/account" className="account" activeClassName="active">
          <AccountIcon />
          {/* <img src={logo} className="hovering" about alt="logoaccount" /> */}
          <div>Account</div>
        </NavLink>
        <NavLink to="/basket" className="basket " activeClassName="active">
          <BasketIcon />
          {/* <img src="/icons/basket.svg" className="hovering" about alt="logoaccount" /> */}

          <div>Basket</div>
        </NavLink>
      </div>
    </nav>
  );
};

export default Navbar;
