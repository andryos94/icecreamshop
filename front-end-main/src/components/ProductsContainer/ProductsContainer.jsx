import React from "react";
import ProductCard from "../ProductCard/ProductCard";
import "./ProductContainer.css";

const ProductsContainer = ({ products }) => {
  console.log(products);
  return (
    <div className="products-container">
      {products.map((result) => (
        <ProductCard
          key={result.id}
          id={result.id}
          title={result.title}
          description={result.shortDesc || result.description}
          price={result.price}
          img={result.photoUrl || result.img}
        />
      ))}
    </div>
  );
};

export default ProductsContainer;
