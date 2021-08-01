import React, {useEffect, useState} from 'react';
import styled from 'styled-components';
import findEventsApi from "./FindEventsApi";

const Eventwrap = styled.div``
const Container = styled.div`width: 1050px; margin: 0 auto;`
const Row = styled.div``

const Eventli = styled.div`display: block; height: 200px; background: #dbdbdb; margin: 10px 0px; &:hover {cursor: pointer;}`

const Event = ({history}) => {
    const [events,setEvents] = useState(null);

    const move = (event) => {
        if(event.classification=== "DetailedEvent")
            history.push('/shop/main/event?name='+event.fileName);
        else
            history.push('/');

    }
    useEffect(()=> {
        findEventsApi().then(eventPromises => {
            setEvents(eventPromises)
        });
    },[]);

    const imgs = events ? events.map((event)=>{
        const base64Image = event ? 'data:image/png;base64,' + event.imageByteArray:"";
        return <Eventli onClick={()=>move(event)}><img src={{uri: base64Image}}/></Eventli>;
    }) : "";
    console.log(events)
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