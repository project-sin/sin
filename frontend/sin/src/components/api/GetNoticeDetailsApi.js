import React from 'react';
import axios from 'axios';
import { BACKEND_ADDRESS } from '../../constants/ADDRESS';

const getNoticeDetailsApi = (no) => {
  return (axios.get(BACKEND_ADDRESS+"/board/view?id=notice&no="+no)
  .then(response => response.data))
}

export default getNoticeDetailsApi;