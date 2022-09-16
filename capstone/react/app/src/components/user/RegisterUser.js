import {useState} from "react";
import Form from 'react-bootstrap/Form';
import {Button, ButtonGroup} from "react-bootstrap";
import {addUserDetail, getUserDetail, saveUserDetail} from "../../api/UserApi";
import Modal from "react-bootstrap/Modal";
import ApiFetch from "../../api/ApiContext";
import Loading from "../util/Loading";


const RegisterUser = () => {
    const defaultUser = {
        userName:'',
        password:'',
        confirmPassword:'',
        role:'Standard',
        lastName: '',
        firstName:''
    };
    const defaultErrors = {
        userName:'',
        password:'',
        confirmPassword:''
    };
    const defaultValid = {
        userName:true,
        password:false,
        confirmPassword:false
    };
    const [user, setUser] = useState(defaultUser)
    const [errors, setErrors] = useState(defaultErrors);
    const [validItems, setValidItems] = useState(defaultValid);

    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [lastName, setLastName] = useState('');
    const [firstName, setFirstName] = useState('');
    const [role, setRole] = useState('Standard');

    const [showModal, setShowModal] = useState(false);

    const handleUserInput = (event) => {
        event.preventDefault();
        const {id,value} = event.target;
        switch (id) {
            case 'userName':
                setUserName(value);
                user.userName = value;
                // if (event.target.validationMessage == null) {
                //     errors.userName = (userName.length < 5) ? 'Username must be at least 5 characters' : '';
                // } else {
                //     errors.userName = event.target.validationMessage;
                // }
                // validItems.userName = (errors.userName === '');
                break;
            case 'password':
                setPassword(value);
                user.password = value;
                errors.password = (password.length < 7) ? 'Password must be at least 7 characters' : '';
                validItems.password = (errors.password === '');
                break;
            case 'confirmPassword':
                setConfirmPassword(value);
                user.confirmPassword = value;
                errors.confirmPassword = (value === password) ? '' : 'Passwords must match'
                validItems.confirmPassword = (errors.confirmPassword === '');
                break;
            case 'firstName':
                setFirstName(value);
                user.firstName = value;
                break;
            case 'lastName':
                setLastName(value);
                user.lastName = value;
                break;
            case 'role':
                setRole(value);
                user.role = value;
                break;
            default:
                break;
        }
        setUser(user);
        setErrors(errors);
        setValidItems(validItems);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const save = async () => {
            const {data: user, error: userError, loading: userLoading} = ApiFetch("user","POST",user);
            // addUserDetail(user).then(data => {
            //     setUser(data);
            //     setShowModal(true);
            // });
            if (userError) throw userError;
            if (userLoading) return <Loading />;
        }
        save();
    }

    return (
            <>
                <Modal show={showModal}>
                        <Modal.Header closeButton>
                            <Modal.Title>Awesome</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            You saved it!
                        </Modal.Body>
                </Modal>
                <Form  className="container" onSubmit={handleSubmit}>
                    <div className="row mt-4">
                        <Form.Group className="col-6" key="username">
                            <Form.Label htmlFor="userName">Username</Form.Label>
                            <Form.Control type="text"  id="userName" value={userName} onChange={handleUserInput} />
                            <Form.Text className="error">{errors.userName}</Form.Text>
                        </Form.Group>
                    </div>

                    <div className="row mt-4">
                        <Form.Group className="col-6" key="password">
                            <Form.Label htmlFor="password">Password</Form.Label>
                            <Form.Control type="password"  id="password" value={password} onChange={handleUserInput}/>
                            <Form.Text className="error">{errors.password}</Form.Text>
                        </Form.Group>
                        <Form.Group className="col-6" key="confirmPassword">
                            <Form.Label htmlFor="confirmPassword">Confirm Password</Form.Label>
                            <Form.Control type="password"  id="confirmPassword" value={confirmPassword} onChange={handleUserInput}/>
                            <Form.Text className="error">{errors.confirmPassword}</Form.Text>
                        </Form.Group>
                    </div>

                    <div className="row mt-4">
                        <Form.Group className="col-6" key="firstName">
                            <Form.Label htmlFor="firstName">First Name</Form.Label>
                            <Form.Control type="text"  id="firstName" value={firstName} onChange={handleUserInput}/>
                        </Form.Group>
                        <Form.Group className="col-6" key="lastName">
                            <Form.Label htmlFor="lastName">Last Name</Form.Label>
                            <Form.Control type="text"  id="lastName" value={lastName} onChange={handleUserInput}/>
                        </Form.Group>
                    </div>
                    <div className="row mt-4">
                        <Form.Group className="col-4" key="role">
                            <Form.Label htmlFor="role">Role</Form.Label>
                            <Form.Control
                                as="select"
                                id="role"
                                value={role}
                                onChange={handleUserInput}
                            >
                                <option value="Standard">Standard</option>
                                <option value="Admin">Admin</option>
                            </Form.Control>
                        </Form.Group>
                    </div>
                    <div className="row mt-4">
                        <Button className="col-2" variant="primary" type="submit"
                                disabled={!(validItems.userName && validItems.password && validItems.confirmPassword)}
                        >Save</Button>
                    </div>

                </Form>

            </>

    );
};

export default RegisterUser;
