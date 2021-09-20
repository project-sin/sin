import React, {useEffect, useState} from 'react';
import styled from "styled-components";
import FindProductListApi from "./FindProductListApi";
import queryString from "query-string";

const ProductsListWrap = styled.div`
  width: 1050px;
  margin: 0 auto;
  border: 2px solid rgb(90, 155, 213);
  padding: 10px;
`;
const Container = styled.div`
  display: inline-block;
  width: 320px;
  margin:11px;
  height: 500px;
  border: 2px solid rgb(90, 155, 213);
`;
const ProductImg = styled.img`
  display: inline-block;
  width: 320px;
  height: 400px;
 
`;

const ProductList = (props) => {
  const category = queryString.parse(props.location.search).category;

  const [products,setProducts] = useState(null);

  useEffect(()=> {
    FindProductListApi(category).then(prodictPromises => {
      setProducts(prodictPromises)
    });
  },[]);

  const productLists = products ? products.map((product)=>{
    return <Container><ProductImg src= {product.imageUrl} /></Container>;
  }) : "";

console.log(products)
  return (
      <ProductsListWrap>
        {productLists}
      </ProductsListWrap>
  );
};

export default ProductList;