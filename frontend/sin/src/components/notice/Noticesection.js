import React, {useState,useEffect} from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import qs from 'query-string';
import getNoticeApi from '../api/notice/GetNoticeApi';
import GetSearchedNoticeApi from '../api/notice/GetSearchedNoticeApi';
import queryString from "query-string";

const Mainrighttitle = styled.div`padding: 20px 0;`
const Mainrighttitlestrong = styled.strong`display: inline-block; font-size: 24px; font-weight: bold; padding-right: 10px;`
const Mainrighttitlespan = styled.span`font-size:14px; font-weight:bold; color: #999;`

const Mainrightlisttable = styled.table`border-collapse: collapse; border-spacing: 0; border-top: 2px solid #522772;`
const Thead = styled.thead``
const Tr = styled.tr`font-size: 12px; text-align: center; cursor: pointer;`
const Th = styled.th`padding: 20px 0;`
const Tdorder = styled.td`width:50px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tdtitle = styled.td`text-align: left; padding: 20px 0px 20px 10px; border-bottom: 1px solid #f4f4f4;`
const Tdwriter = styled.td`width:100px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tddate = styled.td`width:100px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tdview = styled.td`width:30px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`

const NumberButton = styled.div`border: 2px solid black; width:20px; margin:5px; cursor:pointer`
const PageChangeButtons = styled.div`padding-top:30px; display: flex; justify-content: center; text-align: center; `;
const NextButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const PrevButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const FirstPageButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const LastPageButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`

const Mainrightfooter = styled.div`border-top : 1px solid black; padding: 20px 0; margin-top: 20px;`
const Mainrightfooterleft = styled.div`float: left;`
const Mainrightfooterleftspan = styled.span`font-size:12px; padding-right: 20px;`
const Mainrightfooterleftlabel = styled.label`font-size:12px; padding: 0 5px;`
const Mainrightfooterright = styled.div`float: right;`
const Mainrightfooterrightinput = styled.input`width:200px; height: 20px; padding: 6px 10px 3px 10px;`
const Mainrightfooterrightbtn = styled.button`width: 34px; height: 34px; color:#fff; background: #5f0080; border: none; cursor: pointer;`

const Noticesection = (props) => {
    const page = queryString.parse(props.location.search).page;
    const word = qs.parse(props.location.search).word
    const [totalNotices, settotalNotices] = useState(null)
    const [list,setLists] = useState([])
    const [searchopt,setSearchopt] = useState([])
    const [searchword,setSearchword] = useState('')

    const geturl = () => {
        var url = ``
        searchopt.map(item=> url += `&search[${item}]=on`)
        return url
    }

    useEffect(()=>{
        if(word===undefined) {
            getNoticeApi(page ? page : 1).then((res)=>{
                setLists(res)
                settotalNotices(res.totalElements)
            })
        } else {
            GetSearchedNoticeApi(geturl(),searchword,page).then((res)=>{
                setLists(res)
                settotalNotices(res.totalElements)
            })
        }
    },[page])

    const checkboxhandler = e => {
        const {value,checked} = e.target
        if(checked===true) {
            setSearchopt([...searchopt, value])
        } else {
            setSearchopt(searchopt.filter(opt=>opt!==value))
        }
    }

    const getword = e => {
        setSearchword(e.target.value)
    }

    const search = () => {
        props.history.push(`/shop/board/list?id=notice${geturl()}&word=${searchword}&page=0`)
    }

    const numbers = [...Array((parseInt(totalNotices/10)+1)>=5 ? 5 : parseInt(totalNotices/10)+1)].map((value, index)=>
        <NumberButton onClick={()=>
            page?props.history.push("/shop/board/list?id=notice&page="+(parseInt((page-1)/5)*5+index+1)) :props.history.push("/shop/board/list?id=notice&page="+(index+1)) }
        >{page?parseInt((page-1)/5)*5+index+1 : index+1} </NumberButton>
    );

    return (
        <>
        <Mainrighttitle>
            <Mainrighttitlestrong>????????????</Mainrighttitlestrong><Mainrighttitlespan>????????? ????????? ???????????? ????????? ???????????? ???????????? ???????????????</Mainrighttitlespan>
        </Mainrighttitle>
        <Mainrightlisttable>
            <Thead>
                <Tr>
                    <Th>??????</Th>
                    <Th>??????</Th>
                    <Th>?????????</Th>
                    <Th>?????????</Th>
                    <Th>??????</Th>
                </Tr>
            </Thead>
            <tbody>
                <Tr>
                    <Tdorder>??????</Tdorder>
                    <Tdtitle><Link to=''>[????????????] ????????? ??????'s TMI : ????????? ?????? ?????? xcxccxxc??? ???' ?????? ????????? ????????? ??????</Link></Tdtitle>
                    <Tdwriter>marketkurly</Tdwriter>
                    <Tddate>2021-07-08</Tddate>
                    <Tdview>55</Tdview>
                </Tr>
                {list.content ? list.content.map(post =>
                    <Tr onClick={()=>
                        props.history.push("/shop/board/view?id=notice&no=" + post.id)}>
                        <Tdorder>{post.id}</Tdorder>
                        <Tdtitle>{post.title}</Tdtitle>
                        <Tdwriter>{post.writer}</Tdwriter>
                        <Tddate>{post.createdDate}</Tddate>
                        <Tdview>{post.views}</Tdview>
                    </Tr>
                ) : ""}
            </tbody>
        </Mainrightlisttable>
            <PageChangeButtons>
                <FirstPageButton onClick={()=>props.history.push("/shop/board/list?id=notice&page=1")}>
                    ??????
                </FirstPageButton>
                <PrevButton onClick={()=>
                    (page == null || page <= 1)?props.history.push("/shop/board/list?id=notice&page=1"):props.history.push("/shop/board/list?id=notice&page="+ (page-1))}
                >
                    ???
                </PrevButton>
                {numbers}
                <NextButton onClick={()=>
                    (page > parseInt(totalNotices/10))?props.history.push("/shop/board/list?id=notice&page="+parseInt(totalNotices/10+1)):props.history.push("/shop/board/list?id=notice&page="+ (Number(page)+1))}
                >
                    ???
                </NextButton>
                <LastPageButton onClick={()=>props.history.push("/shop/board/list?id=notice&page="+ parseInt(totalNotices/10+1))}>
                    ??????
                </LastPageButton>
            </PageChangeButtons>
        <Mainrightfooter className='clearfix'>
            <Mainrightfooterleft>
                <Mainrightfooterleftspan>?????????</Mainrightfooterleftspan>
                <input type='checkbox' name='search' id='name' value='name' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='name'>??????</Mainrightfooterleftlabel>
                <input type='checkbox' name='search' id='title' value='title' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='title'>??????</Mainrightfooterleftlabel>
                <input type='checkbox' name='search' id='contents' value='contents' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='contents'>??????</Mainrightfooterleftlabel>
                </Mainrightfooterleft>
            <Mainrightfooterright>
                <Mainrightfooterrightinput type='text' name='word' value={searchword}  onChange={getword} />
                <Mainrightfooterrightbtn onClick={search}>??????</Mainrightfooterrightbtn>
            </Mainrightfooterright>
        </Mainrightfooter>
        </>
    )
}

export default Noticesection;