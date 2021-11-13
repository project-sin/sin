import React from 'react';
import styled from 'styled-components';
import Header from "./Header";

const Shoppinglistwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``
const Shoppinglisttop = styled.div`font-size: 30px; text-align: center; padding: 50px 0px;`
const Shoppinglistmain = styled.div`min-height: 550px;`
const Shoppinglistleft = styled.div`float: left; width: 70%; height: 300px;`
const Shoppinglistright = styled.div`float: left; width: 28%; height: 300px; margin-top: 20px; border: 1px solid #dbdbdb;`

const Shoppinglist = () => {
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
                  <p>left</p>
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