import React from 'react';
import { Route } from 'react-router-dom';
import { createGlobalStyle } from 'styled-components';
import Header from './components/Header';
import Home from './components/Home';
import List from './components/List';
import Item from './components/Item';
import Footer from './components/Footer';

const GlobalStyle = createGlobalStyle`
* {padding: 0; margin: 0;}
a {text-decoration: none; color: black;}

.clearfix{*zoom:1;}
.clearfix:before, .clearfix:after {display: block; content: '';line-height: 0;}
.clearfix:after {clear: both;}

ul,li,ol {list-style:none;}
`

function App() {
  return (
    <>
    <GlobalStyle />
    <Header />
    <Route path='/' exact component={Home} />
    <Route path='/list/:type' exact component={List} />
    <Route path='/item/:id' exact component={Item} />
    <Footer />
    </>
  );
}

export default App;
