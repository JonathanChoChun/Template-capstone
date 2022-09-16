// API Key
// AvJgm3DKvQrS4gmbcqhvcSZD9CwbKdpnhA9cqVVOCa6LrrBaf4NkV7lhl9ZqswH7
import { ReactBingmaps } from 'react-bingmaps';
import {useState} from "react";
import {Button} from "reactstrap";
import Col from "react-bootstrap/Col";
import Card from "react-bootstrap/Card";
import Row from "react-bootstrap/Row";

const MapWithBingReact = () => {

    //51.491545,-0.0602213
    //51.4913505,-0.0595533
    //51.4914648,-0.0597577
    const [showMap, isShowMap] = useState(false);
    const [wayPoint,setWayPoint] = useState([]);
    const [pointOne,setPointOne] = useState(false);
    const [pointTwo,setPointTwo] = useState(false);
    const [pointThree,setPointThree] = useState(false);
    const handleClick = (event) => {
        if (event !== undefined) event.preventDefault();
        isShowMap(!showMap);
        console.log(showMap);
    };const handleOne = (event) => {
        if (event !== undefined) event.preventDefault();
        setPointOne(!pointOne);
        console.log(pointOne);
    };
    const handleTwo = (event) => {
        if (event !== undefined) event.preventDefault();
        setPointTwo(!pointTwo);
    };
    const handleThree = (event) => {
        if (event !== undefined) event.preventDefault();
        setPointThree(!pointThree);
    };
    return (
        <body>

            <Row>
                <Col key="1">
                    <Card >
                        <Card.Body>
                            <Card.Title>Buttons</Card.Title>
                            <Card.Text>
                                <Button onClick={handleClick}>Show/Hide - {((showMap) ? "Yes": "No")}</Button>
                                <Button onClick={handleOne}>WayOne - {((pointOne) ? "Yes": "No")}</Button>
                                <Button onClick={handleTwo}>WayTwo - {((pointTwo) ? "Yes": "No")}</Button>
                                <Button onClick={handleThree}>WayThree - {((pointThree) ? "Yes": "No")}</Button>
                            </Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
            {showMap &&
                <ReactBingmaps
                    bingmapKey = "AiLAGX6KhhdnCA80df2TnRtH-3jgPFr3Bh7ktsl79aqrVToP34AY9AGktCwSGkLL"
                    center = {[51.4913505, -0.0595533]}
                           zoom = {17}
                >
                </ReactBingmaps>
                // <ReactBingmaps
                //     bingmapKey="AiLAGX6KhhdnCA80df2TnRtH-3jgPFr3Bh7ktsl79aqrVToP34AY9AGktCwSGkLL"
                //     center={[51.4913505, -0.0595533]}
                //     zoom={17}
                //     // infoboxesWithPushPins={[
                //     //     {
                //     //         "location": [51.4913505, -0.0595533],
                //     //         "addHandler": "mouseover", //on mouseover the pushpin, infobox shown
                //     //         "infoboxOption": {title: 'test', description: 'big test'},
                //     //         "pushPinOption": {title: 'test pin', description: 'Pushpin'},
                //     //     }
                //     // ]
                //     // }
                // >
                // </ReactBingmaps>
            }

        </body>
    );
}
export default MapWithBingReact;