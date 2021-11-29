import React from 'react';
import styled from "styled-components";

const StyledInput = styled.input`
  margin-right: 10px;
  width:20px;
  height:20px;
`;

const StyledCheckbox = (props) => {
  return <StyledInput
      type="checkbox"
      onChange={props.onChange}
      checked={props.checked}
  />;
};

export default StyledCheckbox;