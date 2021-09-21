import React,{useEffect, useState} from 'react';
import styled from 'styled-components';

const Listwrap = styled.div`margin-top: 50px;`
const Container = styled.div`width:1050px; margin: 0 auto;`
const Row = styled.div``
const Title = styled.div`margin-bottom: 20px;`
const Nav = styled.div`position: relative;`
const Navtype = styled.span`font-size:14px; font-weight: bold; padding-left: 10px; color: gray;`
const Category = styled.p`font-weight: bold;`
const Orderby = styled.button` float: right; border: none; background: none; cursor: pointer; color: gray`
const Orderbylistul = styled.ul`position: absolute; display: none; width: 100%; top: 30px; right: -10px; width: 90px; text-align: right; box-shadow: 0 2px 4px rgb(0 0 0 / 30%); background-color: white; z-index: 1`
const Orderbylistli = styled.li`font-size: 12px; font-weight: bold; color: gray; padding: 10px 5px 10px 0; cursor: pointer; &:hover {color: #5f0080;}`

const Sort = (props) => {
    const [selectedli,setSelectedli] = useState('')
    const [pageinfo,setPageinfo] = useState('')

    useEffect(()=>{
        document.getElementById('nav').childNodes.forEach(e => {e.style.color = 'gray'})
        if(!props.category) {
            setPageinfo('혜택순')
            document.getElementById('lifou').style.color = '#5f0080'
            setSelectedli('lifou')
        } else if(props.category==='038') {
            setPageinfo('신상품순')
            document.getElementById('litwo').style.color = '#5f0080'
            setSelectedli('litwo')
        } else if(props.category==='029') {
            setPageinfo('추천순')
            document.getElementById('lione').style.color = '#5f0080'
            setSelectedli('lione')
        } else {
            setPageinfo( '추천순')
            document.getElementById('lione').style.color = '#5f0080'
            setSelectedli('lione')
        }
    },[props.category])

    const togglenav = () => {
        const nav = document.getElementById('nav')
        if(nav.style.display==='block') {nav.style.display='none'}
        else {nav.style.display='block'}
    }

    const selectorderby = e => {
        const nav = document.getElementById('nav');
        nav.style.display='none';
        e.target.style.color = '#5f0080'
        if(e.target.id != selectedli) {
            if(e.target.id === 'lione')
                setPageinfo('추천순')
            if(e.target.id === 'litwo')
                setPageinfo('신상품순')
            if(e.target.id === 'lithr')
                setPageinfo('인기상품순')
            if(e.target.id === 'lifou')
                setPageinfo('혜택순')
            if(e.target.id === 'lifiv')
                setPageinfo('낮은 가격순')
            if(e.target.id === 'lisix')
                setPageinfo('높은 가격순')
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
                        <Category>test</Category>
                    </Title>
                    <Nav>
                        <Navtype>총 {props.products ? Object.keys(props.products).length : ""}건</Navtype>
                        <Orderby onClick={togglenav}>{pageinfo}</Orderby>
                        <Orderbylistul id='nav'>
                            <Orderbylistli id='lione' onClick={selectorderby}>추천순</Orderbylistli>
                            <Orderbylistli id='litwo' onClick={selectorderby}>신상품순</Orderbylistli>
                            <Orderbylistli id='lithr' onClick={selectorderby}>인기상품순</Orderbylistli>
                            <Orderbylistli id='lifou' onClick={selectorderby}>혜택순</Orderbylistli>
                            <Orderbylistli id='lifiv' onClick={selectorderby}>낮은 가격순</Orderbylistli>
                            <Orderbylistli id='lisix' onClick={selectorderby}>높은 가격순</Orderbylistli>
                        </Orderbylistul>
                    </Nav>
                </Row>
            </Container>
        </Listwrap>
    )
}

export default Sort;