import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const findNonMemberCartApi = (query) => {
  return (axios.get(BACKEND_ADDRESS + "/goods/cart?productCode=" + query)
  .then(response => response.data))
};

export default findNonMemberCartApi;