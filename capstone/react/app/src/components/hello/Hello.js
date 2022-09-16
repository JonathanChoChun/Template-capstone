import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

const Hello = ({user}) => {

    const [showModal, setShowModal] = useState(false);

    const handleShowModalOn = () => {
        setShowModal(true);
    };
    const handleShowModalOff = () => {
        setShowModal(false);
    };

    return (
        <>
            <div className="App-header">
                <Button variant="primary" onClick={handleShowModalOn}>
                    Say Hello
                </Button>
            </div>
            <Modal show={showModal} onHide={handleShowModalOff}>
                <Modal.Header closeButton>
                    <Modal.Title>Greetings</Modal.Title>
                </Modal.Header>
                <Modal.Body><hr />
                    <h1>Hello {user.firstName}</h1>
                    <hr />
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleShowModalOff}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}
export default Hello;