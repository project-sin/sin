import React from 'react';
import axios from "axios";
import {BACKEND_ADDRESS} from "../../../constants/ADDRESS";

const checkIsDuplicateId = (id, setCheckDuplicatedId) => {
  return axios.get(BACKEND_ADDRESS + "/member/check/id?id="+id)
  .then(response => response.data)
  .catch(error => {
    if (error.response.status === 400 || error.response.status === 404) {
      alert(error.response.data.message);
      setCheckDuplicatedId(false)
      return Promise.reject();
    } else {
      alert("이유가 뭔지 모르겠지만 실패...");
      setCheckDuplicatedId(false)
    }
  });
};

export default checkIsDuplicateId;