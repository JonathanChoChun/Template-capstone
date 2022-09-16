import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { LinkContainer } from 'react-router-bootstrap';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import Form from 'react-bootstrap/Form';
import { ButtonGroup, Button } from 'react-bootstrap';
import './App.css';
import Hello from './components/hello/Hello.js';
import ActorGrid from './components/grid/ActorGrid';
import Home from './components/home/Home';
import React, { useState, useEffect } from 'react';
import Modal from 'react-bootstrap/Modal';
import {getUser, getUserFromCookie, loginUser, logoutUser} from './api/UserApi';
import AllActors from './components/actor/AllActors';
import { getAllActors } from './api/ActorApi';
import SampleModalComponent from './components/modal/SampleModalComponent';
import AuthExample from './components/auth/AuthExample';
import MovieGrid from "./components/movie/MovieGrid";
import AllMovies from "./components/movie/AllMovies";
import {getAllMovies} from "./api/MovieApi";
import MovieDetail from "./components/movie/MovieDetail";
import EditProfile from "./components/user/EditProfile";
import WebImage from "./components/webimage/WebImage";
import MapWithGoogle from "./components/maps/MapWithGoogle";
import MapWithBing from "./components/maps/MapWithBing";
import RegisterUser from "./components/user/RegisterUser";
import WebImages from "./components/webimage/WebImages";
import MapWithBingReact from "./components/maps/MapWithBingReact";




window.ADMIN = "Admin";
window.VIP = "Vip"
window.STANDARD = "Standard"
window.GUEST = "GUEST";

function App() {

  let defaultUser = {
    'firsName': '',
    'lastName': '',
    'name': '',
    'token': '',
    'role': 'guest'
  };

  const [user, setUser] = useState(defaultUser);

  const [showModal, setShowModal] = useState(false);

  const handleShowModalOn = () => {
    setShowModal(true);
  };

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');


  const doSetUsername = (event) => {
    setUsername(event.target.value);
  };
  const doSetPassword = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = (event) => {
    // validate
    if (event !== undefined) event.preventDefault();
    const login = async () => {
      loginUser({ 'userName': username, 'password': password }).then(data=>{
        setUser(data);
      });
    }
    login().then(data=>{
      setPassword('');
      setShowModal(false);
    });
  };

  const handleLogout = async () => {
    logoutUser().then(data => {
      const userUpdate = async () => {
        getUser().then(user => {
              setUser(user);
              setPassword('');
              setShowModal(false);
            }
        );
      }
    });
  }

  const handleCancel = () => {
    setPassword('');
    setShowModal(false);
  };

  const [actors, setActors] = useState([]);
  useEffect(() => {
    const getActors = async () => {
      const data = await getAllActors();
      setActors(data);
    }
    getActors();
  }, []);

  const [movies, setMovies] = useState([]);
  useEffect(() => {
    const getMovies = async () => {
      const data = await getAllMovies();
      setMovies(data);
    }
    getMovies();
  }, []);

  useEffect(() => {
    const getUserFromCookie = async () => {
      getUser().then( user=> {
            setUser(user);
          }
      );
    }
    getUserFromCookie();
  }, []);

  return (
    <div className="wrapper">
      <Router>
        <Nav>
          <Navbar bg="light" variant="light" expand="lg">
            <Container>
              <Navbar.Brand>
                <img
                  // src={`${process.env.PUBLIC_URL}/images/${imageVarName}.png}
                  src="/images/movie.png"
                  width="30"
                  height="30"
                  className="d-inline-block align-top"
                  alt="Movie Logo"
                  onClick={handleShowModalOn}
                />
              </Navbar.Brand>
              <Navbar.Toggle aria-controls="basic-navbar-nav" />
              <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="justify-content-center">
                  <LinkContainer to="/home">
                    <Nav.Link>Home</Nav.Link>
                  </LinkContainer>
                  <LinkContainer to="/modalcomponent">
                    <Nav.Link>Modal</Nav.Link>
                  </LinkContainer>
                  <LinkContainer to="/auth">
                    <Nav.Link>Auth</Nav.Link>
                  </LinkContainer>
                  <LinkContainer to="/hello">
                    <Nav.Link>Say Hello</Nav.Link>
                  </LinkContainer>
                  {user.role === window.ADMIN &&
                    <NavDropdown title="Actors" id="basic-nav-dropdown">
                      <LinkContainer to="/actors">
                        <NavDropdown.Item>Search Actors</NavDropdown.Item>
                      </LinkContainer>
                      <LinkContainer to="/allActors">
                        <NavDropdown.Item>All Actors</NavDropdown.Item>
                      </LinkContainer>
                      <NavDropdown.Divider />
                      <NavDropdown.Item>Create Actor</NavDropdown.Item>
                    </NavDropdown>
                  }
                  {user.role === window.ADMIN &&
                      <NavDropdown title="Movies" id="basic-nav-dropdown">
                        <LinkContainer to="/movies">
                          <NavDropdown.Item>Movies Grid</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/allMovies">
                          <NavDropdown.Item>Search Movies</NavDropdown.Item>
                        </LinkContainer>
                        <NavDropdown.Divider />

                        <LinkContainer to="/editProfile/1">
                          <NavDropdown.Item>User Edit</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/googleMapSample">
                          <NavDropdown.Item>Google Map</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/bingMapSample">
                          <NavDropdown.Item>Bing Map</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/registerUser">
                          <NavDropdown.Item>Register User</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/webImages">
                          <NavDropdown.Item>Meme List Sample</NavDropdown.Item>
                        </LinkContainer>
                        <LinkContainer to="/mapWithBingReact">
                          <NavDropdown.Item>Other Bing Map</NavDropdown.Item>
                        </LinkContainer>

                      </NavDropdown>
                  }
                </Nav>
              </Navbar.Collapse>
              <Form className="d-flex">
                <Form.Control
                  type="search"
                  placeholder="Search"
                  className="me-2"
                  aria-label="Search"
                />
                <Button variant="outline-success">Search</Button>
              </Form>

            </Container>
          </Navbar>
        </Nav>
        {user.id === 0 &&
          <Modal show={showModal} onHide={handleCancel}>
            <Form onSubmit={handleLogin}>
              <Modal.Header closeButton>
                <Modal.Title>Log In {username}</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <Form.Group className="mb-3" controlId="formBasicEmail">
                  <Form.Label>Username</Form.Label>
                  <Form.Control type="email" placeholder="Enter username"
                    value={username} onChange={doSetUsername} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Password"
                    value={password} onChange={doSetPassword} />
                </Form.Group>
              </Modal.Body>
              <Modal.Footer>
                <ButtonGroup>
                  <Button variant="primary" onClick={handleLogin} type="submit">
                    Login
                  </Button>
                  <Button variant="secondary" onClick={handleCancel}>
                    Cancel
                  </Button>
                </ButtonGroup>
              </Modal.Footer>
            </Form>
          </Modal>
        }
        {user.id !== 0 &&
          <Modal show={showModal} onHide={handleCancel}>
            <Form>
              <Modal.Header closeButton>
                <Modal.Title>Log Out</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                <ButtonGroup>
                  <Button variant="primary" onClick={handleLogout}  type="submit">
                    Logout
                  </Button>
                  <Button variant="secondary" onClick={handleCancel}>
                    Cancel
                  </Button>
                </ButtonGroup>
              </Modal.Body>
            </Form>
          </Modal>
        }

        <div>
          <Routes>
            <Route
              path="/" exact
              element={<Home />} />
            <Route
              path="/home" exact
              element={<Home />} />
            <Route
              path="/modalcomponent" exact
              element={<SampleModalComponent />} />
              <Route
                path="/auth" exact
                element={<AuthExample />} />
            <Route
              path="/hello"
              element={<Hello user={user} />} />
            <Route
              path="/actors"
              element={<ActorGrid />} />
            <Route
              path="/allActors"
              element={<AllActors actors={actors} />} />
            <Route
                path="/movies"
                element={<MovieGrid />} />
            <Route
                path="/allMovies"
                element={<AllMovies movies={movies} />} />
            <Route
                path="/movieDetail/:movieId"
                element={<MovieDetail  />} />
            <Route
                path="/editProfile/:userId"
                element={<EditProfile  />} />
            <Route
                path="/googleMapSample"
                element={<MapWithGoogle  />} />
            <Route
                path="/bingMapSample"
                element={<MapWithBing  />} />
            <Route
                path="/registerUser"
                element={<RegisterUser  />} />
            <Route
                path="/webImages"
                element={<WebImages  />} />
            <Route
                path="/mapWithBingReact"
                element={<MapWithBingReact  />} />

          </Routes>
        </div>
      </Router>
    </div>
  );
}

export default App;