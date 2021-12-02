import React, { useState } from "react";
import Button from "../button";
import classes from "./styles.module.css";

const CartItem = (props) => {
  const { id, title, price, description, category, quantity, totalHarga } = props;

  return (
    <div className={classes.item}>
      <h3>{`ID ${id}`}</h3>
      <p>{`Nama Barang: ${title}`}</p>
      <p>{`Harga: ${price}`}</p>
      <p>{`Jumlah: ${quantity}`}</p>
      <p>{`Deskripsi: ${description}`}</p>
      <p>{`Kategori: ${category}`}</p>
      <p>
        <b>{`Total Harga: ${price * quantity}`}</b>
      </p>
    </div>
  );
};
export default CartItem;
