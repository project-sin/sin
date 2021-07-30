import React, {useState,useEffect} from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import axios from 'axios';
import qs from 'query-string';

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

const Pagenav = styled.div`text-align: center; margin-top: 50px;`
const Pagebtn = styled.button`padding: 8px 13px; margin-left: -1px; background: none; border: 1px solid gray;`
const Pagebtnl = styled.button`padding: 8px 8px; margin-left: -1px; background: none; border: 1px solid gray;`

const Mainrightfooter = styled.div`border-top : 1px solid black; padding: 20px 0; margin-top: 20px;`
const Mainrightfooterleft = styled.div`float: left;`
const Mainrightfooterleftspan = styled.span`font-size:12px; padding-right: 20px;`
const Mainrightfooterleftlabel = styled.label`font-size:12px; padding: 0 5px;`
const Mainrightfooterright = styled.div`float: right;`
const Mainrightfooterrightinput = styled.input`width:200px; height: 20px; padding: 6px 10px 3px 10px;`
const Mainrightfooterrightbtn = styled.button`width: 34px; height: 34px; color:#fff; background: #5f0080; border: none; cursor: pointer;`

const Noticesection = (props) => {
    const page = parseInt(qs.parse(props.location.search).page)
    
    const [pageinfo,setpageinfo] = useState({
        totalpages: 5,
        currentpage: page
    })
    const [list,setLists] = useState([])
    const [searchopt,setSearchopt] = useState([])
    const [searchword,setSearchword] = useState('')

    const geturl = () => {
        var url = ``
        searchopt.map(item=> url += `&search[${item}]=on`)
        return url
    }

    useEffect(()=>{
        if(!searchword) {
            axios.get(`http://localhost:8080/shop/board/list?id=notice&page=`+page).then((res)=>{
                setLists(list.filter(post=> post.id === -1))
                list.concat(res.data.content)
                setpageinfo({
                    ...pageinfo,
                    totalpages: res.data.totalpages
                })
            })
        } else {
            axios.get(`http://localhost:8080/shop/board/list?id=notice${geturl()}&word=${searchword}&page=`+page).then((res)=>{
                setLists(list.filter(post=> post.id === -1))
                list.concat(res.data.content)
                setpageinfo({
                    ...pageinfo,
                    totalpages: res.data.totalpages
                })
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
    

    return (
        <>
        <Mainrighttitle>
            <Mainrighttitlestrong>공지사항</Mainrighttitlestrong><Mainrighttitlespan>컬리의 새로운 소식들과 유용한 정보들을 한곳에서 확인하세요</Mainrighttitlespan>
        </Mainrighttitle>
        <Mainrightlisttable>
            <Thead>
                <Tr>
                    <Th>번호</Th>
                    <Th>제목</Th>
                    <Th>작성자</Th>
                    <Th>작성일</Th>
                    <Th>조회</Th>
                </Tr>
            </Thead>
            <tbody>
                <Tr>
                    <Tdorder>공지</Tdorder>
                    <Tdtitle><Link to=''>[마켓컬리] 유튜브 컬리's TMI : 뷰티에 대한 모든 것 편' 댓글 이벤트 당첨자 안내</Link></Tdtitle>
                    <Tdwriter>marketkurly</Tdwriter>
                    <Tddate>2021-07-08</Tddate>
                    <Tdview>55</Tdview>
                </Tr>
                {list.map(post =>
                    <Tr onClick={()=>props.history.push(`/shop/board/view?id=notice&no=${post.id}`)}>
                        <Tdorder>{post.id}</Tdorder>
                        <Tdtitle>{post.title}</Tdtitle>
                        <Tdwriter>{post.writer}</Tdwriter>
                        <Tddate>{post.createdDate}</Tddate>
                        <Tdview>{post.views}</Tdview>
                    </Tr>
                )}
            </tbody>
        </Mainrightlisttable>
        <Pagenav>
            <Pagebtnl onClick={()=>props.history.push(`/shop/board/list?id=notice&page=1`)}>&lt;&lt;</Pagebtnl>
            <Pagebtn onClick={()=>(page >1)? props.history.push(`/shop/board/list?id=notice&page=${page-1}`):null}>&lt;</Pagebtn>
            {[...Array(pageinfo.totalpages)].map((n,idx)=>{
                return (
                    <Pagebtn onClick={()=>props.history.push(`/shop/board/list?id=notice&page=${idx+1}`)}>{idx+1}</Pagebtn>
                )
            })}
            <Pagebtn onClick={()=>(page <pageinfo.totalpages)?props.history.push(`/shop/board/list?id=notice&page=${page+1}`):null}>&gt;</Pagebtn>
            <Pagebtnl onClick={()=>props.history.push(`/shop/board/list?id=notice&page=${pageinfo.totalpages}`)}>&gt;&gt;</Pagebtnl>
        </Pagenav>
        <Mainrightfooter className='clearfix'>
            <Mainrightfooterleft>
                <Mainrightfooterleftspan>검색어</Mainrightfooterleftspan>
                <input type='checkbox' name='search' id='name' value='name' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='name'>이름</Mainrightfooterleftlabel>
                <input type='checkbox' name='search' id='title' value='title' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='title'>제목</Mainrightfooterleftlabel>
                <input type='checkbox' name='search' id='contents' value='contents' onChange={checkboxhandler} />
                <Mainrightfooterleftlabel htmlFor='contents'>내용</Mainrightfooterleftlabel>
                </Mainrightfooterleft>
            <Mainrightfooterright>
                <Mainrightfooterrightinput type='text' name='word' value={searchword}  onChange={getword} />
                <Mainrightfooterrightbtn onClick={search}>검색</Mainrightfooterrightbtn>
            </Mainrightfooterright>
        </Mainrightfooter>
        </>
    )
}

export default Noticesection;