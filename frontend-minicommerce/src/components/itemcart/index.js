import React, { Component } from "react";
import CartItem from "../cartItem/index.js";
// import Button from "../button";
import APIConfig from "../../api/APIConfig.js";
import classes from "./styles.module.css";
import { Link } from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import Button from "@material-ui/core/Button";
import PointOfSaleIcon from "@mui/icons-material/PointOfSale";

class ItemCart extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
    };
    this.handleDelete = this.handleDelete.bind(this);
    this.loadDataCart = this.loadDataCart.bind(this);
  }

  componentDidMount() {
    this.loadDataCart();
  }

  async loadDataCart() {
    try {
      const { data } = await APIConfig.get("/cart");
      this.setState({ items: data.result });
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async handleDelete() {
    try {
      const { data } = await APIConfig.get("/cart/checkout");
      alert(data);
      this.loadDataCart();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  render() {
    return (
      <>
        <div>
          <div className={classes.itemList}>
            <h1 className={classes.title}>Cart Items</h1>
            <div style={{ position: "fixed", top: 25, left: 25 }}>
              <Link to="/list-item" style={{ textDecoration: "none" }}>
                <Button variant="contained" color="primary">
                  <ArrowBackIcon></ArrowBackIcon>
                  BACK
                </Button>
              </Link>
            </div>
          </div>
        </div>
        <div className="list-group">
          {this.state.items.length === 0 ? (
            <div className="text-center">
              <h4>Belum ada item yang dipilih</h4>
            </div>
          ) : (
            <div style={{ position: "fixed", top: 25, right: 25 }}>
              <Button variant="contained" color="primary" onClick={this.handleDelete}>
                <PointOfSaleIcon></PointOfSaleIcon>
                CHECKOUT
              </Button>
            </div>
          )}
          {this.state.items.map((item) => (
            <CartItem key={item.id} id={item.id} title={item.item.title} price={item.item.price} description={item.item.description} category={item.item.category} quantity={item.quantity} />
          ))}
        </div>
      </>
    );
  }
}

export default ItemCart;
