import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const todayRecommendationAPi = () => {
  return (axios.get(BACKEND_ADDRESS + '/main/index/today-recommendation')
  .then(response => response.data))
};

export default todayRecommendationAPi;