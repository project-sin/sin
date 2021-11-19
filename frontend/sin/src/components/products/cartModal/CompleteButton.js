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

const CompleteButton = ({
  productCode,
  count,
  setOpenAddCartListModal,
  setCount,
  setTotalPrice
}) => {
  return (
      <StyledCompleteButton onClick={() => {
        if (count <= 0) {
          alert("수량은 반드시 1 이상이어야 합니다.")
        } else {
          if (sessionStorage.getItem(productCode)) {
            sessionStorage.setItem(productCode,
                Number(sessionStorage.getItem(productCode)) + count);
            alert("장바구니에 이미 있는 상품이어서 상품이 추가되었습니다.")
          } else {
            sessionStorage.setItem(productCode, count);
            alert("장바구니에 상품을 담았습니다.")
          }
          setOpenAddCartListModal(false)
          setCount(0);
          setTotalPrice(0);
        }
      }}>
        장바구니 담기
      </StyledCompleteButton>
  );
};

export default CompleteButton;