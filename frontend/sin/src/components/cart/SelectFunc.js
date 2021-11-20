import React from 'react';
import styled from "styled-components";
import StyledCheckbox from "./StyledCheckbox";

const StyledDiv = styled.div`
  border-bottom: 1px solid black;
  height: 35px;
  width: 95%;
`

const SelectAllButton = styled.div`
  display: inline-block;
  width:20%;
  font-size:15px;
  cursor:pointer;
  padding-left:10px;
  margin-right: -30px;
`;

const DeleteSelectedProducts = styled.div`
  display: inline-block;
  width:20%;
  font-size:15px;
  cursor:pointer;
  padding-left:10px;
  margin-left: 15px;
`;

const SelectFunc = (props) => {
  const onClick = () => {
    if (props.checkedProductCodes.length > 0) {
      props.setCheckedProductCodes([]);
    } else {
      props.setCheckedProductCodes(
          props.products.map(product => product.productCode));
    }
  };

  const deleteSelectedProducts = () => {
    if (props.checkedProductCodes.length === 0) {
      alert("삭제할 상품을 선택해주세요")
    } else if (window.confirm("선택한 상품을 삭제하시겠습니까?")) {
      for (let i = 0; i < props.checkedProductCodes.length; i++) {
        sessionStorage.removeItem(props.checkedProductCodes[i])
      }
      props.setProducts(props.products.filter(
          product => !props.checkedProductCodes.includes(product.productCode)))
    }
  }

  return (
      <StyledDiv>
        <SelectAllButton onClick={onClick}>
          <StyledCheckbox
              onChange={() => onClick}
              checked={(props.checkedProductCodes.length
                  === props.products.length)}/>
          전체선택</SelectAllButton>
        |
        <DeleteSelectedProducts
            onClick={deleteSelectedProducts}>선택삭제</DeleteSelectedProducts>
      </StyledDiv>
  );
};

export default SelectFunc;