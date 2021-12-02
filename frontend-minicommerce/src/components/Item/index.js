import React, { useState } from "react";
import Button from "../button";
import classes from "./styles.module.css";
const Item = (props) => {
  const { id, title, price, description, category, quantity, handleEdit, handleDelete, handleAdd } = props;
  const [qCart, setQCart] = useState(0);

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
      <div>
        <input type="number" placeholder="Type quantity" value={qCart} onChange={(e) => setQCart(e.target.value)} />
        <Button action={() => handleAdd(id, qCart, quantity)}>Add To Cart</Button>
      </div>
    </div>
  );
};
export default Item;
