import React,{useEffect, useState} from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import query from 'query-string';

const Listwrap = styled.div`margin-top: 50px;`
const Container = styled.div`width:1050px; margin: 0 auto;`
const Row = styled.div``

const Item = styled.div`border: 1px solid #black; width: 32%; height: 450px; padding: 10px; margin: 10px; &:hover{cursor: pointer;}`
const Itemimg = styled.div`height: 75%; background: #999; transition: 0.3s all ease-in; &:hover {background: #fff;}`
const Itemname = styled.div`height: 7%; margin-top:10px; background: #888;`
const Itemprice = styled.div`height: 7%; background: #777;`
const Itemdesc = styled.div`height: 7%; background: #666;`

const Title = styled.div`margin-bottom: 20px;`
const Nav = styled.div`position: relative;`
const Navtype = styled.span`font-size:14px; font-weight: bold; padding-left: 10px; color: #5f0080;`
const Category = styled.p`font-weight: bold;`
const Orderby = styled.button` float: right; border: none; background: none; cursor: pointer;`
const Orderbylistul = styled.ul`position: absolute; display: none; width: 100%; top: 30px; right: -10px; width: 90px; text-align: right; box-shadow: 0 2px 4px rgb(0 0 0 / 30%)`
const Orderbylistli = styled.li`font-size: 12px; font-weight: bold; color: gray; padding: 10px 5px 10px 0; cursor: pointer; &:hover {color: #5f0080;}`

const List = ({history,location}) => {
    const [selectedli,setSelectedli] = useState('')
    const category = query.parse(location.search).category

    if(!category) {
        var korcategory = '알뜰쇼핑'
        // var init = 'lifou'
        var orderby = '혜택순'
    } else if(category==='new') {
        var korcategory = '신상품'
        // var init = 'litwo'
        var orderby = '신상품순'
    } else {
        var korcategory = '베스트'
        // var init = 'lione'
        var orderby = '추천순'
    }

    const link = (itemid) => {
        history.push(`/shop/goods/goods_view?goodsno=${itemid}`)
    }

    const togglenav = () => {
        const nav = document.getElementById('nav')
        if(nav.style.display==='block') {nav.style.display='none'}
        else {nav.style.display='block'}
    }

    const test = e => {
        e.target.style.color = '#5f0080'
        if(!selectedli) {
            setSelectedli(e.target.id)
        } else if(e.target.id === selectedli) {
            console.log('same')
        } else {
            document.getElementById(selectedli).style.color = 'gray'
            //이 식으로 색이 변화되면 기존 hover 시 색변화 css 미적용됨
            setSelectedli(e.target.id)
        }
    }


    return (
        <Listwrap>
            <Container>
                <Row>
                    <Title>
                        <Category>{korcategory}</Category>
                    </Title>
                    <Nav>
                        <Navtype>전체보기</Navtype>
                        <Orderby onClick={togglenav}>{orderby}</Orderby>
                        <Orderbylistul id='nav'>
                            <Orderbylistli id='lione' onClick={test}>추천순</Orderbylistli>
                            <Orderbylistli id='litwo' onClick={test}>신상품순</Orderbylistli>
                            <Orderbylistli id='lithr' onClick={test}>인기상품순</Orderbylistli>
                            <Orderbylistli id='lifou' onClick={test}>혜택순</Orderbylistli>
                            <Orderbylistli id='lifiv' onClick={test}>낮은 가격순</Orderbylistli>
                            <Orderbylistli id='lisix' onClick={test}>높은 가격순</Orderbylistli>
                        </Orderbylistul>
                    </Nav>
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
                </Row>
            </Container>
        </Listwrap>
    )
}

export default List;