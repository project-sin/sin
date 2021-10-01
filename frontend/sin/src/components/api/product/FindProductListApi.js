import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const findProductListApi = (category, list) => {
  return category ? axios.get(BACKEND_ADDRESS + "/goods/goods_list?category=" + category).then(response => response.data)
      : axios.get(BACKEND_ADDRESS + "/goods/goods_list?list=" + list).then(response => response.data);
};

export default findProductListApi;