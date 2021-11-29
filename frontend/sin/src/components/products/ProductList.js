import React, {useEffect, useState} from 'react';
import styled from "styled-components";
import findProductListApi from "../api/product/FindProductListApi";
import queryString from "query-string";
import Sort from "./Sort";
import Header from "../Header";

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
  const [products, setProducts] = useState(null);
  const [pageinfo, setPageinfo] = useState(null);
  const [productList, setProductList] = useState(null);

  const changeList = (List) => {
    setProductList(List.map((product) => {
      return <Container>
        <ProductImg src={product.imageUrl}/>
        <ProductDetails>
          <ProductName>{product.name}</ProductName>
          <ProductDiscountPercent>{product.discountPercent}%</ProductDiscountPercent>
          <ProductPrice>{Math.floor(product.price * (1 - product.discountPercent
              / 100))}원</ProductPrice>
          <ProductPrevPrice>{product.price}원</ProductPrevPrice>
          <ProductSummary>{product.contentSummary ? product.contentSummary
              : "상품요약정보"}</ProductSummary>
        </ProductDetails>
      </Container>;
    }))
  }

  useEffect(() => {
    setProducts(null)
    findProductListApi(category, list).then(prodictPromises => {
      setProducts(prodictPromises)
      changeList(prodictPromises);
    });
  }, [category, list]);

  useEffect(() => {
    if (pageinfo === "혜택순" && products != null) {
      setProducts(products.sort(function compareNumbers(a, b) {
        return b.discountPercent - a.discountPercent;
      }))
      changeList(products);
    }
    if (pageinfo === "추천순" && products != null) {
      setProducts(products.sort(function compareNumbers(a, b) {
        if (b.reviewCount !== a.reviewCount) {
          return b.reviewCount - a.reviewCount;
        } else {
          return b.price - a.price;
        }
      }))
      changeList(products);
    }
    if (pageinfo === "신상품순" && products != null) {
      setProducts(products.sort(function compareNumbers(a, b) {
        return new Date(b.createdDate) - new Date(a.createdDate);
      }))
      changeList(products);
    }
    if (pageinfo === "인기상품순" && products != null) {
      setProducts(products.sort(function compareNumbers(a, b) {
        if (b.reviewCount !== a.reviewCount) {
          return b.reviewCount - a.reviewCount;
        } else {
          return b.price - a.price;
        }
      }))
      changeList(products);
    }
    if (pageinfo === "낮은 가격순") {
      setProducts(products.sort(function compareNumbers(a, b) {
        return a.price - b.price;
      }))
      changeList(products);
    }
    if (pageinfo === "높은 가격순") {
      setProducts(products.sort(function compareNumbers(a, b) {
        return b.price - a.price;
      }))
      changeList(products);
    }
  }, [pageinfo])

  return (
      <>
        <Header/>
        <ProductsListWrap>
          <Sort
              products={products}
              category={category}
              pageinfo={pageinfo}
              setPageinfo={setPageinfo}
          />
          {productList}
        </ProductsListWrap>
      </>
  );
};

export default ProductList;