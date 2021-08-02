import React, {useEffect, useState} from 'react'
import styled from 'styled-components';
import findFaqApi from "./FindFaqApi";
import queryString from "query-string";
import findTotalFaqsApi from "./FindTotalFaqsApi";

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
const NumberButton = styled.div`border: 2px solid black; width:20px; margin:5px; cursor:pointer`
const PageChangeButtons = styled.div`padding-top:30px; display: flex; justify-content: center; text-align: center; `;
const NextButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const PrevButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const FirstPageButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`
const LastPageButton = styled.div`font-weight:bold; border: 1px solid black; width:20px; margin:5px; cursor:pointer`

const Faq = (props) => {
    const page = queryString.parse(props.location.search).page;
    const [faqs,setFaqs] = useState(null);
    const [totalfaqs,setTotalfaqs] = useState(null);
    const [viewnum,setViewnum] = useState(-1);

    useEffect(() => {
        findFaqApi(page).then(faqPromise => {
            setFaqs(faqPromise)
        });
    }, [page]);

    useEffect(() => {
        findTotalFaqsApi().then(faqsPromise => {
            setTotalfaqs(faqsPromise)
        });
    }, []);

    const unfold = (id) => {
        const now = document.getElementById(id)
        if(viewnum === id) {
            now.style.display = 'none'
            setViewnum(-1)
        } else {
            if(viewnum === -1) {
                now.style.display = 'block'
            } else {
                const prev = document.getElementById(viewnum)
                prev.style.display = 'none'
                now.style.display = 'block'
            }
            setViewnum(id)
        }
    }

    const numbers = [...Array((parseInt(totalfaqs/10)+1)>=5 ? 5 : parseInt(totalfaqs/10)+1)].map((value, index)=>
        <NumberButton onClick={()=>
            page?props.history.push("/shop/service/faq?page="+(parseInt((page-1)/5)*5+index+1)) :props.history.push("/shop/service/faq?page="+(index+1)) }
        >{page?parseInt((page-1)/5)*5+index+1 : index+1} </NumberButton>
    );

    const FaqSet = faqs ? (faqs.length>=1 ?faqs.map((faq)=>
        <Question onClick={()=>unfold(faq.id)}>
            <Mainsection>
                <Mainsectiontable>
                    <Mainsectiontabletbody>
                        <Mainsectiontabletbodytr>
                            <Mainsectiontabletbodytdnum>{faq.id}</Mainsectiontabletbodytdnum>
                            <Mainsectiontabletbodytdcate>{faq.category}</Mainsectiontabletbodytdcate>
                            <Mainsectiontabletbodytdtitle>{faq.title}</Mainsectiontabletbodytdtitle>
                        </Mainsectiontabletbodytr>
                    </Mainsectiontabletbody>
                </Mainsectiontable>
            </Mainsection>
            <Answerwrap id={faq.id}>
                <Answer>{faq.content}</Answer>
            </Answerwrap>
        </Question>) : "검색결과가 없습니다 다시 검색해주세요"
    ):"";
    return(
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
            {FaqSet}
            <PageChangeButtons>
                <FirstPageButton onClick={()=>props.history.push("/shop/service/faq?page=1")}>
                    〈〈
                </FirstPageButton>
                <PrevButton onClick={()=>
                    (page <= 1)?props.history.push("/shop/service/faq?page=1"):props.history.push("/shop/service/faq?page="+ (page-1))}
                >
                    〈
                </PrevButton>
                {numbers}
                <NextButton onClick={()=>
                    (page > parseInt(totalfaqs/10))?props.history.push("/shop/service/faq?page="+parseInt(totalfaqs/10+1)):props.history.push("/shop/service/faq?page="+ (Number(page)+1))}
                >
                    〉
                </NextButton>
                <LastPageButton onClick={()=>props.history.push("/shop/service/faq?page="+ parseInt(totalfaqs/10+1))}>
                    〉〉
                </LastPageButton>
            </PageChangeButtons>
        </Main>
        </>
    )
};

export default Faq;