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

  const [products, setProducts] = useState(null);


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