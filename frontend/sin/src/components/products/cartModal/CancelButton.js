import React from 'react';
import styled from "styled-components";

const StyledCancelButton = styled.div`
  position: absolute;
  bottom: -5px;
  left: 0;
  width: 180px;
  height: 36px;
  padding-top:15px;
  font-size: 15px;
  border: 1px solid rgb(200,200,200);
  cursor: pointer;
`;

const CancelButton = ({setOpenAddCartListModal, setCount, setTotalPrice}) => {
  return (
      <StyledCancelButton onClick={() => {
        setOpenAddCartListModal(false);
        setCount(0);
        setTotalPrice(0);
      }}>취소</StyledCancelButton>
  );
};

export default CancelButton;