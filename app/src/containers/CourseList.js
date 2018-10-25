import React, {Component} from 'react';
import { withRouter } from 'react-router-dom';
import {Container, Row, Col} from 'reactstrap';
import { Tabs, Tab } from 'react-bootstrap';
import AppNavBar from './AppNavBar';
import Loader from 'react-loader';
import SearchComponent from '../components/SearchComponent';
import CalendarComponent from '../components/CalendarComponent';
import '../App.css';

class CourseList extends Component {

    constructor(props) {

        super(props);

        this.state = {
            coursesList: [],
            query: '',
            selectedOption: [],
            courseGroupObject: [],
            courseName: '',
            chosenCourseData: [],
            activeTab: 1,
            loaded: false,
        };

        this.handleTabSelect = this.handleTabSelect.bind(this);
    }

    componentDidMount() {
        fetch('api/courses')
            .then(response => response.json())
            .then(data => this.setState({
                coursesList: data,
                loaded: true
            }));
    }

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    };

    handleChosenCourse = (courseNum) => {
        let updatedChosenCourse, updatedCourseName, updatedChosenCourseData;
        fetch(`api/courses/${courseNum.value}`)
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

    handleAddingCourseToCalendar = (event, courseGroupObj) => {
        event.preventDefault();
        this.setState({
            courseGroupObject: courseGroupObj,
            query: '',
        })
        console.log('course object:\n' + this.state.courseName)
    };

    handleTabSelect(activeTab) {
        this.setState({ activeTab });
    }

    render() {

        const {coursesList, query, selectedOption, courseGroupObject, chosenCourseData} = this.state;

        const filteredCourses =
            query === ''
                ? coursesList
                : coursesList.filter((course) => (
                    course.courseName.toLowerCase().includes(query.toLowerCase())
                ));

        return (

            <Loader loaded={this.state.loaded}>
            <div>
                <AppNavBar/>
                <Container fluid>
                    <Row>
                        <Col md={3} xs={12}>
                            <SearchComponent
                                query={query}
                                selectedOption={selectedOption}
                                chosenCourseData={chosenCourseData}
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
                                    />
                                </Tab>
                                <Tab eventKey={2} title="סמסטר ב">
                                    <CalendarComponent
                                        courseObject={courseGroupObject}
                                        activeTabIndex= '2'
                                    />
                                </Tab>
                                <Tab eventKey={3} title="סמסטר ג">
                                    <CalendarComponent
                                        courseObject={courseGroupObject}
                                        activeTabIndex= '3'
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