import React, { useEffect, useState } from 'react';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import { getActorList } from '../../api/ActorApi';

const ActorGrid = () => {

  const [actors, setActors] = useState([]);

  useEffect(() => {
    const getActors = async() => {
      const data = await getActorList("ALLEN");
      setActors(data);
    }
    getActors();
        
  }, []);

  return (
    <Row xs={1} md={2} className="g-4">
      {actors.map(actor => (
        <Col key={actor.id}>
          <Card>
            <Card.Img variant="top" src="../../../images/happyface.png" />
            <Card.Body>
              <Card.Title>{actor.name}</Card.Title>
              <Card.Text>
                {actor.biography}
              </Card.Text>
            </Card.Body>
          </Card>
        </Col>
      ))}
    </Row>
  );
}
export default ActorGrid;