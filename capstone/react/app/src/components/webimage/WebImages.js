
import React, { useEffect, useState } from 'react';
import {getWebImage, getWebImages} from "../../api/ImagesApi";
import Col from "react-bootstrap/Col";
import Card from "react-bootstrap/Card";
import {LinkContainer} from "react-router-bootstrap";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import MemeImage from "./MemeImage";

const WebImages = () => {
    const [imageList, setImageList] = useState([]);
    const getData = async () => {
        getWebImages().then(images => {
            setImageList(images);
        });
    }

    useEffect(() => {
        getData();
    }, []);
    return (

            <>
                <Row>
                    {imageList?.map(image => (
                        <Col key={image.id}>
                            <Card className="my-2"
                                  style={{
                                      width: '18rem'
                                  }}>
                                <Card.Body>
                                    <Card.Title>{image.fileName}</Card.Title>
                                    <Card.Text>
                                        <MemeImage imageId={image.id} cssClass="meme" altText={image.fileName} />
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        </Col>
                    ))}
                </Row>
            </>
    )
}
export default WebImages;
