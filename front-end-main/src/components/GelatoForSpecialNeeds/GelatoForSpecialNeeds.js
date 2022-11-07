import React, { useState, useEffect } from "react";
import ProductsContainer from "../ProductsContainer/ProductsContainer";
import image2 from "../resources2/Group 1446.png";

function GelatoForSpecialNeeds() {
  const [products, setProducts] = useState(null);

  useEffect(() => {
    (async () => {
      const response = await fetch(
        "https://backend-bbtmigkjaa-ue.a.run.app:443/products?categoryId=2"
      );
      const data = await response.json();
      setProducts(data);
    })();
  }, []);

  return (
    <>
      <div className="text-to-image">
        <img src={image2} alt="donut area" className="image-style" />
        <div className="centered">GELATO FOR SPECIAL NEEDS</div>
      </div>
      <h2 className="title-container">TOP SELLING GELATO FOR SPECIAL NEEDS </h2>
      {products && <ProductsContainer products={products} />}
    </>
  );
}

export default GelatoForSpecialNeeds;
