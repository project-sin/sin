import React from 'react';
import styled from 'styled-components';

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
const Listbtn = styled.button`color: #fff; border: none; padding: 10px 50px; background: #795b8f;`

const Prevnextul = styled.ul`border-top: 1px solid black; border-bottom : 1px solid black; margin-bottom: 50px;`
const Prevnextlipone = styled.p`padding: 10px 15px;`
const Prevnextliptwo = styled.p`padding: 10px 15px;`
const Prevli = styled.div`display: flex; border-bottom: 1px solid #dbdbdb;`
const Nextli = styled.div`display: flex;`

const Noticepage = () => {
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
                                <Td>[마켓컬리켓]유튜브 '마켓컬리 신선 MD 고창 수박 브이로그' 댓글 이벤트 당첨자 안내</Td>
                            </Sectiontwotabletr>
                            <Sectiontwotabletr>
                                <Th>작성자</Th>
                                <Td>MarketKurly</Td>
                            </Sectiontwotabletr>
                            <Sectiontwotabletr>
                                <td colSpan='2'>
                                <Ul>
                                    <Li>
                                        <Lipone>작성일</Lipone>
                                        <Liptwo>2021-07-20</Liptwo>
                                    </Li>
                                    <Li>
                                        <Lipone>조회수</Lipone>
                                        <Liptwo>603</Liptwo>
                                    </Li>
                                </Ul>
                                </td>
                            </Sectiontwotabletr>
                        </Sectiontwotable>
                        <Sectiontwocontent>
                            <h2>본문</h2>
                        </Sectiontwocontent>
                    </Sectiontwo>
                    <Divbtn>
                        <Listbtn>목록</Listbtn>
                    </Divbtn>
                    <Prevnextul>
                        <Prevli><Prevnextlipone>이전글</Prevnextlipone><Prevnextliptwo>[가격인하공지]핫 트러플 제스트 외 2건 (2021.7.23~)</Prevnextliptwo></Prevli>
                        <Nextli><Prevnextlipone>다음글</Prevnextlipone><Prevnextliptwo>[가격인상공지]산마르코 핸들형 트레이 골드 (2021.7.26~)</Prevnextliptwo></Nextli>
                    </Prevnextul>
                </Row>
            </Container>
        </NoticeWrap>
    )
}

export default Noticepage;