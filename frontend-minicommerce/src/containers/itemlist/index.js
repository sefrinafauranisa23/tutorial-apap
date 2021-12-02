import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import { Fab } from "@material-ui/core";
import ViewStreamIcon from "@mui/icons-material/ViewStream";
import CartItem from "../../components/cartItem";
import { ProductionQuantityLimitsOutlined } from "@mui/icons-material";
import ItemCart from "../../components/itemcart";
import { Redirect } from "react-router";
import { Link } from "react-router-dom";

class ItemList extends Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
      isLoading: false,
      isCreate: false,
      isEdit: false,
      id: "",
      title: "",
      price: 0,
      description: "",
      category: "",
      quantity: 0,
      searchInput: "",
      totalHarga: 0,
      cartItems: [],
      cartHidden: true,
    };
    this.handleClickLoading = this.handleClickLoading.bind(this);
    this.handleAddItem = this.handleAddItem.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
    this.handleChangeField = this.handleChangeField.bind(this);
    this.handleEditItem = this.handleEditItem.bind(this);
    this.handleSubmitItem = this.handleSubmitItem.bind(this);
    this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
    this.handleAddItemToCart = this.handleAddItemToCart.bind(this);
    this.handleDeleteItem = this.handleDeleteItem.bind(this);
    this.loadData = this.loadData.bind(this);
    this.loadDataCart = this.loadDataCart.bind(this);
  }

  componentDidMount() {
    this.loadData();
    this.loadDataCart();
  }

  handleToggle = () => {
    const cartHidden = this.state.cartHidden;
    this.setState({ cartHidden: !cartHidden });
  };

  async loadData() {
    try {
      const { data } = await APIConfig.get("/item");
      this.setState({ items: data.result });
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async loadDataCart() {
    try {
      const { data } = await APIConfig.get("/cart");
      this.setState({ cartItems: data.result });
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async handleSubmitItem(event) {
    event.preventDefault();
    try {
      const data = {
        title: this.state.title,
        price: this.state.price,
        description: this.state.description,
        category: this.state.category,
        quantity: this.state.quantity,
      };
      await APIConfig.post("/item", data);
      this.setState({
        title: "",
        price: 0,
        description: "",
        category: "",
        quantity: 0,
      });
      this.loadData();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
    this.handleCancel(event);
  }

  async handleSubmitEditItem(event) {
    event.preventDefault();
    try {
      const data = {
        title: this.state.title,
        price: this.state.price,
        description: this.state.description,
        category: this.state.category,
        quantity: this.state.quantity,
      };
      await APIConfig.put(`/item/${this.state.id}`, data);
      this.setState({
        id: "",
        title: "",
        price: 0,
        description: "",
        category: "",
        quantity: 0,
      });
      this.loadData();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
    this.handleCancel(event);
  }

  shouldComponentUpdate(nextProps, nextState) {
    console.log("shouldComponentUpdate()");
    return true;
  }

  handleAddItem() {
    this.setState({ isCreate: true });
  }

  handleCancel(event) {
    event.preventDefault();
    this.setState({ isCreate: false, isEdit: false });
  }

  handleChangeField(event) {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  handleClickLoading() {
    const currentLoading = this.state.isLoading;
    this.setState({ isLoading: !currentLoading });
    console.log(this.state.isLoading);
  }

  handleEditItem(item) {
    this.setState({
      isEdit: true,
      id: item.id,
      title: item.title,
      price: item.price,
      description: item.description,
      category: item.category,
      quantity: item.quantity,
    });
  }

  async handleAddItemToCart(id, qCart, quantity) {
    const newItems = this.state.cartItems;
    const baru = newItems.filter((it) => it.item.id === id)[0];
    const q = baru ? baru.quantity : 0;
    console.log(quantity);
    console.log(q + qCart);
    console.log(qCart);
    try {
      if (quantity - qCart - q >= 0) {
        const data = {
          idItem: id,
          quantity: qCart,
        };
        await APIConfig.post("/cart", data);
        this.loadDataCart();
        alert("Item behasil ditambahkan ke cart");
      } else {
        alert("Stok tidak memenuhi");
      }
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  async searchItems(searchValue) {
    console.log(searchValue);
    const { data } = await APIConfig.get("/item");
    const item = data.result;
    this.setState({ searchInput: searchValue });
    const baru = item.filter((val) => val.title.toLowerCase().includes(this.state.searchInput.toLowerCase()));
    this.setState({ items: baru });
  }

  async handleDeleteItem(item) {
    try {
      await APIConfig.delete("/item/" + item.id);
      this.loadData();
    } catch (error) {
      alert("Oops terjadi masalah pada server");
      console.log(error);
    }
  }

  render() {
    return (
      <div className="container pt-3">
        <div className="row mt-3">
          <div className={classes.itemList}>
            <h1 className={classes.title}>All Items</h1>
            <div style={{ position: "fixed", top: 25, right: 25 }}>
              <Link to="/list-cart">
                <Fab variant="extended" onClick={this.handleToggle}>
                  <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                    <ShoppingCartIcon />
                  </Badge>
                </Fab>
              </Link>
            </div>
            <input className={classes.textField} placeholder="Search" value={this.state.searchInput} onChange={(e) => this.searchItems(e.target.value)} />
            <Button action={this.handleAddItem}>Add Item</Button>
            <div>
              {this.state.items.map((item) => (
                <Item
                  key={item.id}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  description={item.description}
                  category={item.category}
                  quantity={item.quantity}
                  handleEdit={() => this.handleEditItem(item)}
                  handleDelete={() => this.handleDeleteItem(item)}
                  handleAdd={this.handleAddItemToCart}
                />
              ))}
            </div>
            <Modal show={this.state.isCreate || this.state.isEdit} handleCloseModal={this.handleCancel} modalTitle={this.state.isCreate ? "Add Item" : `Edit Item ID ${this.state.id}`}>
              <form>
                <input className={classes.textField} type="text" placeholder="Nama Item" name="title" value={this.state.title} onChange={this.handleChangeField} />
                <input className={classes.textField} type="number" placeholder="Harga" name="price" value={this.state.price} onChange={this.handleChangeField} />
                <textarea className={classes.textField} placeholder="Deskripsi" name="description" rows="4" value={this.state.description} onChange={this.handleChangeField} />
                <input className={classes.textField} type="text" placeholder="Kategori" name="category" value={this.state.category} onChange={this.handleChangeField} />
                <input className={classes.textField} type="number" placeholder="qty" name="quantity" value={this.state.quantity} onChange={this.handleChangeField} />
                <Button action={this.state.isCreate ? this.handleSubmitItem : this.handleSubmitEditItem}>Create</Button>
                <Button action={this.handleCancel}>Cancel</Button>
              </form>
            </Modal>
          </div>
        </div>
      </div>
    );
  }
}
export default ItemList;
