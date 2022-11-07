import React, { useState, useEffect } from "react";
import ProductsContainer from "../ProductsContainer/ProductsContainer";
import image2 from "../resources2/Group 1446.png";

function DonutsForSpecialNeeds() {
  const [products, setProducts] = useState(null);

  useEffect(() => {
    (async () => {
      const response = await fetch(
        "https://backend-bbtmigkjaa-ue.a.run.app:443/products?categoryId=4"
      );
      const data = await response.json();
      setProducts(data);
    })();
  }, []);

  return (
    <>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">DONUTS FOR SPECIAL NEEDS</div>
      </div>
      <h2 className="title-container">TOP SELLING GELATO FOR SPECIAL NEEDS </h2>
      {products && <ProductsContainer products={products} />}
    </>
  );
}

export default DonutsForSpecialNeeds;

/*
import React from 'react'

const GelatoForSpecialNeeds = () => {
    return  <div>
    <h1>This is donuts for special needs page</h1>
        </div>;
}

export default GelatoForSpecialNeeds
*/
