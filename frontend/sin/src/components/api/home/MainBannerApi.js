import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const mainBannerApi = () => {
  return (axios.get(BACKEND_ADDRESS + '/main/index/main-banner')
  .then(response => response.data))
};

export default mainBannerApi;