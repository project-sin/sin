import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import Header from "../Header";
import SelectFunc from "./SelectFunc";
import StyledCheckbox from "./StyledCheckbox";
import StyledElement from "./StyledElement";
import findNonMemberCartApi from "../api/cart/FindNonMemberCartApi";
import {ACCESS_TOKEN} from "../../constants/Sessionstorage";
import findMemberCartApi from "../api/cart/FindMemberCartApi";
import deleteCartProductAPi from "../api/cart/DeleteCartProductAPi";
import minusCountInCartApi from "../api/cart/MinusCountInCartApi";
import plusCountInCartApi from "../api/cart/PlusCountInCartApi";

const Shoppinglistwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``
const Shoppinglisttop = styled.div`font-size: 30px; text-align: center; padding: 50px 0px;`
const Shoppinglistmain = styled.div`min-height: 550px;`
const Shoppinglistleft = styled.div`position: relative;float: left; width: 70%; min-height: 300px;`
const ProductName = styled.div`position: absolute; left: 100px;bottom: 40px;width:360px;`
const Calculator = styled.div`
  position: absolute;
  bottom:38px;
  right: 160px;
  width: 100px;
  height: 30px;
  font-weight: bold;
  border: 1px solid rgb(200,200,200);
  -webkit-user-select:none; 
  -moz-user-select:none; 
  -ms-user-select:none; 
  user-select:none
`;

const MinusButton = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  top: -18px;
  left: 7px;
  font-size: 40px;
`;

const Count = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  bottom: 0;
  left: 41px;
  font-size: 20px;
  width: 30px;
  height:30px;
`;

const PlusButton = styled.div`
  position: absolute;
  display: inline-block;
  cursor: pointer;
  font-size: 30px;
  right: 4px;
  bottom: 0;
`;

const Price = styled.div`
  position: absolute;
  bottom:38px;
  right: 60px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  text-align: right;
  font-weight: bold;
`;

const Delete = styled.div`
  position: absolute;
  bottom:38px;
  right: -55px;
  width: 100px;
  height: 30px;
  font-size: 15px;
  color: rgb(150,150,150);
  cursor: pointer;
`

const NonProducts = styled.div`
  position: absolute;
  font-size: 20px;
  top:160px;
  left: 25%;
`
const Shoppinglistright = styled.div`float: left; width: 28%; height: 300px; margin-top: 20px; border: 1px solid #dbdbdb;`

const Shoppinglist = () => {
  const [checkedProductCodes, setCheckedProductCodes] = useState([]);
  const [products, setProducts] = useState([]);
  const [count, setCount] = useState([]);
  const [totalPrice, setTotalPrice] = useState(0);

  const accessToken = sessionStorage.getItem(ACCESS_TOKEN);

  useEffect(() => {
    if (accessToken) {
      findMemberCartApi(accessToken).then((productPromises) => {
        setProducts(productPromises)
        productPromises.map((product) => {
          setCount((count) => [...count, {
            productCode: product.productCode,
            count: product.cnt
          }])
        })
      })
    } else {
      let query = ""
      for (let productCode in sessionStorage) {
        if (isNaN(productCode)) {
          break;
        } else {
          query += productCode + ",";
          setCount((count) => [...count, {
            productCode: productCode,
            count: sessionStorage.getItem(productCode)
          }])
        }
      }
      if (query != "") {
        findNonMemberCartApi(query).then((productPromises) => {
          setProducts(productPromises)
        })
      }
    }
  }, []);
  const onProductCheckboxClicked = (productCode) => {
    if (checkedProductCodes.includes(productCode)) {
      setCheckedProductCodes(checkedProductCodes.filter(e => e !== productCode))
    } else {
      setCheckedProductCodes([...checkedProductCodes, productCode]);
    }
  };

  const productsPresent = products.length !== 0 ? products.map((e, idx) =>
      <StyledElement>
        <StyledCheckbox
            onChange={() => onProductCheckboxClicked(products[idx].productCode)}
            checked={checkedProductCodes.includes(products[idx].productCode)}/>
        <img style={{height: '85px', width: "60px"}}
             src={products[idx].imageUrl}/>
        <ProductName>{products[idx].name}</ProductName>
        <Calculator>
          <MinusButton onClick={() => {
            if (accessToken) {
              let productCode = products[idx].productCode
              minusCountInCartApi({
                productCode, accessToken
              });
              setCount(count.map((product) => {
                if (product.productCode === products[idx].productCode) {
                  return {
                    productCode: product.productCode,
                    count: product.count > 1 ? product.count - 1 : product.count
                  };
                } else {
                  return {
                    productCode: product.productCode,
                    count: product.count
                  };
                }
              }));
            } else if (sessionStorage.getItem(products[idx].productCode) > 1) {
              sessionStorage.setItem(products[idx].productCode,
                  Number(sessionStorage.getItem(products[idx].productCode))
                  - 1);
              setCount(count.map((product) => {
                if (product.productCode === products[idx].productCode) {
                  return {
                    productCode: product.productCode,
                    count: product.count - 1
                  };
                } else {
                  return {
                    productCode: product.productCode,
                    count: product.count
                  };
                }
              }))
            }
          }}>-</MinusButton>
          <Count> {count.map((product) => {
            if (product.productCode === products[idx].productCode) {
              return product.count
            }
          })}</Count>
          <PlusButton onClick={() => {
            if (accessToken) {
              let productCode = products[idx].productCode
              plusCountInCartApi({
                productCode, accessToken
              });
              setCount(count.map((product) => {
                if (product.productCode === products[idx].productCode) {
                  return {
                    productCode: product.productCode,
                    count: Number(product.count) + 1
                  };
                } else {
                  return {
                    productCode: product.productCode,
                    count: product.count
                  };
                }
              }));
            } else {
              sessionStorage.setItem(products[idx].productCode,
                  Number(sessionStorage.getItem(products[idx].productCode))
                  + 1);
              setCount(count.map((product) => {
                if (product.productCode === products[idx].productCode) {
                  return {
                    productCode: product.productCode,
                    count: Number(product.count) + 1
                  };
                } else {
                  return {
                    productCode: product.productCode,
                    count: product.count
                  };
                }
              }));
            }
          }}>+</PlusButton>
        </Calculator>
        <Price>{accessToken ? products[idx].cnt * Math.floor(
            products[idx].price * (1 - products[idx].discountPercent
                / 100)) : count.map((product) => {
          if (product.productCode === products[idx].productCode) {
            return product.count * products[idx].price
          }
        })} 원</Price>
        <Delete onClick={() => {
          if (window.confirm("삭제하시겠습니까?")) {
            if (accessToken) {
              deleteCartProductAPi(accessToken, products[idx].productCode);
            } else {
              sessionStorage.removeItem(products[idx].productCode)
            }
            setProducts(products.filter(
                product => (products[idx].productCode !== product.productCode)))
          }
        }}>x</Delete>
      </StyledElement>
  ) : <NonProducts>장바구니에 담긴 상품이 없습니다</NonProducts>;

  return (
      <>
        <Header/>
        <Shoppinglistwrap>
          <Container>
            <Row>
              <Shoppinglisttop>
                <strong>장바구니</strong>
              </Shoppinglisttop>
              <Shoppinglistmain className='clearfix'>
                <Shoppinglistleft>
                  <SelectFunc
                      checkedProductCodes={checkedProductCodes}
                      setCheckedProductCodes={setCheckedProductCodes}
                      products={products}
                      setProducts={setProducts}
                      accessToken={accessToken}
                  />
                  {productsPresent}
                </Shoppinglistleft>
                <Shoppinglistright><p>right</p></Shoppinglistright>
              </Shoppinglistmain>
            </Row>
          </Container>
        </Shoppinglistwrap>
      </>
  )
}

export default Shoppinglist;