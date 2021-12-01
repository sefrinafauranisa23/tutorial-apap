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
      qCart: 0,
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
    this.loadData = this.loadData.bind(this);
  }

  componentDidMount() {
    this.loadData();
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
    this.setState({ isCrzeate: true });
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

  handleAddItemToCart(item) {
    const newItems = [...this.state.cartItems];
    const newItem = { ...item };
    const targetInd = newItems.findIndex((it) => it.id === newItem.id);
    if (targetInd < 0) {
      if (item.quantity >= this.state.qCart) {
        newItem.inCart = true;
        newItems.push(newItem);
        this.updateShopItem(newItem, true);
        item.quantity -= this.state.qCart;
      } else {
        alert("Stok tidak cukup");
      }
    }
    this.setState({ cartItems: newItems });
  }

  updateShopItem = (item, inCart) => {
    const tempShopItems = this.state.items;
    const targetInd = tempShopItems.findIndex((it) => it.id === item.id);
    tempShopItems[targetInd].inCart = inCart;
    this.setState({ shopItems: tempShopItems });
  };

  async searchItems(searchValue) {
    console.log(searchValue);
    const { data } = await APIConfig.get("/item");
    const item = data.result;
    this.setState({ searchInput: searchValue });
    const baru = item.filter((val) => val.title.toLowerCase().includes(this.state.searchInput.toLowerCase()));
    this.setState({ items: baru });
  }

  render() {
    return (
      <div className="container pt-3">
        <div className="row mt-3">
          {!this.state.cartHidden ? (
            <div className="col-sm">
              <div className={classes.itemList}>
                <h1 className={classes.title}>Cart Items</h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                  <Button variant="contained">CHECKOUT</Button>
                </div>
                <div style={{ position: "fixed", top: 25, left: 25 }}>
                  <Button variant="contained">BACK</Button>
                </div>
              </div>
            </div>
          ) : (
            <div className={classes.itemList}>
              <h1 className={classes.title}>All Items</h1>
              <div style={{ position: "fixed", top: 25, right: 25 }}>
                <Fab variant="extended" onClick={this.handleToggle}>
                  {this.state.cartHidden ? (
                    <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                      <ShoppingCartIcon />
                    </Badge>
                  ) : (
                    <ViewStreamIcon />
                  )}
                </Fab>
              </div>
              <input type="text" placeholder="Search" value={this.state.searchInput} onChange={(e) => this.searchItems(e.target.value)} />
              {/* <CInputGroup className="mb-3">
          <CFormInput placeholder="Type title of product" aria-label="title" aria-describedby="basic-addon1" value={this.state.searchInput} onChange={(e) => this.searchItems(e.target.value)} />
        </CInputGroup> */}
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
                    handleAdd={() => this.handleAddItemToCart(item)}
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
          )}
        </div>
      </div>
    );
  }
}
export default ItemList;
