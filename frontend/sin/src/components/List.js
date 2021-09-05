import React,{useEffect, useState} from 'react';
import styled from 'styled-components';
import query from 'query-string';
import axios from 'axios';
import {BACKEND_ADDRESS} from "../constants/ADDRESS";

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
    const [pageinfo,setPageinfo] = useState({
        kor:'',
        orderby:''
    })
    const [items,setItems] = useState([])
    
    const category = query.parse(location.search).category

    useEffect(()=>{
        document.getElementById('nav').childNodes.forEach(e => {e.style.color = 'gray'})
        if(!category) {
            setPageinfo({
                ...pageinfo,
                kor: '알뜰쇼핑',
                orderby: '혜택순'
            })
            document.getElementById('lifou').style.color = '#5f0080'
            setSelectedli('lifou')
            axios.get(BACKEND_ADDRESS+'/shop/goods/goods_list?list=sale').then((res)=>{
                items.concat(res)
            })
        } else if(category==='038') {
            setPageinfo({
                ...pageinfo,
                kor: '신상품',
                orderby: '신상품순'
            })
            document.getElementById('litwo').style.color = '#5f0080'
            setSelectedli('litwo')
            axios.get(BACKEND_ADDRESS+'/shop/goods/goods_list?category='+category).then((res)=>{
                items.concat(res)
            })
        } else if(category==='029') {
            setPageinfo({
                ...pageinfo,
                kor: '베스트',
                orderby: '추천순'
            })
            document.getElementById('lione').style.color = '#5f0080'
            setSelectedli('lione')
            axios.get(BACKEND_ADDRESS+'/shop/goods/goods_list?category='+category).then((res)=>{
                items.concat(res)
            })
        } else {
            setPageinfo({
                ...pageinfo,
                kor: '카테고리',
                orderby: '추천순'
            })
            document.getElementById('lione').style.color = '#5f0080'
            setSelectedli('lione')
            axios.get(BACKEND_ADDRESS+'/shop/goods/goods_list?category='+category).then((res)=>{
                items.concat(res)
            })
        }
    },[category])

    const link = (itemid) => {
        history.push(`/shop/goods/goods_view?goodsno=${itemid}`)
    }

    const togglenav = () => {
        const nav = document.getElementById('nav')
        if(nav.style.display==='block') {nav.style.display='none'}
        else {nav.style.display='block'}
    }

    const selectorderby = e => {
        e.target.style.color = '#5f0080'
        if(e.target.id === selectedli) {
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
                        <Category>{pageinfo.kor}</Category>
                    </Title>
                    <Nav>
                        <Navtype>전체보기</Navtype>
                        <Orderby onClick={togglenav}>{pageinfo.orderby}</Orderby>
                        <Orderbylistul id='nav'>
                            <Orderbylistli id='lione' onClick={selectorderby}>추천순</Orderbylistli>
                            <Orderbylistli id='litwo' onClick={selectorderby}>신상품순</Orderbylistli>
                            <Orderbylistli id='lithr' onClick={selectorderby}>인기상품순</Orderbylistli>
                            <Orderbylistli id='lifou' onClick={selectorderby}>혜택순</Orderbylistli>
                            <Orderbylistli id='lifiv' onClick={selectorderby}>낮은 가격순</Orderbylistli>
                            <Orderbylistli id='lisix' onClick={selectorderby}>높은 가격순</Orderbylistli>
                        </Orderbylistul>
                    </Nav>
                    <ul>
                        {items.map(item=><li onClick={()=>link('itemid')}>
                            <Item>
                                <Itemimg>{item.img}}</Itemimg>
                                <Itemname>{item.title}</Itemname>
                                <Itemprice>{item.price}</Itemprice>
                                <Itemdesc>{item.desc}</Itemdesc>
                            </Item>
                        </li>
                        )}
                    </ul>
                </Row>
            </Container>
        </Listwrap>
    )
}

export default List;