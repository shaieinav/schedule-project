import React, { Component } from 'react';
import { Button, Container, Row, Col } from 'reactstrap';
import AppNavBar from './AppNavBar';
import { Link } from 'react-router-dom';
import SearchComponent from '../components/SearchComponent'
import CourseTableComponent from '../components/CourseTableComponent'

class CourseList extends Component {

    constructor(props) {

        super(props);

        this.state = {
            courses: [],
            isLoading: true,
            query: '',
            selectedOption: [],
        };

        this.updateQuery = this.updateQuery.bind(this);
        this.handleChosenCourse = this.handleChosenCourse.bind(this);
        this.remove = this.remove.bind(this);
    }

    componentWillMount() {

        fetch('courses')
            .then(response => response.json())
            .then(data => this.setState({
                courses: data,
                isLoading: false
            }));
    }


    async remove(courseNum) {

        await fetch(`courses/${courseNum}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
            let updatedCourses = [...this.state.courses].filter(i => i.courseNum !== courseNum);
            this.setState({courses: updatedCourses});
    }

    updateQuery = (query) => {
        this.setState(() => ({
            query: query
        }))
    };

    handleChosenCourse = (courseNum) => {
        let updatedChosenCourse = [];
        fetch(`courses/${courseNum.value}`)
            .then(response => response.json())
            .then(data => {
                updatedChosenCourse = data.courseGroups;
                    this.setState({
                        selectedOption: updatedChosenCourse
                    })
                }
            )
    };

    render() {

        const { courses, isLoading, query, selectedOption } = this.state;

        const filteredCourses =
            query === ''
            ? courses
            : courses.filter((course) => (
                course.courseName.toLowerCase().includes(query.toLowerCase())
            ))

        if (isLoading) {
            return <p>Loading... Please Wait</p>;
        }

        return (
            <div>
                <AppNavBar/>
                <Container fluid>

                    <div className="float-right">
                        <Button color="success" tag={Link} to="/courses/new">Add Group</Button>
                    </div>

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
                            />
                        </Col>
                        <Col md={9} xs={12}>
                            <CourseTableComponent
                                filteredCourses={filteredCourses}
                                remove={this.remove}
                            />
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default CourseList;