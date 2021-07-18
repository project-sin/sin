import React, {useEffect, useState} from 'react'
import styled from 'styled-components';
import findFaqApi from "./FindFaqApi";
import queryString from "query-string";

const Faqtitlewrap = styled.div``
const Faqtitle = styled.span`font-size: 22px; font-weight: bold; padding-right: 20px;`
const Faqdesc = styled.span`font-size: 14px; color: #999;`

const Main = styled.div`margin-top: 30px; border-top: 2px solid #522772; border-bottom : 1px solid #333;padding-bottom: 50px;`

const Mainheader = styled.div``
const Mainheadertable = styled.table`border-collapse: collapse; border-spacing: 0; `
const Mainheadertabletbody = styled.tbody``
const Mainheadertabletbodytr = styled.tr`font-size: 12px; text-align: center;`
const Mainheadertabletbodythnum = styled.th`padding: 20px 0; width: 70px; border-bottom: 1px solid #dbdbdb;`
const Mainheadertabletbodythcate = styled.th`padding: 20px 0; width: 135px; border-bottom: 1px solid #dbdbdb;`
const Mainheadertabletbodythtitle = styled.th`padding: 20px 0; width: 600px; border-bottom: 1px solid #dbdbdb;`

const Question = styled.div`border-bottom: 1px solid #dbdbdb; &:hover {cursor: pointer;} &:on`
const Mainsection = styled.div``
const Mainsectiontable = styled.table``
const Mainsectiontabletbody = styled.tbody``
const Mainsectiontabletbodytr = styled.tr`font-size: 12px; text-align: center;`
const Mainsectiontabletbodytdnum = styled.td`padding: 20px 0; width: 70px;`
const Mainsectiontabletbodytdcate = styled.td`padding: 20px 0; width: 135px;`
const Mainsectiontabletbodytdtitle = styled.td`text-align: left; padding: 20px 0; width: 600px;`
const Answerwrap = styled.div`display: none; border-top: 1px solid #dbdbdb;`
const Answer = styled.div`min-height: 30px; font-size: 12px; padding: 20px 10px 20px 40px;`

const Faq = (props) => {

    const page = queryString.parse(props.location.search).page;
    const [openArticleNumber, setOpenArticleNumber] = useState(-1);
    const [faqs,setFaqs] = useState(null);

    useEffect(() => {
        findFaqApi(page).then(faqPromise => {
            setFaqs(faqPromise)
        });
    }, [page]);

    console.log(faqs)

    const unfold = (id) => {
        const test = document.getElementById(id)
        if(test.style.display == 'block'){
            test.style.display = 'none'
        } else {
            test.style.display = 'block'
        }
    }
    return (
        <>
        <Faqtitlewrap><Faqtitle>자주하는 질문</Faqtitle><Faqdesc>고객님들께서 가장 자주하시는 질문을 모두 모았습니다.</Faqdesc></Faqtitlewrap>
        <Main>
            <Mainheader>
                <Mainheadertable>
                    <Mainheadertabletbody>
                        <Mainheadertabletbodytr>
                            <Mainheadertabletbodythnum>번호</Mainheadertabletbodythnum>
                            <Mainheadertabletbodythcate>카테고리</Mainheadertabletbodythcate>
                            <Mainheadertabletbodythtitle>제목</Mainheadertabletbodythtitle>
                        </Mainheadertabletbodytr>
                    </Mainheadertabletbody>
                </Mainheadertable>
            </Mainheader>
            <Question onClick={()=>unfold('getidone')}>
                <Mainsection>
                    <Mainsectiontable>
                        <Mainsectiontabletbody>
                            <Mainsectiontabletbodytr>
                                <Mainsectiontabletbodytdnum>66</Mainsectiontabletbodytdnum>
                                <Mainsectiontabletbodytdcate>회원문의</Mainsectiontabletbodytdcate>
                                <Mainsectiontabletbodytdtitle>아이디와 비밀번호가 생각나지 않아요. 어떻게 찾을 수 있나요?</Mainsectiontabletbodytdtitle>
                            </Mainsectiontabletbodytr>
                        </Mainsectiontabletbody>
                    </Mainsectiontable>
                </Mainsection>
                <Answerwrap id='getidone'>
                    <Answer>(PC) 오른쪽 위의 [로그인] &gt; 화면 아래 [아이디 찾기] [비밀번호 찾기]</Answer>
                </Answerwrap>
            </Question>
            <Question onClick={()=>unfold('getidtwo')}>
            <Mainsection>
                <Mainsectiontable>
                    <Mainsectiontabletbody>
                        <Mainsectiontabletbodytr>
                            <Mainsectiontabletbodytdnum>65</Mainsectiontabletbodytdnum>
                            <Mainsectiontabletbodytdcate>주문/결제</Mainsectiontabletbodytdcate>
                            <Mainsectiontabletbodytdtitle>(샛별배송) 어제 주문했는데 오늘 아침에 배송이 안 됐어요. 왜 그런가요?</Mainsectiontabletbodytdtitle>
                        </Mainsectiontabletbodytr>
                    </Mainsectiontabletbody>
                </Mainsectiontable>
            </Mainsection>
            <Answerwrap id='getidtwo'>
                    <Answer>샛별배송은 밤 11시 이후 주문건은 다다음날 새벽에 배송됩니다.</Answer>
                </Answerwrap>
            </Question>
        </Main>
        </>
    )
}

export default Faq;