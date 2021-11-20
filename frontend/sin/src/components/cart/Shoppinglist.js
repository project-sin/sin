import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import Header from "../Header";
import SelectAllButton from "./SelectAllButton";
import StyledCheckbox from "./StyledCheckbox";
import StyledElement from "./StyledElement";
import findNonMemberCartApi from "../api/cart/FindNonMemberCartApi";

const Shoppinglistwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``
const Shoppinglisttop = styled.div`font-size: 30px; text-align: center; padding: 50px 0px;`
const Shoppinglistmain = styled.div`min-height: 550px;`
const Shoppinglistleft = styled.div`
  display: relative;
  float: left; 
  width: 70%; 
  height: 300px;
  border: 1px solid red;
`
const Shoppinglistright = styled.div`float: left; width: 28%; height: 300px; margin-top: 20px; border: 1px solid #dbdbdb;`

const Shoppinglist = () => {
  const [checkedProductCodes, setCheckedProductCodes] = useState([]);
  const [products, setProducts] = useState([]);

  useEffect(() => {
    let query = ""
    for (let productCode in sessionStorage) {
      if (isNaN(productCode)) {
        break;
      } else {
        query+= productCode + ",";
      }
    }
    findNonMemberCartApi(query).then((productPromises)=>{
      setProducts(productPromises)
    })
  }, []);

  const onProductCheckboxClicked = (productCode) => {
    if (checkedProductCodes.includes(productCode)) {
      setCheckedProductCodes(checkedProductCodes.filter(e => e !== productCode))
    } else {
      setCheckedProductCodes([...checkedProductCodes, productCode]);
    }
  };

  const productsPresent = products.map((e, idx) =>
      <StyledElement onClick={() => onProductCheckboxClicked(products[idx].productCode)}>
        <StyledCheckbox
            onChange={() => onProductCheckboxClicked(products[idx].productCode)}
            checked={checkedProductCodes.includes(products[idx].productCode)}/>
        {products[idx].name}
      </StyledElement>
  );

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