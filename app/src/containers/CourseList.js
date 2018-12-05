import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import {Container, Row, Col} from 'reactstrap';
import { Tabs, Tab } from 'react-bootstrap';
import AppNavBar from './AppNavBar';
import Loader from 'react-loader';
import SearchComponent from '../components/SearchComponent';
import CalendarComponent from '../components/CalendarComponent';
import { getCurrentUser } from '../util/APIUtils';
import '../App.css';
import LoadingIndicator from "../common/LoadingIndicator";

class CourseList extends Component {

    emptyEnrollmentItem = {
        enrolledId: {
            studentId: 0,
            groupNum: 0,
            groupLocation: '',
            courseNum: 0,
            semesterName: ''
        }
    };

    emptyStudiesItem = {
        studiesId: {
            studentId: 0,
            courseNum: 0
        },
        studyDay: '',
        studyTime: ''
    };

    constructor(props) {

        super(props);

        this.state = {
            coursesList: [],
            semesterData: [],
            query: '',
            selectedOption: [],
            courseGroupObject: [],
            courseName: '',
            chosenCourseData: [],
            activeTab: 1,
            loaded: false,
            //
            enrollmentItem: this.emptyEnrollmentItem,
            studiesItem: this.emptyStudiesItem,
            studentId: 0,
            isAuthenticated: false,
            loading: false,
            currentUserCourses: {}
        };

        this.handleTabSelect = this.handleTabSelect.bind(this);
        this.handleAddingCourseToCalendar = this.handleAddingCourseToCalendar.bind(this);
    }

    async componentDidMount() {
        fetch('http://localhost:8080/api/courses')
            .then(response => response.json())
            .then(data => this.setState({
                coursesList: data,
                loaded: true,
        }));
        fetch('http://localhost:8080/semesters')
            .then(response => response.json())
            .then(semesterData => this.setState({
                semesterData: semesterData,
                loaded: true,
        }));
        await getCurrentUser()
            .then(response => {
                this.setState({
                    isAuthenticated: true,
                    loading: false,
                    studentId: response.studentId,
                });
            })
            .catch(error => {
                console.log('Error in CourseList -> handleAddingCourseToCalendar -> getCurrentUser');
                this.setState({
                    loading: false
                });
            });
        await fetch(`http://localhost:8080/enrolled/${this.state.studentId}`)
            .then(response => response.json())
            .then(userCoursesData => this.setState({
                currentUserCourses: userCoursesData,
        }));
        console.log("user with student id = " + this.state.studentId + " has the following courses:\n" + this.state.currentUserCourses);
    }

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    };

    handleChosenCourse = (courseNum) => {
        let updatedChosenCourse, updatedCourseName, updatedChosenCourseData;
        fetch(`http://localhost:8080/api/courses/${courseNum.value}`)
            .then(response => response.json())
            .then(data => {
                    updatedChosenCourse = data.courseGroups;
                    updatedChosenCourseData = data;
                    updatedCourseName = data.courseName;
                    console.log(data);
                    console.log('course name: ' + updatedCourseName);
                    if (updatedChosenCourse && updatedCourseName) {
                        console.log('Setting states');
                        this.setState({
                            selectedOption: updatedChosenCourse,
                            courseName: updatedCourseName,
                            chosenCourseData: updatedChosenCourseData,
                        });
                        console.log('Finished setting states');
                    }
                }
            )
    };

    async handleAddingCourseToCalendar(event, courseGroupObj, courseData) {
        event.preventDefault();

        this.setState({
            courseGroupObject: courseGroupObj,
            query: '',
            chosenCourseData: courseData,
        });
        console.log('course info:\n' + this.state.courseName);
        console.log('course data:\n' + courseData.courseName);
        console.log('course group object:\n' + courseGroupObj[0].groupNum);

        //
        await getCurrentUser()
            .then(response => {
                this.setState({
                    isAuthenticated: true,
                    loading: false,
                    studentId: response.studentId,
                });
                console.log('user authenticated in CourseList:', this.state.isAuthenticated);
                console.log('user studentId in CourseList:', this.state.studentId);

                //
                let enrollmentItem = {...this.state.enrollmentItem};
                console.log("enrollment item:", enrollmentItem);
                console.log("current user is authenticated:", this.state.isAuthenticated);
                console.log("current user id:", this.state.studentId);
                enrollmentItem.enrolledId.studentId = this.state.studentId;
                enrollmentItem.enrolledId.groupNum = courseGroupObj[0].courseGroupId.groupNum;
                enrollmentItem.enrolledId.groupLocation = courseGroupObj[0].courseGroupId.groupLocation;
                enrollmentItem.enrolledId.courseNum = courseGroupObj[0].courseGroupId.courseNum;
                enrollmentItem.enrolledId.semesterName = courseGroupObj[0].courseGroupId.semesterName;
                console.log("updated enrollment item:",  enrollmentItem);
                this.setState({enrollmentItem});
                console.log("updated enrollment item to POST:",  this.state.enrollmentItem);
                //
                let studiesItem = {...this.state.studiesItem};
                console.log("studies item:", studiesItem);
                studiesItem.studiesId.studentId = this.state.studentId;
                studiesItem.studiesId.courseNum = courseGroupObj[0].courseGroupId.courseNum;
                studiesItem.studyDay = courseGroupObj[0].day;
                studiesItem.studyTime = courseGroupObj[0].hours;
                console.log("updated study item:",  studiesItem);
                this.setState({studiesItem});
                console.log("updated studies item to POST:",  this.state.studiesItem);
            }).catch(error => {
                console.log('Error in CourseList -> handleAddingCourseToCalendar -> getCurrentUser');
                this.setState({
                    loading: false
                });
        });

        //
        await fetch('http://localhost:8080/enrolled', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.enrollmentItem),
        });

        await fetch('http://localhost:8080/studies', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(this.state.studiesItem),
        });
    };

    handleTabSelect(activeTab) {
        this.setState({ activeTab });
    }

    render() {

        if(this.state.loading) {
            return <LoadingIndicator />
        }

        const {coursesList, query, selectedOption, courseGroupObject, chosenCourseData, semesterData,
            currentUserCourses, studentId} = this.state;

        console.log("used chosenCourseData in render");
        console.log("user authenticated in CourseList render:", this.props.authenticated);

        const filteredCourses =
            query === ''
                ? coursesList
                : coursesList.filter((course) => (
                    course.courseName.toLowerCase().includes(query.toLowerCase())
                ));

        return (

            <Loader loaded={this.state.loaded}>

            <div>
                <Container fluid>
                    <Row>
                        <Col md={3} xs={12}>
                            <SearchComponent
                                query={query}
                                selectedOption={selectedOption}
                                chosenCourseData={chosenCourseData}
                                semesterData={semesterData}
                                filteredCourses={filteredCourses}
                                updateQuery={this.updateQuery}
                                handleChosenCourse={this.handleChosenCourse}
                                handleAddingCourseToCalendar={this.handleAddingCourseToCalendar}
                            />
                        </Col>
                        <Col md={9} xs={12}>
                            <Tabs
                                activeKey={this.state.activeTab}
                                onSelect={this.handleTabSelect}
                                id="controlled-tab"
                            >
                                <Tab eventKey={1} title="סמסטר א">
                                    <CalendarComponent
                                        courseObject={courseGroupObject}
                                        activeTabIndex= '1'
                                        currentUserCourses={currentUserCourses}
                                        studentId={studentId}
                                        // coursesList={coursesList}
                                    />
                                </Tab>
                                <Tab eventKey={2} title="סמסטר ב">
                                    <CalendarComponent
                                        courseObject={courseGroupObject}
                                        activeTabIndex= '2'
                                        currentUserCourses={currentUserCourses}
                                        studentId={studentId}
                                        // coursesList={coursesList}
                                    />
                                </Tab>
                                <Tab eventKey={3} title="סמסטר ג">
                                    <CalendarComponent
                                        courseObject={courseGroupObject}
                                        activeTabIndex= '3'
                                        currentUserCourses={currentUserCourses}
                                        studentId={studentId}
                                        // coursesList={coursesList}
                                    />
                                </Tab>
                            </Tabs>
                        </Col>
                    </Row>
                </Container>
            </div>
            </Loader>
        );
    }
}

export default withRouter(CourseList);