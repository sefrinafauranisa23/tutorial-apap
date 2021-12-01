import React from "react";
import Item from "../Item/index.js";

export default function ItemCart({ title, items, onItemClick, isShopList }) {
  return (
    <>
      <h3 style={StyleSheet.heading}>{title}</h3>
      <div className="list-group">
        {items.length === 0 ? (
          <div className="text-center">
            <h1>Belum ada item yang dipilih</h1>
          </div>
        ) : null}
        {items.map((item) => (
          <Item key={item.id} item={item} onChange={onItemClick} isShopList={isShopList} />
        ))}
      </div>
    </>
  );
}
