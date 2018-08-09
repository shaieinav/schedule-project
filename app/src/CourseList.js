import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class CourseList extends Component {

    constructor(props) {
        super(props);
        this.state = {
            courses: [],
            isLoading: true
        };
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {

        this.setState({isLoading: true});

        fetch('courses')
            .then(response => response.json())
            .then(data => this.setState({courses: data, isLoading: false}));
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

    render() {

        const {courses, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading... Please Wait</p>;
        }

        const courseList = courses.map(course => {
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
                    <h3>My Courses</h3>
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
                </Container>
            </div>
        );
    }
}

export default CourseList;