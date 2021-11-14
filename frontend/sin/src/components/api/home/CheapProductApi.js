import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const cheapProductApi = () => {
  return (axios.get(BACKEND_ADDRESS + '/main/index/cheap-product')
  .then(response => response.data))
};


export default cheapProductApi;