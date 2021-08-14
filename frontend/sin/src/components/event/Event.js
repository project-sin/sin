import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import findEventsApi from "./FindEventsApi";
import {BACKEND_ADDRESS} from "../../constants/ADDRESS";

const Eventwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Eventli = styled.div`display: block; height: 200px; background: #dbdbdb; margin: 10px 0px; &:hover {cursor: pointer;}`

const Event = ({history}) => {
    const [events,setEvents] = useState(null);

    const move = (event) => {
        if(event.classification=== "NonProducts")
            history.push('/shop/main/event?name='+ event.fileCode);
        else
            history.push('/shop/goods/goods_list?category=' + event.fileCode);

    }
    useEffect(()=> {
        findEventsApi().then(eventPromises => {
            setEvents(eventPromises)
        });
    },[]);

    const imgs = events ? events.map((event)=>{
        const uri = BACKEND_ADDRESS + "/shop/goods/event/display?filename=" + event.fileName;
        return <Eventli onClick={()=>move(event)}><img src= {uri} /></Eventli>;
    }) : "";

    return(
        <Eventwrap>
            <Container>
                <Row>
                    <ul>
                        {imgs}
                    </ul>
                </Row>
            </Container>
        </Eventwrap>
    )
}

export default Event;