import React, {useEffect, useState} from 'react';
import styled from "styled-components";
import findProductListApi from "./FindProductListApi";
import queryString from "query-string";

const ProductsListWrap = styled.div`
  width: 1050px;
  margin: 0 auto;
  padding: 10px;
`;
const Container = styled.div`
  position: relative;
  display: inline-block;
  width: 320px;
  margin:11px;
  height: 500px;
  cursor: pointer;
`;
const ProductImg = styled.img`
  position: absolute;
  width: 320px;
  height: 340px;
`;

const ProductDetails = styled.div`
  position: absolute;
  top: 350px;
`;

const ProductName = styled.div`
  font-size: 25px;
`;

const ProductDiscountPercent = styled.div`
  display: inline-block;
  font-size: 20px;
  color: red;
  font-weight: bold;
`;

const ProductPrice = styled.div`
  display: inline-block;
  font-size: 20px;
  margin-left:10px;
  font-weight: bold;
`;

const ProductPrevPrice = styled.div`
  color: gray;
  text-decoration:line-through
`;

const ProductSummary = styled.div`
`;

const ProductList = (props) => {
  const category = queryString.parse(props.location.search).category;
  const list = queryString.parse(props.location.search).list;
  const [products,setProducts] = useState(null);

  useEffect(()=> {
    setProducts(null)
    findProductListApi(category, list).then(prodictPromises => {
      setProducts(prodictPromises)
    });
  },[category, list]);

  const productLists = products ? products.map((product)=>{
    return <Container>
             <ProductImg src= {product.imageUrl} />
             <ProductDetails>
               <ProductName>{product.name}</ProductName>
               <ProductDiscountPercent>{product.discountPercent}%</ProductDiscountPercent>
               <ProductPrice>{Math.floor(product.price * (1-product.discountPercent/100))}원</ProductPrice>
               <ProductPrevPrice>{product.price}원</ProductPrevPrice>
               <ProductSummary>{product.contentSummary ? product.contentSummary : "상품요약정보"}</ProductSummary>
             </ProductDetails>
           </Container>;
  }) : "";

console.log(products)
  return (
      <ProductsListWrap>
        {productLists}
      </ProductsListWrap>
  );
};

export default ProductList;