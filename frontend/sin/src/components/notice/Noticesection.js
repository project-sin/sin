import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';


const Mainrighttitle = styled.div`padding: 20px 0;`
const Mainrighttitlestrong = styled.strong`display: inline-block; font-size: 24px; font-weight: bold; padding-right: 10px;`
const Mainrighttitlespan = styled.span`font-size:14px; font-weight:bold; color: #999;`

const Mainrightlisttable = styled.table`border-collapse: collapse; border-spacing: 0; border-top: 2px solid #522772;`
const Thead = styled.thead``
const Tr = styled.tr`font-size: 12px; text-align: center;`
const Th = styled.th`padding: 20px 0;`
const Tdorder = styled.td`width:50px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tdtitle = styled.td`text-align: left; padding: 20px 0px 20px 10px; border-bottom: 1px solid #f4f4f4;`
const Tdwriter = styled.td`width:100px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tddate = styled.td`width:100px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Tdview = styled.td`width:30px; padding: 20px 0; border-bottom: 1px solid #f4f4f4;`
const Mainrightfooter = styled.div`border-top : 1px solid black; padding: 20px 0; margin-top: 20px;`
const Mainrightfooterleft = styled.div`float: left;`
const Mainrightfooterleftspan = styled.span`font-size:12px; padding-right: 20px;`
const Mainrightfooterleftlabel = styled.label`font-size:12px; padding: 0 5px;`
const Mainrightfooterright = styled.div`float: right`
const Mainrightfooterrightinput = styled.input`width:200px; height: 20px; padding: 5px 10px;`
const Mainrightfooterrightbtn = styled.button`width: 34px; height: 34px; color:#fff; background: #5f0080;`

const Noticesection = () => {
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
                <Tr>
                    <Tdorder>999</Tdorder>
                    <Tdtitle><Link to=''>[가격인상공지][훕훕베이글]시그니처 베이글 6종</Link></Tdtitle>
                    <Tdwriter>marketkurly</Tdwriter>
                    <Tddate>2021-07-06</Tddate>
                    <Tdview>555</Tdview>
                </Tr>
            </tbody>
        </Mainrightlisttable>
        <Mainrightfooter className='clearfix'>
            <Mainrightfooterleft>
                <Mainrightfooterleftspan>검색어</Mainrightfooterleftspan>
                <input type='radio' name='search' id='name' />
                <Mainrightfooterleftlabel htmlFor='name'>이름</Mainrightfooterleftlabel>
                <input type='radio' name='search' id='title' />
                <Mainrightfooterleftlabel htmlFor='title'>제목</Mainrightfooterleftlabel>
                <input type='radio' name='search' id='content' />
                <Mainrightfooterleftlabel htmlFor='content'>내용</Mainrightfooterleftlabel>
                </Mainrightfooterleft>
            <Mainrightfooterright>
                <Mainrightfooterrightinput type='text' />
                <Mainrightfooterrightbtn>btn</Mainrightfooterrightbtn>
            </Mainrightfooterright>
        </Mainrightfooter>
        </>
    )
}

export default Noticesection;