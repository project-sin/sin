import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../constants/ADDRESS";
const findFaqApi = (page) => {
  return axios.get(BACKEND_ADDRESS + "/shop/service/faq?page="+page)
  .then(response => response.data);
};

export default findFaqApi;