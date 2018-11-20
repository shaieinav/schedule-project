import React, { Component } from 'react';
import { Collapse, Nav, Navbar, NavbarBrand, NavbarToggler, NavItem, NavLink, Button, Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import {Link} from 'react-router-dom';
import '../common/AppHeader.css';

export default class AppNavBar extends Component {

    constructor(props) {

        super(props);

        this.state = {
            isOpen: false,
            modal: false
        };

        this.toggle = this.toggle.bind(this);
        this.toggleModal = this.toggleModal.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    toggleModal() {
        this.setState({
            modal: !this.state.modal
        });
    }

    render() {

        return (
            <Navbar color="dark" dark expand="md">
                {/*<div className="app-branding">*/}
                    <NavbarBrand tag={Link} to="/">Open University Course Scheduler</NavbarBrand>
                {/*</div>*/}
                <NavbarToggler onClick={this.toggle}/>
                <Collapse isOpen={this.state.isOpen} navbar>
                    {/*<div className="app-options">*/}
                        <Nav className="ml-auto" navbar>
                            <NavItem>
                                <NavLink href="/">Home</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="#" onClick={this.toggleModal}>
                                    About
                                </NavLink>
                                <Modal isOpen={this.state.modal} toggle={this.toggleModal} className={this.props.className}>
                                    <ModalHeader toggle={this.toggleModal}>About</ModalHeader>
                                    <ModalBody>
                                        This is a Project built by Shai Einav in the Advanced Programming in Java workshop at the Open University of Israel.
                                    </ModalBody>
                                    <ModalFooter>
                                        <Button color="secondary" onClick={this.toggleModal}>Cancel</Button>
                                    </ModalFooter>
                                </Modal>
                            </NavItem>
                            <NavItem>
                                <NavLink href="https://github.com/shaieinav/schedule-project">GitHub</NavLink>
                            </NavItem>
                            <div className="app-nav">
                            { this.props.authenticated ? (
                                <div>
                                    <ul>
                                        <li>
                                            <NavItem>
                                                <NavLink href="/profile">Profile</NavLink>
                                            </NavItem>
                                        </li>
                                        <li>
                                            <NavItem>
                                                <NavLink href="#" onClick={this.props.onLogout}>Logout</NavLink>
                                            </NavItem>
                                        </li>
                                    </ul>
                                </div>
                            ): (
                                <div>
                                    <ul>
                                        <li>
                                            <NavItem>
                                                <NavLink href="/login">Login</NavLink>
                                            </NavItem>
                                        </li>
                                        <li>
                                            <NavItem>
                                                <NavLink href="/signup">Signup</NavLink>
                                            </NavItem>
                                        </li>
                                    </ul>
                                </div>
                            )}
                            </div>
                        </Nav>
                    {/*</div>*/}
                </Collapse>
            </Navbar>
        );
    }
}