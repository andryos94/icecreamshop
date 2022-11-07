import React from "react";
import "./Home.css";

import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import { ReactComponent as NextButton } from "../../components/resources2/Group 1340.svg";
import ButtonGroup from "@mui/material/ButtonGroup";
import ProductsContainer from "../../components/ProductsContainer/ProductsContainer";
import Hero from "../../components/Hero/Hero";
import donuts from "../../MockData/donuts";

function Home(props) {
  return (
    <div>
      <div className="">
        <Hero />
      </div>

      {/* Top selling ice cream */}
      <div className="allign-text">
        <Button disabled>
          <h2 className="title-icecream">TOP SELLING ICE CREAM &amp; GELATO</h2>
        </Button>
        <Button component={Link} to="./Gelato" className="btn-icon">
          <h2 className="button-next">EXPLORE ALL </h2>
          <NextButton />
        </Button>
      </div>
      <ProductsContainer
        products={donuts.filter(({ category }) => category === "Gelato")}
      />
      {/* Top selling donuts */}
      <div className="allign-text">
        <Button disabled>
          <h2 className="title-icecream">TOP SELLING DONUTS </h2>
        </Button>
        <Button component={Link} to="../Donuts" className="btn-icon">
          <h2 className="button-next">EXPLORE ALL </h2>
          <NextButton />
        </Button>
      </div>
      <ProductsContainer
        products={donuts.filter(({ category }) => category === "Top selling")}
      />

      {/* Merchandise */}
      <div className="allign-text">
        <Button disabled>
          <h2 className="title-icecream">MERCHANDISE</h2>
        </Button>
      </div>
      <ProductsContainer
        products={donuts.filter(({ category }) => category === "Merch")}
      />

      {/* pagination component */}
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
}

export default Home;
