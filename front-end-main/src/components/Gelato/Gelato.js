import React, { useState, useEffect } from "react";
import ProductsContainer from "../ProductsContainer/ProductsContainer";
import image2 from "../resources2/Group 1446.png";
import "./Gelato.css";

function Gelato() {
  const [products, setProducts] = useState(null);

  useEffect(() => {
    (async () => {
      const response = await fetch(
        "https://backend-bbtmigkjaa-ue.a.run.app:443/products?categoryId=1"
      );
      const data = await response.json();
      setProducts(data);
    })();
  }, []);

  return (
    <>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">GELATO</div>
      </div>
      <h2 className="title-container">All GELATO PRODUCT </h2>
      {products && <ProductsContainer products={products} />}
    </>
  );
}

export default Gelato;

/*
import React from "react";
//import Cards from "../components/Cards/Cards";
import image2 from "../resources2/Group 1446.png";
//import { getListProducts } from '../../api'
//import { useState,useEffect } from 'react'

import ProductContainerGelatoIce from "../ProductsContainer/ProductContainerGelatoIce";

const Gelato = () => {
  return (
    <div>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">GELATO</div>
      </div>
      <h2 className="title-container">TOP SELLING ICE CREAM &amp; GELATO</h2>
      <div className="card-container">
        <ProductContainerGelatoIce />
      </div>
    </div>
  );
};

export default Gelato;
*/
