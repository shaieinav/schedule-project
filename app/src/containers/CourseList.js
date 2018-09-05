import React, {Component} from 'react';
import {Container, Row, Col} from 'reactstrap';
import AppNavBar from './AppNavBar';
import SearchComponent from '../components/SearchComponent';
import CalendarComponent from '../components/CalendarComponent';

class CourseList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            coursesList: [],
            isLoading: true,
            query: '',
            selectedOption: [],
            courseGroupObject: []
        };
    }

    componentDidMount() {
        fetch('courses')
            .then(response => response.json())
            .then(data => this.setState({
                coursesList: data,
                isLoading: false
            }));
    }

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    };

    handleChosenCourse = (courseNum) => {
        let updatedChosenCourse;
        fetch(`courses/${courseNum.value}`)
            .then(response => response.json())
            .then(data => {
                    updatedChosenCourse = data.courseGroups;
                    if (updatedChosenCourse) {
                        this.setState({
                            selectedOption: updatedChosenCourse
                        })
                    }
                }
            )
    };

    handleAddingCourseToCalendar = (event, courseObj) => {
        event.preventDefault();
        this.setState({
            courseGroupObject: courseObj,
            query: ''
        })
    }

    render() {

        const {coursesList, isLoading, query, selectedOption, courseGroupObject} = this.state;

        const filteredCourses =
            query === ''
                ? coursesList
                : coursesList.filter((course) => (
                    course.courseName.toLowerCase().includes(query.toLowerCase())
                ));

        if (isLoading) {
            return <p>Loading... Please Wait</p>;
        }

        return (
            <div>
                <AppNavBar/>
                <Container fluid>

                    {/*                    <div className="float-right">
                        <Button color="success" tag={Link} to="/courses/new">Add Group</Button>
                    </div>*/}

                    <Row>
                        <h3>My Courses</h3>
                    </Row>

                    <Row>
                        <Col md={3} xs={12}>
                            <SearchComponent
                                query={query}
                                selectedOption={selectedOption}
                                filteredCourses={filteredCourses}
                                updateQuery={this.updateQuery}
                                handleChosenCourse={this.handleChosenCourse}
                                handleAddingCourseToCalendar={this.handleAddingCourseToCalendar}
                            />
                        </Col>
                        <Col md={9} xs={12}>
                            <CalendarComponent
                                courseObject={courseGroupObject}
                            />
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default CourseList;