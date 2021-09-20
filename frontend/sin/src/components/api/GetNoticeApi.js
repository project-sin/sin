import React from 'react';
import axios from 'axios';
import { BACKEND_ADDRESS } from '../../constants/ADDRESS';

const GetNoticeApi = (page) => {
    return (
        axios.get(BACKEND_ADDRESS+`/board/list?id=notice&page=`+page)
    )
}

export default GetNoticeApi;