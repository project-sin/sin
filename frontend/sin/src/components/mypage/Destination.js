import React from 'react';
import styled from 'styled-components'

const Destinationlist = styled.div``
const Destinationheader = styled.div`padding: 0 10px 20px 0;`
const Destinationmain = styled.div`padding: 20px 10px 20px 0; border-top: 2px solid #111; border-bottom: 1px solid #dbdbdb;`

const Mainul = styled.ul`padding-bottom: 20px; border-bottom: 1px solid black;`
const Mainlione = styled.li`float: left; padding-right: 180px;`
const Mainlitwo = styled.li`float: left; padding-right: 180px;`
const Mainlithr = styled.li`float: left; padding-right: 35px;`
const Mainlifou = styled.li`float: left; padding-right: 35px;`
const Mainlifiv = styled.li`float: left; padding-right: 35px;`
const Mainlisix = styled.li`float: left;`
const Destination = () => {
    return (
        <Destinationlist>
            <Destinationheader><strong>배송지 관리</strong> <span>배송지에 따라 상품 정보가 달라질 수 있습니다.</span></Destinationheader>
            <Destinationmain>
                <Mainul className='clearfix'>
                    <Mainlione>선택</Mainlione>
                    <Mainlitwo>주소</Mainlitwo>
                    <Mainlithr>받을실 분</Mainlithr>
                    <Mainlifou>연락처</Mainlifou>
                    <Mainlifiv>배송유형</Mainlifiv>
                    <Mainlisix>수정</Mainlisix>
                </Mainul>
                <p>상품 구매를 위해 배송지를 설정해주세요</p>
                <ul>
                    <li></li>
                </ul>
            </Destinationmain>
        </Destinationlist>
    )
}

export default Destination;