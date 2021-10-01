import React from 'react';
import axios from 'axios';
import { BACKEND_ADDRESS } from '../../../constants/ADDRESS';

const GetSearchedNoticeApi = (url,word,page) => {
    return (
        axios.get(BACKEND_ADDRESS+`/board/list?id=notice`+url+`&word=`+word+`&page=`+page)
    )
}

export default GetSearchedNoticeApi;