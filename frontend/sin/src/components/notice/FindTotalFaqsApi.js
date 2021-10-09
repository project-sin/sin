import React from 'react';
import {BACKEND_ADDRESS} from "../../constants/ADDRESS";
import axios from "axios";

const findTotalFaqsApi = () => {

  return (axios.get(BACKEND_ADDRESS + "/service/faq/total")
  .then(response => response.data))
};

export default findTotalFaqsApi;