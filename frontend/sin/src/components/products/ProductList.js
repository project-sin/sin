import React from 'react';
import styled from "styled-components";

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

const ProductList = () => {
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