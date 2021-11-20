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
`;

const SelectFunc = (props) => {
  const onClick = () => {
    if (props.checkedProductCodes.length > 0) {
      props.setCheckedProductCodes([]);
    } else {
      props.setCheckedProductCodes(props.products.map(product => product.productCode));
    }
  };

  return (
      <StyledDiv>
        <SelectAllButton onClick={onClick}>
          <StyledCheckbox
              onChange={() => onClick}
              checked={(props.checkedProductCodes.length === props.products.length)}/>
          전체선택</SelectAllButton>
      </StyledDiv>
  );
};

export default SelectFunc;