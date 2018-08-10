import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table, Row, Col, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class CourseList extends Component {

    constructor(props) {

        super(props);

        this.state = {
            courses: [],
            isLoading: true,
            search: '',
            filter: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {

        this.setState({isLoading: true});
        this.setState({filter: []});

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
        }).then(() => {
            let updatedCourses = [...this.state.courses].filter(i => i.courseNum !== courseNum);
            this.setState({courses: updatedCourses});
        });
    }

    handleChange = (event) => {
        try {
            this.setState({search: event.target.value});
            let filter = this.state.courses.filter(course => course.courseName.includes(this.state.search));
            this.setState({filter: filter});
        }
        catch (error) {
            console.log(error);
        }
    };

    render() {

        const {courses, isLoading, search, filter} = this.state;

        if (isLoading) {
            return <p>Loading... Please Wait</p>;
        }

        const courseList = filter.map(course => {
            return (
                <tr key={course.courseNum}>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseNum}</td>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseName}</td>
                    <td style={{whiteSpace: 'nowrap'}}>{course.courseCredit}</td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" tag={Link} to={"/courses/" + course.courseNum}>Edit</Button>
                            <Button size="sm" color="danger" onClick={() => this.remove(course.courseNum)}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            )
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>

                    <div className="float-right">
                        <Button color="success" tag={Link} to="/courses/new">Add Group</Button>
                    </div>

                    <Row>
                        <h3>My Courses</h3>
                    </Row>

                    <Row>
                        <Col xs="3">
                            <div className="search-course">
                                <Container>
                                    <Form>
                                        <FormGroup>
                                            <Label for="courseNum">Choose Course Number or Name</Label>
                                            <Input type="text" name="courseNum" id="courseNum"
                                                   value={this.state.search || ''}
                                                   placeholder={"Enter a Course"}
                                                   onChange={this.handleChange} autoComplete="courseNum"
                                            />
                                        </FormGroup>
                                        <FormGroup>
                                            <Label for="courseNum">Choose Semester</Label>
                                            <Input type="text" name="semester" id="semester"
                                                   value={this.state.search || ''}
                                                   placeholder={"Choose Semester"}
                                                   onChange={this.handleChange} autoComplete="semester"
                                            />
                                        </FormGroup>
                                        <FormGroup>
                                            <Label for="courseNum">Choose a Course Group</Label>
                                            <Input type="text" name="courseGroup" id="courseGroup"
                                                   value={this.state.search || ''}
                                                   placeholder={"Choose Course Group"}
                                                   onChange={this.handleChange} autoComplete="courseGroup"
                                            />
                                        </FormGroup>
                                    </Form>
                                </Container>
                            </div>
                        </Col>
                        <Col xs="9">
                            <div className="courses">
                                <Table className="mt-4">
                                    <thead>
                                        <tr>
                                            <th width="20%">Course Number</th>
                                            <th width="50%">Course Name</th>
                                            <th width="20%">Course Credit</th>
                                            <th width="10%">Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {courseList}
                                    </tbody>
                                </Table>
                            </div>
                        </Col>
                    </Row>
                </Container>
            </div>
        );
    }
}

export default CourseList;