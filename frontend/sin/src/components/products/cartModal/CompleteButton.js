import React from 'react';
import styled from "styled-components";

const StyledCompleteButton = styled.div`
  position: absolute;
  bottom: -5px;
  right: 0;
  width: 180px;
  height: 36px;
  padding-top:15px;
  font-size: 15px;
  border: 1px solid rgb(200,200,200);
  cursor: pointer;
  background: rgb(112,48,160);
  color: white;
`;

const CompleteButton = ({setOpenAddCartListModal, setCount, setTotalPrice}) => {
  return (
      <StyledCompleteButton onClick={()=>{
        setOpenAddCartListModal(false)
        setCount(0);
        setTotalPrice(0);
      }}>
        장바구니 담기
      </StyledCompleteButton>
  );
};

export default CompleteButton;