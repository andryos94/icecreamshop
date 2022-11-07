import React, { useState, useEffect } from "react";
import ProductsContainer from "../ProductsContainer/ProductsContainer";
import image2 from "../resources2/Group 1446.png";
import "./Donuts.css";

function Donuts() {
  const [products, setProducts] = useState(null);

  useEffect(() => {
    (async () => {
      const response = await fetch(
        "https://backend-bbtmigkjaa-ue.a.run.app:443/products?categoryId=3"
      );
      const data = await response.json();
      setProducts(data);
    })();
  }, []);

  return (
    <>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">DONUTS</div>
      </div>
      <h2 className="title-container">All DONUTS PRODUCT </h2>
      {products && <ProductsContainer products={products} />}
    </>
  );
}

export default Donuts;

/*
import React from "react";
import image2 from "../resources2/Group 1446.png";
import "./Donuts.css";
import ButtonGroup from "@mui/material/ButtonGroup";
import Button from "@mui/material/Button";
//import ProductContainerDonuts from "../ProductsContainer/ProductContainerDonuts";
//import ProductContainerDonutsAll from "../ProductsContainer/ProductContainerDonutsAll";
import ProductsContainer from "../ProductsContainer/ProductsContainer";

const Donuts = () => {
  return (
    <div>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">DONUTS</div>
      </div>
      <h2 className="title-container">TOP SELLING DONUTS</h2>
      <ProductsContainer category="Top selling" />
      <div className=".cards-container"></div>

      <h2 className="title-container">All DONUTS PRODUCTS</h2>
      <ProductsContainer category="Top selling" />
      <div className="cards-container"></div>

      <div className="cards-container"></div>
      <div className="custom-button-group">
        <ButtonGroup
          variant="outlined"
          aria-label="outlined primary button group"
        >
          <Button className="previous-button">Previous</Button>
          <Button className="first-donut-page-button">1</Button>
          <Button className="next-button">2</Button>
          <Button className="next-button">3</Button>
          <Button className="next-button">Next</Button>
        </ButtonGroup>
      </div>
    </div>
  );
};

export default Donuts;
*/
