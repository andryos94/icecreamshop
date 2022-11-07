import React from "react";
import "./ProductCard.css";
// import Card from "@mui/material/Card";
// import CardContent from "@mui/material/CardContent";
// import CardMedia from "@mui/material/CardMedia";
// import Typography from "@mui/material/Typography";
// import { Button, CardActions } from "@mui/material";

const ProductCard = (props) => {
  const { title, description, price, img } = props;
  return (
    <div className="product-card">
      <div className="image-container">
        <img src={img} alt="productCard" />
      </div>
      <div className="card-body">
        <div className="card-title">{title}</div>
        <div className="card-description">{description}</div>
        <div className="card-price">{price}</div>
        <div className="card-button">Add to basket</div>
      </div>

      {/* <Card sx={{ width: 500 }}>
        <CardMedia
          component="img"
          height="350"
          image={`/images/${id}.png`}
          alt="donuts"
        />
        <CardContent>
          <Typography
            gutterBottom
            variant="h5"
            component="div"
            className="card-text-style"
          >
            {title}
          </Typography>
          <Typography
            variant="body2"
            color="text.secondary"
            className="card-description-style"
          >
            {description}
          </Typography>
          <Typography className="card-price-style">{price}</Typography>
        </CardContent>
        <CardActions>
          <Button size="big" variant="contained" className="card-button-style">
            Add to basket
          </Button>
        </CardActions>
      </Card> */}
    </div>
  );
};

export default ProductCard;
