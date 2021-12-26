import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const searchProductApi = (sword) => {
  return axios.get(
      BACKEND_ADDRESS + "/goods/goods-list/search?sword=" + sword).then(
      response => response.data)

};

export default searchProductApi;