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

const ProductList = (props) => {
  const category = queryString.parse(props.location.search).category;

  const [products,setProducts] = useState(null);

  useEffect(()=> {
    FindProductListApi(category).then(prodictPromises => {
      setProducts(prodictPromises)
    });
  },[]);

  return (
      <ProductsListWrap>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
        <Container>ㅎㅇㅎㅇ</Container>
      </ProductsListWrap>
  );
};

export default ProductList;