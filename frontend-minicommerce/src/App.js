import React, { Component } from "react";
import Layout from "./components/layout";
import ItemList from "./containers/itemlist";
import ItemCart from "./components/itemcart";
import { Redirect } from "react-router";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

class App extends Component {
  render() {
    return (
      <Layout>
        <Router>
          <Switch>
            <Route exact path="/list-item" component={ItemList} />
            <Route exact path="/list-cart" component={ItemCart} />
            <Route exact path="/">
              <Redirect to="/list-item" />
            </Route>
          </Switch>
        </Router>
      </Layout>
    );
  }
}
export default App;
