import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const findProductListApi = (category, list) => {
  return category ? axios.get(BACKEND_ADDRESS + "/goods/goods-list?category=" + category).then(response => response.data)
      : axios.get(BACKEND_ADDRESS + "/goods/goods-list?list=" + list).then(response => response.data);
};

export default findProductListApi;