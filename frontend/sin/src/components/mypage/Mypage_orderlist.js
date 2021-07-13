import React from 'react';
import styled from 'styled-components'

const Orderlist = styled.div``
const Orderlistheader = styled.div`padding: 0 10px 20px 0;`
const Orderlistmain = styled.div`padding: 20px 10px 20px 0; border-top: 2px solid #111; border-bottom: 1px solid #dbdbdb;`
const Mypage_orderlist = () => {
    return (
        <Orderlist>
            <Orderlistheader><strong>주문 내역</strong> <span>지난 3년간의 주문 내역 조회가 가능합니다</span></Orderlistheader>
            <Orderlistmain>
                <p>주문 내역이 없습니다</p>
                <ul>
                    <li></li>
                </ul>
            </Orderlistmain>
        </Orderlist>
    )
}

export default Mypage_orderlist;