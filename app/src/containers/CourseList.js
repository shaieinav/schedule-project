import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import { Link, withRouter } from 'react-router-dom';
import {TabContent, TabPane, Nav, NavItem, NavLink, Button, Container, Row, Col} from 'reactstrap';
import AppNavBar from './AppNavBar';
//
import { withCookies } from 'react-cookie';
//
import SearchComponent from '../components/SearchComponent';
import CalendarComponent from '../components/CalendarComponent';
import CoursesInfoComponent from '../components/CoursesInfoComponent';
import '../App.css';
import classnames from 'classnames';

class CourseList extends Component {

    constructor(props) {

        super(props);

        //
        const {cookies} = props;
        //

        this.state = {
            //
            isAuthenticated: false,
            user: undefined,
            csrfToken: cookies.get('XSRF-TOKEN'),
            //
            coursesList: [],
            semestersList: [],
            isLoading: true,
            query: '',
            selectedOption: [],
            courseGroupObject: [],
            courseName: '',
            activeTabIndex: '1',
        };

        //
        this.login = this.login.bind(this);
        this.logout = this.logout.bind(this);
        //

        this.handleTabClick = this.handleTabClick.bind(this);
    }

    // componentDidMount() {
    //     fetch('courses')
    //         .then(response => response.json())
    //         .then(data => this.setState({
    //             coursesList: data,
    //             isLoading: false
    //         }));
    //     fetch('semesters')
    //         .then(response => response.json())
    //         .then(data => this.setState({
    //             semestersList: data,
    //         }));
    // }

    //
    async componentDidMount() {

        const response = await fetch('/user', {credentials: 'include'});
        const body = await response.text();
        if (body === '') {
            this.setState(({isAuthenticated: false}))
        } else {
            this.setState({isAuthenticated: true, user: JSON.parse(body)})
        }

        fetch('courses', {credentials: 'include'})
            .then(response => response.json())
            .then(data => this.setState({coursesList: data, isLoading: false}))
            .catch(() => this.props.history.push('/'));
        fetch('semesters', {credentials: 'include'})
            .then(response => response.json())
            .then(data => this.setState({semestersList: data,}))
            .catch(() => this.props.history.push('/'));
    }
    //

    //
    login() {
        let port = (window.location.port ? ':' + window.location.port : '');
        if (port === ':3000') {
            port = ':8080';
        }
        window.location.href = '//' + window.location.hostname + port + '/private';
    }

    logout() {
        console.log('logging out...');
        fetch('/logout', {method: 'POST', credentials: 'include',
            headers: {'X-XSRF-TOKEN': this.state.csrfToken}}).then(res => res.json())
            .then(response => {
                window.location.href = response.logoutUrl + "?id_token_hint=" +
                    response.idToken + "&post_logout_redirect_uri=" + window.location.origin;
            });
    }
    //

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    };

    handleChosenCourse = (courseNum) => {
        let updatedChosenCourse;
        let updatedCourseName;
        fetch(`courses/${courseNum.value}`)
            .then(response => response.json())
            .then(data => {
                    updatedChosenCourse = data.courseGroups;
                    updatedCourseName = data.courseName;
                    console.log(data);
                    console.log(updatedCourseName);
                    if (updatedChosenCourse && updatedCourseName) {
                        console.log('Setting states');
                        this.setState({
                            selectedOption: updatedChosenCourse,
                            courseName: updatedCourseName
                        });
                        console.log('Finished setting states');
                    }
                }
            )
    };

    handleAddingCourseToCalendar = (event, courseObj) => {
        event.preventDefault();
        this.setState({
            courseGroupObject: courseObj,
            query: '',
        })
        console.log('course object:\n' + courseObj.courseNum)
    };

    handleTabClick(tab) {
        if (this.state.activeTabIndex !== tab) {
            this.setState({
                activeTabIndex: tab
            });
        }
    }

    render() {

        const {coursesList, isLoading, query, selectedOption, courseGroupObject, courseName, semestersList} = this.state;

        const filteredCourses =
            query === ''
                ? coursesList
                : coursesList.filter((course) => (
                    course.courseName.toLowerCase().includes(query.toLowerCase())
                ));


        //
        const message = this.state.user ?
            <h2>Welcome, {this.state.user.name}!</h2> :
            <p>Please log in to manage your courses.</p>;

        const button = this.state.isAuthenticated ?
            <div>
                <Button color="link"><Link to="/">Manage Courses</Link></Button>
                <br/>
                <Button color="link" onClick={this.logout}>Logout</Button>
            </div> :
            <Button color="primary" onClick={this.login}>Login</Button>;
        //


        // const semesterACourses = coursesList.filter((course) => (
        //     course.courseGroups.courseGroupId.semesterName.includes('א')
        // ));
        // const semesterBCourses = coursesList.filter((course) => (
        //     course.courseGroups.courseGroupId.semesterName.includes('ב')
        // ));
        // const semesterCCourses = coursesList.filter((course) => (
        //     course.courseGroups.courseGroupId.semesterName.includes('ג')
        // ));

        // if (isLoading) {
        //     return <p>Loading... Please Wait</p>;
        // }

        return (
            <div>

                <AppNavBar/>

                <Container fluid>
                    {/**/}
                    {message}
                    {button}
                    {/**/}
                    <Row>
                        <h3>My Courses</h3>
                    </Row>
                    <Row>
                        <Col md={3} xs={12}>
                            <SearchComponent
                                query={query}
                                selectedOption={selectedOption}
                                semestersList={semestersList}
                                filteredCourses={filteredCourses}
                                updateQuery={this.updateQuery}
                                handleChosenCourse={this.handleChosenCourse}
                                handleAddingCourseToCalendar={this.handleAddingCourseToCalendar}
                            />
                        </Col>
                        <Col md={9} xs={12}>
                            <Nav tabs>
                                <NavItem>
                                    <NavLink
                                        className={classnames({ active: this.state.activeTabIndex === '1' })}
                                        onClick={() => { this.handleTabClick('1'); }}
                                    >
                                        סמסטר א
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink
                                        className={classnames({ active: this.state.activeTabIndex === '2' })}
                                        onClick={() => { this.handleTabClick('2'); }}
                                    >
                                        סמסטר ב
                                    </NavLink>
                                </NavItem>
                                <NavItem>
                                    <NavLink
                                        className={classnames({ active: this.state.activeTabIndex === '3' })}
                                        onClick={() => { this.handleTabClick('3'); }}
                                    >
                                        סמסטר ג
                                    </NavLink>
                                </NavItem>
                            </Nav>
                            <TabContent activeTab={this.state.activeTabIndex}>
                                <TabPane tabId="1">
                                    <Row>
                                        <Col sm="12">
                                            <CalendarComponent
                                                courseObject={courseGroupObject}
                                                activeTabIndex='1'
                                            />
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane tabId="2">
                                    <Row>
                                        <Col sm="12">
                                            <CalendarComponent
                                                courseObject={courseGroupObject}
                                                activeTabIndex='2'
                                            />
                                        </Col>
                                    </Row>
                                </TabPane>
                                <TabPane tabId="3">
                                    <Row>
                                        <Col sm="12">
                                            <CalendarComponent
                                                courseObject={courseGroupObject}
                                                activeTabIndex='3'
                                            />
                                        </Col>
                                    </Row>
                                </TabPane>
                            </TabContent>
                            {/*<Row>*/}
                                {/*<CalendarComponent*/}
                                    {/*courseObject={courseGroupObject}*/}
                                {/*/>*/}
                            {/*</Row>*/}
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default withCookies(withRouter(CourseList));