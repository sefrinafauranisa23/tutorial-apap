import React from "react";
import Button from "../button";
import classes from "./styles.module.css";
const Item = (props) => {
  const { id, title, price, description, category, quantity, handleEdit, handleDelete, inCart, handleAdd, qCart } = props;
  const handleSubmit = (value) => {
    this.props.qCart(value);
  };

  return (
    <div className={classes.item}>
      <h3>{`ID ${id}`}</h3>
      <p>{`Nama Barang: ${title}`}</p>
      <p>{`Harga: ${price}`}</p>
      <p>{`Deskripsi: ${description}`}</p>
      <p>{`Kategori: ${category}`}</p>
      <p>{`stok: ${quantity}`}</p>
      <Button action={handleEdit}>Edit</Button>
      <Button action={handleDelete}>Delete</Button>
      <form>
        <input type="number" placeholder="Type quantity" value={qCart} onChange={(e) => handleSubmit(e.target.value)} />
        <Button action={handleAdd}>Add To Cart</Button>
      </form>
    </div>
  );
};
export default Item;
