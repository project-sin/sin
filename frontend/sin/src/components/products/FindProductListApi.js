import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../constants/ADDRESS";

const FindProductListApi = (category) => {
  return (axios.get(BACKEND_ADDRESS + "/goods/goods_list?category=" + category)
  .then(response => response.data))
};

export default FindProductListApi;