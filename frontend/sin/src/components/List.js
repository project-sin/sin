import React from 'react';
import styled from 'styled-components';

const Listwrap = styled.div``
const Container = styled.div`width:1050px; margin: 0 auto;`
const Row = styled.div``

const Item = styled.div`border: 1px solid #black; width: 32%; height: 450px; padding: 10px; margin: 10px; &:hover{cursor: pointer;}`
const Itemimg = styled.div`height: 75%; background: #999; transition: 0.3s all ease-in; &:hover {background: #fff;}`
const Itemname = styled.div`height: 7%; margin-top:10px; background: #888;`
const Itemprice = styled.div`height: 7%; background: #777;`
const Itemdesc = styled.div`height: 7%; background: #666;`
const List = ({match,history}) => {
    const link = (itemid) => {
        history.push(`/item/${itemid}`)
    }
    const type = match.params.type
    return (
        <Listwrap>
            <Container>
                <Row>
                    <h2>{type}</h2>
                    <ul>
                        <li onClick={()=>link('itemid')}>
                            <Item>
                                <Itemimg>img</Itemimg>
                                <Itemname>친환경대파</Itemname>
                                <Itemprice>1,999원</Itemprice>
                                <Itemdesc>맛있는 대파</Itemdesc>
                            </Item>
                        </li>
                    </ul>
                    {/* 데이터 보내주세요~ */}
                </Row>
            </Container>
        </Listwrap>
    )
}

export default List;