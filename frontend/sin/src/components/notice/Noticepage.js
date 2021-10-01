import React,{useState,useEffect} from 'react';
import styled from 'styled-components';
import axios from 'axios';
import qs from 'query-string';
import getNoticeDetailsApi from "../api/notice/GetNoticeDetailsApi";

const NoticeWrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Sectionone = styled.div`text-align: center; margin: 40px 0;`
const Sectiononetitle = styled.p`font-weight: bold; font-size: 22px;`
const Sectiononedesc = styled.p`font-size:12px; color: #777;`

const Sectiontwo =styled.div`border-top:2px solid black;`
const Sectiontwotable = styled.table`width: 100%; border-collapse: collapse; border-spacing: 0;`
const Sectiontwotabletr = styled.tr``
const Th = styled.th`width: 130px; text-align: left; padding: 10px 15px; background: #f7f5f8;border-bottom: 1px solid #dbdbdb;`
const Td = styled.td`padding: 10px 15px;border-bottom: 1px solid #dbdbdb;`
const Ul = styled.ul`display: flex;`
const Li = styled.li`display: flex;`
const Lipone = styled.p`width: 130px; padding: 10px 15px;background: #f7f5f8;`
const Liptwo = styled.p`padding: 10px 15px; margin-right: 50px;`
const Sectiontwocontent = styled.div`min-height: 500px; padding: 10px 15px; border-top: 1px solid #dbdbdb;`

const Divbtn = styled.div`text-align: right; margin: 20px 0px;`
const Listbtn = styled.button`color: #fff; border: none; padding: 10px 50px; background: #795b8f; cursor: pointer;`

const Prevnextul = styled.ul`border-top: 1px solid black; border-bottom : 1px solid black; margin-bottom: 50px;`
const Prevnextlipone = styled.p`padding: 10px 15px;`
const Prevnextliptwo = styled.p`padding: 10px 15px;`
const Prevli = styled.div`display: flex; border-bottom: 1px solid #dbdbdb; cursor: pointer;`
const Nextli = styled.div`display: flex; cursor: pointer;`

const Noticepage = (props) => {
    const no = parseInt(qs.parse(props.location.search).no)
    const [post,setPost] = useState({
        id: "id",
        title: "title",
        content: "content",
        writer : "writer",
        createDate: "createDate",
        views: "views"
    })

    const [prevOther,setPrevOther] = useState(
        {
            id:'testtitle',
            title:'testid'
        })
    const [nextOther,setNextOther] = useState(
        {
            id:'testtitle',
            title:'testid'
        })

    useEffect(()=>{
        getNoticeDetailsApi(no).then((res)=> {
            setPost({
                ...post,
                id: res.cur.id,
                title: res.cur.title,
                content: res.cur.content,
                writer : res.cur.writer,
                createDate: res.cur.createDate,
                views: res.views
            })
            setPrevOther({id: res.prev.id, title: res.prev.title})
            setNextOther({id: res.next.id, title: res.next.title})
        })
    },[no])

    return (
        <NoticeWrap>
            <Container>
                <Row>
                    <Sectionone>
                            <Sectiononetitle>공지사항</Sectiononetitle>
                            <Sectiononedesc>컬리의 새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.</Sectiononedesc>
                    </Sectionone>
                    <Sectiontwo>
                        <Sectiontwotable>
                            <Sectiontwotabletr>
                                <Th>제목</Th>
                                <Td>{post.title}</Td>
                            </Sectiontwotabletr>
                            <Sectiontwotabletr>
                                <Th>작성자</Th>
                                <Td>{post.writer}</Td>
                            </Sectiontwotabletr>
                            <Sectiontwotabletr>
                                <td colSpan='2'>
                                <Ul>
                                    <Li>
                                        <Lipone>작성일</Lipone>
                                        <Liptwo>{post.createDate}</Liptwo>
                                    </Li>
                                    <Li>
                                        <Lipone>조회수</Lipone>
                                        <Liptwo>{post.views}</Liptwo>
                                    </Li>
                                </Ul>
                                </td>
                            </Sectiontwotabletr>
                        </Sectiontwotable>
                        <Sectiontwocontent>
                            <h2>{post.content}</h2>
                        </Sectiontwocontent>
                    </Sectiontwo>
                    <Divbtn>
                        <Listbtn onClick={()=>props.history.push(`/shop/board/list?id=notice`)}>목록</Listbtn>
                    </Divbtn>
                    <Prevnextul>
                        {prevOther?<Prevli onClick={()=>props.history.push(`/shop/board/view?id=notice&no=${prevOther.id}`)}><Prevnextlipone>이전글</Prevnextlipone><Prevnextliptwo>{prevOther.title}</Prevnextliptwo></Prevli>:null}
                        {nextOther?<Nextli onClick={()=>props.history.push(`/shop/board/view?id=notice&no=${nextOther.id}`)}><Prevnextlipone>다음글</Prevnextlipone><Prevnextliptwo>{nextOther.title}</Prevnextliptwo></Nextli>:null}
                    </Prevnextul>
                </Row>
            </Container>
        </NoticeWrap>
    )
}

export default Noticepage;